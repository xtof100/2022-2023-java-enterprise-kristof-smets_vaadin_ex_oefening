package be.ucll.jpa.exercise1.dao;

import be.ucll.jpa.exercise1.entities.Address;

public class AddressBuilder {

	private Address address = new Address();

	public AddressBuilder country(String country) {
		address.setCountry(country);
		return this;
	}

	public AddressBuilder municipality(String municipality) {
		address.setMunicipality(municipality);
		return this;
	}

	public AddressBuilder postalCode(String postalCode) {
		address.setPostalCode(postalCode);
		return this;
	}

	public AddressBuilder street(String street) {
		address.setStreet(street);
		return this;
	}

	public AddressBuilder houseNumber(String houseNumber) {
		address.setHouseNumber(houseNumber);
		return this;
	}

	public AddressBuilder box(String box) {
		address.setBox(box);
		return this;
	}

	public Address build() {
		return address;
	}
}
