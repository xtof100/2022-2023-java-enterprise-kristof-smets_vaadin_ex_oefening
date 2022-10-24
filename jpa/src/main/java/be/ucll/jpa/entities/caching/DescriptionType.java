package be.ucll.jpa.entities.caching;

import java.util.Arrays;

public enum DescriptionType {
	CATEGORY("C"), ITEM("I");

	private String type;

	private DescriptionType(String type) {
		this.type = type;
	}

	public static DescriptionType fromType(String type) {
		for (DescriptionType descriptionType : DescriptionType.values()) {
			if (descriptionType.type.equals(type)) {
				return descriptionType;
			}
		}
		throw new RuntimeException("Could not construct DescriptionType enum from type: " + type
				+ " . Supported types:" + Arrays.toString(DescriptionType.values()));
	}

	public String getType() {
		return type;
	}
}
