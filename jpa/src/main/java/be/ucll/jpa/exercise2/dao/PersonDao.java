package be.ucll.jpa.exercise2.dao;

import java.util.List;

import be.ucll.jpa.exercise2.AddressSearchCriteria;
import be.ucll.jpa.exercise2.entities.Person;


public interface PersonDao {

	public List<Person> findAll();

	public List<Person> findByName(String name);

	public void savePerson(Person person);

	public void removePerson(Person person);

	public List<Person> findByAddress(AddressSearchCriteria addressSearchCriteria);

}
