package library.control.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PagesValidatorTest {

    @Test
    public void shouldPatternPagesTest() {
        Validator validator = new PagesValidator();
        String expectedInteger = "43423423423423";
        assertTrue(validator.validate(expectedInteger));
    }

    @Test
    public void shouldNotPatternPagesTest() {
        Validator validator = new PagesValidator();
        String expectedInteger = "badValue";
        assertFalse(validator.validate(expectedInteger));
    }


}