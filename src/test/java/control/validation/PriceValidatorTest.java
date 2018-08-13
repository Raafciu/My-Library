//package control.validation;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PriceValidatorTest {
//
//    @Test
//    public void shouldPatternPriceTest() {
//        Validator validator = new PriceValidator();
//        String expectedValue = "4423423.33";
//        assertTrue(validator.validate(expectedValue));
//    }
//
//    @Test
//    public void shouldNotPatternPriceTest(){
//        Validator validator = new PriceValidator();
//        String expectedValue = "badValue";
//        assertFalse(validator.validate(expectedValue));
//    }
//
//    @Test
//    public void shouldNotPatternPrice2Test(){
//        Validator validator = new PriceValidator();
//        String expectedValue = "123,123";
//        assertFalse(validator.validate(expectedValue));
//    }
//
//}