package be.ucll.jpa.optimistic;

import be.ucll.jpa.entities.optimistic.SeatReservation;
import be.ucll.jpa.entities.optimistic.SeatReservationService;
import be.ucll.spring.JpaConfig;
import be.ucll.spring.MyAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import java.util.concurrent.atomic.AtomicLong;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;


@Test
@ContextConfiguration(classes = {JpaConfig.class, MyAppConfig.class})
@ActiveProfiles("jpa")
public class OptimisticTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private SeatReservationService seatReservationService;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public void test() {
        AtomicLong seatId = new AtomicLong();

        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setPropagationBehavior(TransactionTemplate.PROPAGATION_REQUIRES_NEW);

        transactionTemplate.execute((TransactionCallback<Void>) status -> {
            SeatReservation seatReservation = new SeatReservation();
            entityManager.persist(seatReservation);
            seatId.set(seatReservation.getId());
            return null;
        });

        //First request comes in and asks a seat, which is going to be reserved for Mike
        SeatReservation seatReservationForMike = seatReservationService.getSeat(seatId.get());
        assertNull(seatReservationForMike.getGivenTo()); // -- check if seat is still free

        //In the meantime, a second transaction - simulated here to be faster - asks the same seat to be reserverd
        //for John. This transaction finishes faster and has already taken the seat
        SeatReservation seatReservationForJohn = seatReservationService.getSeat(seatId.get());
        assertNull(seatReservationForJohn.getGivenTo()); // -- check if seat is still free
        seatReservationForJohn.setGivenTo("John");
        seatReservationService.reserveSeat(seatReservationForJohn);

        seatReservationForMike.setGivenTo("Mike");
        try {
            seatReservationService.reserveSeat(seatReservationForMike);
        } catch (OptimisticLockException e) {
            //Show message to the user the seat was already reserved
        }

        SeatReservation toCheck = seatReservationService.getSeat(seatId.get());
        assertEquals(toCheck.getGivenTo(), "John");
    }
}
