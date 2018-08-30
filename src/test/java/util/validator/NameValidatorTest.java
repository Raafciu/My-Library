package util.validator;

import com.vaadin.data.Validator;
import org.junit.Test;
import util.validator.user.NameValidator;

public class NameValidatorTest {

    private final String CORRECT_FIRSTNAME = "Adam";
    private final String CORRECT_LASTNAME = "adamowski";
    private final String WRONG_FIRSTNAME = "Ada8m";
    private final String WRONG_LASTNAME = "Adamo0wski";
    private NameValidator firstNameValidator = new NameValidator();

    @Test
    public void shouldValidateFirstNameTest() {
        firstNameValidator.validate(CORRECT_FIRSTNAME);
    }

    @Test
    public void shouldValidateLastNameTest() {
        firstNameValidator.validate(CORRECT_LASTNAME);
    }

    @Test(expected = Validator.InvalidValueException.class)
    public void shouldNotValidateFirstNameTest() {
        firstNameValidator.validate(WRONG_FIRSTNAME);
    }

    @Test(expected = Validator.InvalidValueException.class)
    public void shouldNotValidateLastNameTest() {
        firstNameValidator.validate(WRONG_LASTNAME);
    }

}