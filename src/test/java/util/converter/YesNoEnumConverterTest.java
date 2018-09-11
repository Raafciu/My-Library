package util.converter;

import org.junit.Test;
import util.enums.YesNoEnum;

import static org.junit.Assert.assertEquals;
import static util.enums.YesNoEnum.NIE;
import static util.enums.YesNoEnum.TAK;

public class YesNoEnumConverterTest {


    private final YesNoEnumConverter testConverter = new YesNoEnumConverter();


    @Test
    public void shouldConvertEnumToStringTest() {
        String expectedValueYes = "Y";
        String expectedValueNo = "N";

        String value = testConverter.convertToDatabaseColumn(TAK);
        assertEquals(expectedValueYes, value);

        value = testConverter.convertToDatabaseColumn(NIE);
        assertEquals(expectedValueNo, value);
    }


    @Test
    public void shouldConvertStringToEnumTest() {

        YesNoEnum value = testConverter.convertToEntityAttribute("Y");
        assertEquals(TAK, value);

        value = testConverter.convertToEntityAttribute("N");
        assertEquals(NIE, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionDuringConvertStringToEnumTest() {
        testConverter.convertToEntityAttribute("badValue");
    }
}