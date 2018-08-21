package util.validator;

import com.vaadin.data.Validator;

public class NameValidator implements Validator{

    private final String WRONG_NAME = "Zły Format";

    @Override
    public void validate(Object value) throws Validator.InvalidValueException {
        String firstName = (String) value;
        if(firstName.isEmpty()
                ||  !firstName.replace(" ","").matches("[A-ZŻŹĆĄŚĘŁÓŃa-zżźćńółęąś]{3,50}")){
            throw new Validator.InvalidValueException(WRONG_NAME);
        }
    }
}
