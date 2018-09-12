package util.converter;

import util.enums.YesNoEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static util.enums.YesNoEnum.NIE;
import static util.enums.YesNoEnum.TAK;

@Converter
public class YesNoEnumConverter implements AttributeConverter<YesNoEnum, String> {

    @Override
    public String convertToDatabaseColumn(YesNoEnum attribute) {
        switch (attribute) {
            case TAK:
                return "T";
            case NIE:
                return "N";
            default:
                throw new IllegalArgumentException("Unknown " + attribute);
        }
    }

    @Override
    public YesNoEnum convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "T":
                return TAK;
            case "N":
                return NIE;
            default:
                throw new IllegalArgumentException("Unknown " + dbData);
        }
    }
}
