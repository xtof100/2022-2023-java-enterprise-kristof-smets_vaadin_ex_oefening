package be.ucll.jpa.exercise2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import be.ucll.jpa.exercise2.AddressSearchCriteria;
import be.ucll.jpa.exercise2.entities.Person;

@Profile("jpa")
@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Person> findAll() {
		return entityManager.createQuery("from Person").getResultList();
	}

	public List<Person> findByName(String name) {
		return null;
	}

	public void removePerson(Person person) {
		entityManager.remove(person);
	}

	public void savePerson(Person person) {
		entityManager.persist(person);
	}

	public List<Person> findByAddress(AddressSearchCriteria addressSearchCriteria) {
		return null;
	}
}