package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.example.PasswordValidator.*;

/**
 * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
 * 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외를 발생시킨다.
 * 경계조건에 대해 테스트 코드를 작성해야 한다.
 */

public class PasswordValidatorTest {

    @Test
    @DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생한다.")
    void validatePasswwordTest() {
        // given
        String password = "12345678";
        PasswordValidator passwordValidator = new PasswordValidator();

        // when, then
        assertThatCode(() -> passwordValidator.validate(password))
                .doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만, 12자 초과하는 경우 IllegalArgumentException 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1234567", "1234567890123"})
    void validatePasswordTest2(String password) {
        // given
        PasswordValidator passwordValidator = new PasswordValidator();

        // when, then
        assertThatCode(() -> passwordValidator.validate(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE);
    }

    /**
     * 테스트 하기 쉬운 코드를 작성하다 보면 더 낮은 결합도를 가진 설계를 얻을 수 있다.
     */
    @DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다.")
    @Test
    void validatePasswordTest3() {
        // given
        PasswordValidator passwordValidator = new PasswordValidator();

        // when, then
        assertThatCode(() -> passwordValidator.validate2(new CorrectPasswordGenerator()))
                .doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만, 12자 초과하는 경우 IllegalArguementException 예외가 발생한다.")
    @Test
    void validatePasswordTest4() {
        // given
        PasswordValidator passwordValidator = new PasswordValidator();

        // when, then
        assertThatCode(() -> passwordValidator.validate2(new WrongPasswordGenerator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE);
    }
}
