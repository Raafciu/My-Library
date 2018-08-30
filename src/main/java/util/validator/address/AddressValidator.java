package util.validator.address;

import com.vaadin.data.Validator;

public class AddressValidator implements Validator {

    private final String WRONG_NAME = "Zły Format";

    @Override
    public void validate(Object value) {
        String userAddress = (String) value;
        if (userAddress.isEmpty()
                || !userAddress.replace(" ", "").matches("[A-ZŻŹĆĄŚĘŁÓŃa-zżźćńółęąś]{3,50}")
        ) {
            throw new Validator.InvalidValueException(WRONG_NAME);
        }
    }
}
