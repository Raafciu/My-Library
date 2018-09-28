package util.validator;

import com.vaadin.data.Validator;

public class PriceValidator implements Validator {

    private static final String WRONG_PRICE = "Zły format (x.xx}";
    private static final String REGEX_PATTERN = "[0-9]{0,17}\\.[0-9]{0,2}";

    @Override
    public void validate(Object value) {
        String price = (String) value;

        if (price.isEmpty()
                || !price.replace(" ", "").matches(REGEX_PATTERN))
            throw new Validator.InvalidValueException(WRONG_PRICE);
    }
}
