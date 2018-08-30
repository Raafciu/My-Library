package util.validator.user;

import com.vaadin.data.Validator;

public class PeselValidator implements Validator {

    private static final String WRONG_PESEL = "Zly format peselu";

    @Override
    public void validate(Object value) {
        String pesel = (String) value;
        if (pesel.isEmpty()
                || !pesel.replace(" ", "").matches("[0-9]{11}")) {
            throw new Validator.InvalidValueException(WRONG_PESEL);
        }
    }
}
