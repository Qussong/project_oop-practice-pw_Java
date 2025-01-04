package org.example;

import org.passay.*;

import java.util.ArrayList;
import java.util.List;

public class RandomPasswordGenerator implements PasswordGeneratePolicy {
    /**
     * Special characters allowed in password.
     */
    public static final String ALLOWED_SPL_CHARACTERS = "!@#$%^&*()_+";

    public static final String ERROR_CODE = "ERRONEOUS_SPECIAL_CHARS";

    @Override
    public String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();

        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return ALLOWED_SPL_CHARACTERS;
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        List<Rule> Rules = new ArrayList<Rule>();
        Rules.add(splCharRule);
        Rules.add(lowerCaseRule);
        Rules.add(upperCaseRule);
        Rules.add(digitRule);

        // 0 ~ 12
        //return gen.generatePassword((int) (Math.random() * 13), splCharRule, lowerCaseRule, upperCaseRule, digitRule);
        return gen.generatePassword((int) (Math.random() * 13), Rules);
    }
}
