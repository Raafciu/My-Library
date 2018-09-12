package util.enums;

public enum YesNoEnum {

    TAK("T", "TAK"),
    NIE("N", "NIE");

    private final String databaseValue;
    private final String readableValue;

    YesNoEnum(String databaseValue, String readableValue) {
        this.databaseValue = databaseValue;
        this.readableValue = readableValue;
    }


    public String getDatabaseValue() {
        return databaseValue;
    }

    public String getReadableValue() {
        return readableValue;
    }

    @Override
    public String toString() {
        return "YesNoEnum{" +
                "databaseValue='" + databaseValue + '\'' +
                ", readableValue='" + readableValue + '\'' +
                '}';
    }
}
