package util.validator.address;

import com.vaadin.data.Validator;

public class PostalCodeValidator implements Validator {

    private final String WRONG_DATA = "Zły Format, (xx-xxx)";

    @Override
    public void validate(Object value) throws Validator.InvalidValueException {
        String postalCode = (String) value;
        if (postalCode.isEmpty()
                || !postalCode.replace(" ", "").matches("[0-9]{2}-[0-9]{3}")
        ) {
            throw new Validator.InvalidValueException(WRONG_DATA);
        }
    }
}
