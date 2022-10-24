package be.ucll.jpa.exercise3.onetomany;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class VehicleTypeConverter implements AttributeConverter<VehicleType, String> {

    @Override
    public String convertToDatabaseColumn(final VehicleType attribute) {
        return attribute.getType();
    }

    @Override
    public VehicleType convertToEntityAttribute(final String dbData) {
        return VehicleType.fromType(dbData);
    }
}
