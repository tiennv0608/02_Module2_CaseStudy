package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static final String EMAIL_REGEX = "^^[a-zA-Z]+[a-zA-Z0-9]*(@)[a-z]+.[a-z]{2,3}$";
    public static final String PHONE_REGEX = "^[0][0-9]{3}.[0-9]{3}.[0-9]{3}$";

    public static boolean validate(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
