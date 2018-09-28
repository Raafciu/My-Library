package util.validator;

import com.vaadin.data.Validator;

public class IsbnValidator implements Validator {

    private static final String WRONG_ISBN = "Zły ISBN";
    private static final String REGEX_PATTERN = "[0-9]{13}";

    @Override
    public void validate(Object value) {
        String isbn = (String) value;

        if (isbn.isEmpty()
                || !isbn.replace(" ", "").matches(REGEX_PATTERN))
            throw new Validator.InvalidValueException(WRONG_ISBN);
    }
}
