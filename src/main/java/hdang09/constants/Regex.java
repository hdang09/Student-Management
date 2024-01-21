package hdang09.constants;

public final class Regex {
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$";
    public static final String PHONE_NUMBER_REGEX = "^(0|\\+?84)(3|5|7|8|9)[0-9]{8}$";
    public static final String IDENTITY_CARD = "^(0[0-9]{2})([0-9]{1})([0-9]{2})([0-9]{6})$";
    public static final String ROLL_NUMBER = "^[S|s|C|c|H|h|Q|q|D|d][E|e|A|a|S|s]+([0-9]{6})$";
}