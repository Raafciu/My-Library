package util.validator;

import com.vaadin.data.Validator;
import org.junit.Test;

import static org.junit.Assert.*;

public class AgeValidatorTest {

    private final String CORRECT_AGE = "60";
    private final String WRONG_AGE = "zlywiek";

    private AgeValidator ageValidator = new AgeValidator();

    @Test
    public void shouldValidateAgeTest(){
        ageValidator.validate(CORRECT_AGE);
    }

    @Test(expected = Validator.InvalidValueException.class)
    public void shouldNotValidateAgeTest(){
        ageValidator.validate(WRONG_AGE);
    }

}