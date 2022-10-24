package be.ucll.jpa.exercise4;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

public class DeliveryAddress extends Address {

	private boolean allowNeighbourDelivery;

	public boolean isAllowNeighbourDelivery() {
		return allowNeighbourDelivery;
	}

	public void setAllowNeighbourDelivery(boolean allowNeighbourDelivery) {
		this.allowNeighbourDelivery = allowNeighbourDelivery;
	}
}
