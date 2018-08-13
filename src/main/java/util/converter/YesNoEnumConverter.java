package util.converter;

import util.enums.YesNoEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static util.enums.YesNoEnum.NO;
import static util.enums.YesNoEnum.YES;

@Converter
public class YesNoEnumConverter implements AttributeConverter<YesNoEnum, String> {

    @Override
    public String convertToDatabaseColumn(YesNoEnum attribute) {
        switch (attribute) {
            case YES:
                return "Y";
            case NO:
                return "N";
            default:
                throw new IllegalArgumentException("Unknown " + attribute);
        }
    }

    @Override
    public YesNoEnum convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "Y":
                return YES;
            case "N":
                return NO;
            default:
                throw new IllegalArgumentException("Unknown " + dbData);
        }
    }
}
