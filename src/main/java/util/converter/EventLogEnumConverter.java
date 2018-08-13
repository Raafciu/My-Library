package util.converter;

import util.enums.EventLogEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static util.enums.EventLogEnum.*;

@Converter
public class EventLogEnumConverter implements AttributeConverter<EventLogEnum, String> {

    @Override
    public String convertToDatabaseColumn(EventLogEnum attribute) {
        switch (attribute) {
            case LOGOWANIE:
                return "LOGOWANIE";
            case WYLOGOWANIE:
                return "WYLOGOWANIE";
            case WYPOZYCZENIE:
                return "WYPOZYCZENIE";
            case ZWROCENIE:
                return "ZWROCENIE";
            default:
                throw new IllegalArgumentException("Unknown " + attribute);
        }
    }

    @Override
    public EventLogEnum convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "LOGOWANIE":
                return LOGOWANIE;
            case "WYLOGOWANIE":
                return WYLOGOWANIE;
            case "WYPOZYCZENIE":
                return WYPOZYCZENIE;
            case "ZWROCENIE":
                return ZWROCENIE;
            default:
                throw new IllegalArgumentException("Unknown " + dbData);
        }
    }
}
