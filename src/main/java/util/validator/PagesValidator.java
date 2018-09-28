package util.validator;

import com.vaadin.data.Validator;

public class PagesValidator implements Validator {

    private static final String WRONG_PAGES = "Zły format";
    private static final String REGEX_PATTERN = "[0-9]{1,4}";

    @Override
    public void validate(Object value) {
        String pages = (String) value;

        if (pages.isEmpty()
                || !pages.replace(" ", "").matches(REGEX_PATTERN))
            throw new Validator.InvalidValueException(WRONG_PAGES);
    }
}
