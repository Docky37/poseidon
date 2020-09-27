package com.nnk.springboot.constants;

public class Constants {

    /**
     * A String length of ten characters.
     */
    public static final int LENGTH_10 = 10;

    /**
     * A String length of thirty characters.
     */
    public static final int LENGTH_30 = 30;

    /**
     * A String length of one hundred and twenty five characters.
     */
    public static final int LENGTH_125 = 125;

    /**
     * A String length of five hundred and twelve characters.
     */
    public static final int LENGTH_512 = 512;

    /**
     * A number of four digits .
     */
    public static final int N4_DIGITS = 4;

    /**
     * A number of twelve digits .
     */
    public static final int N12_DIGITS = 12;

    /**
     * A number of sixteen digits .
     */
    public static final int N16_DIGITS = 16;

    /**
     * The max positive value of an integer.
     */
    public static final int MAX_POSITIVE_INTEGER = 2147483647;

    /**
     * The minimum number of characters of a username.
     */
    public static final int USERNAME_MIN_SIZE = 7;

    /**
     * The maximum number of characters of a username.
     */
    public static final int USERNAME_MAX_SIZE = 20;

    /**
     * The minimum number of characters of fullname.
     */
    public static final int FULLNAME_MIN_SIZE = 8;

    /**
     * The maximum number of characters of a username.
     */
    public static final int FULLNAME_MAX_SIZE = 30;

    /**
     * Constant used to set the maximum age of the cookie in seconds.
     */
    public static final int COOKIE_VALIDITY_IN_SECONDS = 3600;

     /**
     * Constant used to set the maximum age of the cookie in seconds.
     */
    public static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[a-z])"
            + "(?=.*[A-Z])(?=.*[!@#$%^&*-])(?=[a-zA-Z0-9!@#$%^&*-]+$).{8,20}$";

   /**
     * Empty constructor.
     */
    protected Constants() {
    }

}
