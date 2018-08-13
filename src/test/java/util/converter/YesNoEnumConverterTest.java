package util.converter;

import org.junit.Test;
import util.enums.YesNoEnum;

import static org.junit.Assert.assertEquals;
import static util.enums.YesNoEnum.NO;
import static util.enums.YesNoEnum.YES;

public class YesNoEnumConverterTest {


    private final YesNoEnumConverter testConverter = new YesNoEnumConverter();


    @Test
    public void shouldConvertEnumToStringTest() {
        String expectedValueYes = "Y";
        String expectedValueNo = "N";

        String value = testConverter.convertToDatabaseColumn(YES);
        assertEquals(expectedValueYes, value);

        value = testConverter.convertToDatabaseColumn(NO);
        assertEquals(expectedValueNo, value);
    }


    @Test
    public void shouldConvertStringToEnumTest() {

        YesNoEnum value = testConverter.convertToEntityAttribute("Y");
        assertEquals(YES, value);

        value = testConverter.convertToEntityAttribute("N");
        assertEquals(NO, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDuringConvertStringToEnumTest() {
        testConverter.convertToEntityAttribute("badValue");
    }
}