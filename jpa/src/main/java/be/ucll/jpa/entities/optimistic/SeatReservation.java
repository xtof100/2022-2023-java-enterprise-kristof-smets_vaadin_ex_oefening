package be.ucll.jpa.entities.optimistic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLocking;

@Entity
public class SeatReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String seatId;
	private String givenTo;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(final String seatId) {
		this.seatId = seatId;
	}

	public String getGivenTo() {
		return givenTo;
	}

	public void setGivenTo(final String givenTo) {
		this.givenTo = givenTo;
	}

}
