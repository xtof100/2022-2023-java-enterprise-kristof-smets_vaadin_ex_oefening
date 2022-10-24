package be.ucll.jpa.entities.optimistic;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class SeatReservationServiceImpl implements SeatReservationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SeatReservation getSeat(Long seatId) {
        return null;
    }

    @Override
    public void reserveSeat(SeatReservation seatReservation) {

    }
}
