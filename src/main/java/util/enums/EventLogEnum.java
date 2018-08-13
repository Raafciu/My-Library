package util.enums;

public enum EventLogEnum {
    LOGOWANIE("LOGOWANIE"),
    WYLOGOWANIE("WYLOGOWANIE"),
    WYPOZYCZENIE("WYPOZYCZENIE"),
    ZWROCENIE("ZWRÓCENIE");

    private final String value;

    EventLogEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EventLogEnum{" +
                "value='" + value + '\'' +
                '}';
    }
}
