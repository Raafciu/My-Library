package control.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PagesValidator implements Validator {

    private static final String PATTERN = "[0-9]+";

    @Override
    public boolean validate(Object object) {
        String value = String.valueOf(object);
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
