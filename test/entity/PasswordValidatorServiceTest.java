package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorServiceTest {
    private PasswordValidatorService passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidatorService();
    }

    @Test
    void passwordIsValid() {
        String password1 = "Pw1234";
        assertTrue(passwordValidator.passwordIsValid(password1));
        String password2 = "";
        assertFalse(passwordValidator.passwordIsValid(password2));
        String password3 = "123";
        assertFalse(passwordValidator.passwordIsValid(password3));
    }
}