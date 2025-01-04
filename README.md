# 📌패스워드 검증기

### 🔻조건

1. 비밀번호는 최소 8자 이상 12자 이하여야 한다.
2. 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외를 발생시킨다.
3. 경계조건에 대해 테스트 코드를 작성해야 한다.

### 🔻환경

- OS : Windows 11
- Language : Java 17

    >openjdk version "17.0.13" 2024-10-15</br>OpenJDK Runtime Environment Temurin-17.0.13+11 (build 17.0.13+11)</br>OpenJDK 64-Bit Server VM Temurin-17.0.13+11 (build 17.0.13+11, mixed mode, sharing)<br>

- IDE : IntelliJ IDEA Ultimate 2024.3.1.1</br>
- Build Tool : Gradle
- Test Tool : JUnit 5
- Library : AssertJ, Passay


### 🔻Case1

```java
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
```

### 🔻Case2

```java
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
```

### 🔻Case3

```java
@DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다.")
@Test
void validatePasswordTest3() {
    // given
    PasswordValidator passwordValidator = new PasswordValidator();

    // when, then
    assertThatCode(() -> passwordValidator.validate2(new CorrectPasswordGenerator()))
            .doesNotThrowAnyException();
}
```

### 🔻Case4

```java
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
```

