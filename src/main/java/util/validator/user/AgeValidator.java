package util.validator.user;

import com.vaadin.data.Validator;

public class AgeValidator implements Validator {

    private static final String WRONG_AGE = "Zły format Wieku";
    private static final String REGEX_PATTERN = "\\d{1,3}";

    @Override
    public void validate(Object value) {
        String age = (String) value;
        if (age.isEmpty()
                || !age.replace(" ", "").matches(REGEX_PATTERN)) {
            throw new InvalidValueException(WRONG_AGE);
        }
    }
}
