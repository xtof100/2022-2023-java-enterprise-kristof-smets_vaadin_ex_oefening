package be.ucll.jpa.exercise2.dao;

import static org.testng.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import be.ucll.jpa.exercise2.AddressSearchCriteria;
import be.ucll.jpa.exercise2.entities.Person;
import be.ucll.spring.JpaConfig;
import be.ucll.spring.MyAppConfig;

@Test
@ContextConfiguration(classes = { JpaConfig.class, MyAppConfig.class })
@ActiveProfiles("jpa")
public class PersonDaoJpaTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private PersonDao personDao;

	@PersistenceContext
	private EntityManager entityManager;

	private Person johnDoe, steveWilson;

	@BeforeMethod
	private void dataSetup() {
		johnDoe = new PersonBuilder() {
			{
				firstName("John").name("Doe");
				address(new AddressBuilder() {
					{
						country("belgium");
						municipality("brussels").postalCode("1000");
						street("nieuwstraat");
						houseNumber("1").box("A");
					}
				}.build());
			}
		}.build();

		steveWilson = new PersonBuilder() {
			{
				firstName("Steve").name("Wilson");
				address(new AddressBuilder() {
					{
						country("belgium");
						municipality("antwerp").postalCode("2000");
						street("meir");
						houseNumber("10");
					}
				}.build());

				address(new AddressBuilder() {
					{
						country("spain");
						municipality("madrid").postalCode("57867");
						street("lospolloshermanos");
						houseNumber("1");
					}
				}.build());
			}
		}.build();

		personDao.savePerson(johnDoe);
		entityManager.flush();
		personDao.savePerson(steveWilson);
	}

	public void testAll() {
		List<Person> persons = personDao.findAll();
		assertEquals(persons.size(), 2);

		Person john = persons.stream().filter(p -> p.getFirstName().equals("John")).findFirst().get();
		Person steve = persons.stream().filter(p -> p.getFirstName().equals("Steve")).findFirst().get();

		assertEquals(john.getAddress().size(), 1);
		assertEquals(john.getName(), "Doe");

		assertEquals(steve.getAddress().size(), 2);
		assertEquals(steve.getName(), "Wilson");
	}

	public void testFindByName() {
		List<Person> persons = personDao.findByName("doe");
		assertEquals(persons.size(), 1);

		Person john = persons.stream().filter(p -> p.getFirstName().equals("John")).findFirst().get();

		assertEquals(john.getFirstName(), "John");
		assertEquals(john.getName(), "Doe");
		assertEquals(john.getAddress().size(), 1);
	}

	public void testFindByAddressCriteria() {
		AddressSearchCriteria addressSearchCriteria = new AddressSearchCriteria();
		addressSearchCriteria.setCountry("belgium");
		addressSearchCriteria.setMunicipality("antwerp");

		List<Person> persons = personDao.findByAddress(addressSearchCriteria);
		assertEquals(persons.size(), 1);
		Person steve = persons.stream().filter(p -> p.getFirstName().equals("Steve")).findFirst().get();

		assertEquals(steve.getFirstName(), "Steve");
		assertEquals(steve.getName(), "Wilson");
		assertEquals(steve.getAddress().size(), 2);

		addressSearchCriteria = new AddressSearchCriteria();
		addressSearchCriteria.setCountry("belgium");
		addressSearchCriteria.setMunicipality("brussels");

		persons = personDao.findByAddress(addressSearchCriteria);
		assertEquals(persons.size(), 1);

		Person john = persons.stream().filter(p -> p.getFirstName().equals("John")).findFirst().get();

		assertEquals(john.getFirstName(), "John");
		assertEquals(john.getName(), "Doe");
		assertEquals(john.getAddress().size(), 1);

		addressSearchCriteria = new AddressSearchCriteria();
		addressSearchCriteria.setCountry("belgium");
		persons = personDao.findByAddress(addressSearchCriteria);
		assertEquals(persons.size(), 2);
	}

	public void testDelete() {
		personDao.removePerson(personDao.findByName("Doe").iterator().next());
		personDao.removePerson(personDao.findByName("Wilson").iterator().next());
		assertEquals(personDao.findByName("Wilson").size(), 0);
		assertEquals(personDao.findByName("Doe").size(), 0);
	}
}