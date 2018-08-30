package util.validator.address;

import com.vaadin.data.Validator;

public class NumberOfBuildingValidator implements Validator {

    private final String WRONG_DATA = "Zły Format";

    @Override
    public void validate(Object value) {
        String numberOfBuilding = (String) value;
        if (numberOfBuilding.isEmpty()
                || !numberOfBuilding.replace(" ", "").matches("[0-9]{1,3}")
        ) {
            throw new Validator.InvalidValueException(WRONG_DATA);
        }
    }
}
