package util.validator;

import com.vaadin.data.Validator;
import org.junit.Test;

public class UserNameValidatorTest {

    private final String CORRECT_FIRSTNAME = "Adam";
    private final String CORRECT_LASTNAME = "adamowski";
    private final String WRONG_FIRSTNAME = "Ada8m";
    private final String WRONG_LASTNAME = "Adamo0wski";
    private UserNameValidator firstUserNameValidator = new UserNameValidator();

    @Test
    public void shouldValidateFirstNameTest() {
        firstUserNameValidator.validate(CORRECT_FIRSTNAME);
    }

    @Test
    public void shouldValidateLastNameTest() {
        firstUserNameValidator.validate(CORRECT_LASTNAME);
    }

    @Test(expected = Validator.InvalidValueException.class)
    public void shouldNotValidateFirstNameTest() {
        firstUserNameValidator.validate(WRONG_FIRSTNAME);
    }

    @Test(expected = Validator.InvalidValueException.class)
    public void shouldNotValidateLastNameTest() {
        firstUserNameValidator.validate(WRONG_LASTNAME);
    }

}