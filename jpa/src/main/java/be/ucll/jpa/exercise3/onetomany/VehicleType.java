package be.ucll.jpa.exercise3.onetomany;

public enum VehicleType {

	BOAT("BT"),
	AIRCRAFT("AC"),
	TRAIN("TR");

	private String type;

	VehicleType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static VehicleType fromType(String s) {

		for (VehicleType vehicleType : VehicleType.values()) {
			if (vehicleType.getType().equals(s)) {
				return vehicleType;
			}
		}

		throw new IllegalStateException("No type for:" + s);
	}
}
