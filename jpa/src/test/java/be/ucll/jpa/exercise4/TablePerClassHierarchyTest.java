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
public class TablePerClassHierarchyTest extends AbstractTransactionalTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;

    public void test() {
        NaturalPerson johnDoe = new NaturalPerson();
        johnDoe.setIdentificationNumber("1");
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");

        NaturalPerson mobyDick = new NaturalPerson();
        mobyDick.setIdentificationNumber("1");
        mobyDick.setFirstName("Moby");
        mobyDick.setLastName("Dick");

        Organization ucll = new Organization();
        ucll.setIdentificationNumber("1");
        ucll.setCommercialName("UCLL");

        entityManager.persist(johnDoe);
        entityManager.persist(mobyDick);
        entityManager.persist(ucll);

        entityManager.flush();
        entityManager.clear();

        Collection<Party> party = entityManager.createQuery("from Party p where p.identificationNumber = :identificationNumber").setParameter("identificationNumber", "1").getResultList();
        Assert.assertEquals(party.size(), 3);

        System.err.println(party);
    }
}
