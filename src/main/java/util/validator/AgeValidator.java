package util.validator;

import com.vaadin.data.Validator;

public class AgeValidator implements Validator {

    private final String WRONG_AGE = "Zły format Wieku";

    @Override
    public void validate(Object value) throws InvalidValueException {
            String age = (String) value;
            if(age.isEmpty()
                || !age.replace(" ","").matches("\\d{1,3}")){
                throw new InvalidValueException(WRONG_AGE);
            }
    }
}
