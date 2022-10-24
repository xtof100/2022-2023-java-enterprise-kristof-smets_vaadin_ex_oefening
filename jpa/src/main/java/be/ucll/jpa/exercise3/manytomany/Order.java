package be.ucll.jpa.exercise3.manytomany;

import java.util.ArrayList;
import java.util.Collection;

public class Order {

	private Long id;

	private String test;

	private Collection<Item> parents = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTest() {
		return test;
	}

	public void setTest(final String test) {
		this.test = test;
	}

	public Collection<Item> getParents() {
		return parents;
	}

	public void setParents(final Collection<Item> parents) {
		this.parents = parents;
	}
}