package be.ucll.jpa.exercise4;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "partyType", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("P")
public abstract class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String identificationNumber;

	public abstract String name();

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(final String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
}
