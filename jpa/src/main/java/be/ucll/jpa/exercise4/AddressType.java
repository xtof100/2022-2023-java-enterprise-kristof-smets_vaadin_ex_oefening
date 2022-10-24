package be.ucll.jpa.exercise4;

import java.util.Arrays;

public enum AddressType {

	CONTACT("C"), BILLING("B"), DELIVERY("D");

	private String type;

	private AddressType(String type) {
		this.type = type;
	}

	public static AddressType fromType(String type) {
		for (AddressType addressType : AddressType.values()) {
			if (addressType.type.equals(type)) {
				return addressType;
			}
		}
		throw new RuntimeException("Could not construct AddressType enum from type: " + type + " . Supported types:"
				+ Arrays.toString(AddressType.values()));
	}

	public String getType() {
		return type;
	}
}
