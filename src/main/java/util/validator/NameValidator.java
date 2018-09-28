package util.validator;

import com.vaadin.data.Validator;

public class NameValidator implements Validator {

    private static final String WRONG_NAME = "Zły Format";
    private static final String REGEX_PATTERN = "[A-ZŻŹĆĄŚĘŁÓŃa-zżźćńółęąś\\d]{3,50}";

    @Override
    public void validate(Object value) {
        String name = (String) value;

        if (name.isEmpty()
                || !name.replace(" ", "").matches(REGEX_PATTERN))
            throw new Validator.InvalidValueException(WRONG_NAME);
    }
}
