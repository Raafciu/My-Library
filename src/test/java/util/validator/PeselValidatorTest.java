package util.validator;

import com.vaadin.data.Validator;
import org.junit.Test;
import util.validator.user.PeselValidator;

public class PeselValidatorTest {

    private static final String CORRECT_PESEL = "51234568901";
    private static final String WRONG_PESEL = "asdfghjkloi";
    private PeselValidator peselValidator = new PeselValidator();

    @Test
    public void shouldValidatePeselTextfield(){
        peselValidator.validate(CORRECT_PESEL);
    }
    @Test(expected = Validator.InvalidValueException.class)
    public void shouldNotValidatePeselTextfield(){
        peselValidator.validate(WRONG_PESEL);
    }

}