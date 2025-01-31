package org.springbootapp.proplus_backendapplication.constants;

public class ApplicationConstants {

    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_KEY_DEFAULT_VALUE = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";

    public static final String USERNAME_VALIDATION_REGEX = "^[A-Za-z0-9]{3,50}$";
    public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]{1,63}\\.[a-zA-Z]+$";
    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
    public static final String NAME_VALIDATION_REGEX = "^[A-Za-zžđčćšŠĐČĆ]{2,50}$";

    public static final String USERNAME_VALIDATION_MESSAGE = "Username must be between 3 and 50 characters and contain only letters and digits";
    public static final String EMAIL_VALIDATION_MESSAGE = "Email must have at least 5 characters and be in valid format";
    public static final String PASSWORD_VALIDATION_MESSAGE = "Password must be between 8 and 20 characters and contain at least one uppercase letter, one lowercase letter, one digit and one special character";
    public static final String NAME_VALIDATION_MESSAGE = "must be between 2 and 50 characters and contain only letters";
    public static final String DATE_VALIDATION_MESSAGE = "Date must be in valid format (yyyy-MM-dd)";
    public static final String ROLE_VALIDATION_MESSAGE = "Enter valid role (0 : Member) (1 : Co-Leader) (2 : Leader)";
    public static final String NOT_NULL_MESSAGE = "can't be null";
}
