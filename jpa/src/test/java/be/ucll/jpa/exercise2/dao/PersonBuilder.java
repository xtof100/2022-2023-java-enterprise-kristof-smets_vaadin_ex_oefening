package be.ucll.jpa.exercise2.dao;

import be.ucll.jpa.exercise2.entities.Address;
import be.ucll.jpa.exercise2.entities.Person;

public class PersonBuilder {

	private Person person = new Person();

	PersonBuilder id(Long id) {
		person.setId(id);
		return this;
	}

	PersonBuilder name(String name) {
		person.setName(name);
		return this;
	}

	PersonBuilder firstName(String firstName) {
		person.setFirstName(firstName);
		return this;
	}

	PersonBuilder address(Address address) {
		person.getAddress().add(address);
		return this;
	}

	public Person build() {
		return person;
	}
}
