package util.validator.user;

import com.vaadin.data.Validator;

public class NameValidator implements Validator {

    private final String WRONG_NAME = "Zły Format";

    @Override
    public void validate(Object value) {
        String name = (String) value;
        if (name.isEmpty()
                || !name.replace(" ", "").matches("[A-ZŻŹĆĄŚĘŁÓŃa-zżźćńółęąś]{3,50}")) {
            throw new Validator.InvalidValueException(WRONG_NAME);
        }
    }
}
