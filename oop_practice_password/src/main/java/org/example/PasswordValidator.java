package org.example;


public class PasswordValidator {

    public static final String WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE = "비밀번호는 최소 8자 이상 12자 이하여야 한다.";

    public void validate(String password) {
        int length = password.length();
        if (length < Constants.MIN_LENGTH || length > Constants.MAX_LENGTH) {
            throw new IllegalArgumentException(WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    public void validate2(PasswordGeneratePolicy passwordGeneratePolicy) {
        String password = passwordGeneratePolicy.generatePassword();

        int length = password.length();
        if(length < Constants.MIN_LENGTH || length > Constants.MAX_LENGTH) {
            throw new IllegalArgumentException(WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE);
        }
    }
}
