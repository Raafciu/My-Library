package util.enums;

public enum YesNoEnum {

    YES("Y", "Yes"),
    NO("N", "No");

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
