package Util;

import java.util.Random;

public class PasswordGenerator {
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+-={}[]|:;<>,.?/";

    public static String generatePassword(int passwordLength) {
        StringBuilder password = new StringBuilder();
        String charSet = UPPERCASE_LETTERS + LOWERCASE_LETTERS + DIGITS + SPECIAL_CHARACTERS;

        Random random = new Random();

        while (true) {
            for (int i = 0; i < passwordLength; i++) {
                int Index = random.nextInt(charSet.length()-1);
                password.append(charSet.charAt(Index));
            }
            if (checkPass(password.toString()))
                return password.toString();

            password.delete(0, password.length());
        }
    }

    //sometimes generatePassword() method generates a password that doesn't contain one of the attributes
    public static boolean checkPass(String password) {
        boolean containsUpperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean containsLowerCase = password.chars().anyMatch(Character::isLowerCase);
        boolean containsDigit = password.chars().anyMatch(Character::isDigit);
        boolean containsSpecialChar = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));

        if (containsUpperCase && containsLowerCase && containsDigit && containsSpecialChar)
            return true;
        else
            return false;
    }
}
