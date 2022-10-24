package be.ucll.jpa.exercise4;

import be.ucll.spring.JpaConfig;
import be.ucll.spring.MyAppConfig;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;


@Test
@ContextConfiguration(classes = {JpaConfig.class, MyAppConfig.class})
@ActiveProfiles("jpa")
public class TablePerSubclassTest extends AbstractTransactionalTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;

    public void test() {

        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setCountry("belgium");
        deliveryAddress.setAllowNeighbourDelivery(true);

        entityManager.persist(deliveryAddress);

        ContactAddress contactAddress = new ContactAddress();
        contactAddress.setCountry("belgium");

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setCountry("germany");

        entityManager.persist(contactAddress);

        entityManager.flush();
        entityManager.clear();

        Collection<Address> addresses = entityManager.createQuery("from Address2 a where a.country = :country").setParameter("country", "belgium")
                .getResultList();
        Assert.assertEquals(addresses.size(), 2);

        deliveryAddress = (DeliveryAddress) entityManager.createQuery("from DeliveryAddress d where d.country = :country")
                .setParameter("country", "belgium").getResultList().iterator().next();
        Assert.assertNotNull(deliveryAddress);
    }
}
