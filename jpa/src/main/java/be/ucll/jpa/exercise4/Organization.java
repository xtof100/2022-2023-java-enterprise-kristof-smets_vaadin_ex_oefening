package be.ucll.jpa.exercise4;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

public class Organization extends Party {

	private String commercialName;

	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(final String commercialName) {
		this.commercialName = commercialName;
	}

	@Override
	public String name() {
		return commercialName;
	}
}
