package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final String ID_REGEX = "^CUS[0-9]{3}$";
    public static final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public static final String EMAIL_REGEX = "^^[a-zA-Z]+[a-zA-Z0-9]*(@)[a-z]+.[a-z]{2,3}$";
    public static final String PHONE_REGEX = "^[0][0-9]{3}.[0-9]{3}.[0-9]{3}$";
    public static final String PRODUCT_ID_REGEX = "^RICE[0-9]{3}$";
    public static final String BILL_ID_REGEX = "^BILL[0-9]{3}$";

    public static boolean validate(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
