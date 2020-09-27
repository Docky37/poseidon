package com.nnk.springboot.model.mapping;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.nnk.springboot.constants.Constants;

public class RegExPatternTest {

    static List<String> validPasswordList = new ArrayList<>();
    static List<String> invalidPasswordList = new ArrayList<>();
    static {
        validPasswordList.add("Tadm-123");
        validPasswordList.add("TXad*-12");

        invalidPasswordList.add("Tadm----"); // No digit
        invalidPasswordList.add("TADM-123"); // No Lower case letter
        invalidPasswordList.add("tadm-123"); // No Upper case letter
        invalidPasswordList.add("Tadm0123"); // No special character
        invalidPasswordList.add("Tadm -123"); // Contains a space
        invalidPasswordList.add("Tadm+123"); // Invalid character
        invalidPasswordList.add("Tadm-12"); // Too short
        invalidPasswordList.add("Tadm-6789012345678901"); // Too long

    }

    Pattern pattern = Pattern.compile(Constants.PASSWORD_REGEXP);

    @Test
    public void givenValidPasswords_whenTest_thenMatch() {
        System.out.println("Valid passwords list:");
        for (String password : validPasswordList) {
            Matcher matcher = pattern.matcher(password);
            assertTrue(matcher.matches());
            System.out.println(password + " : " + matcher.matches());
        }
    }

    @Test
    public void givenInvalidPasswords_whenTest_thenDoNotMatch() {
        System.out.println("\nInvalid passwords list:");
        for (String password : invalidPasswordList) {
            Matcher matcher = pattern.matcher(password);
            assertFalse(matcher.matches());
            System.out.println(password + " : " + matcher.matches());
        }
    }

}
