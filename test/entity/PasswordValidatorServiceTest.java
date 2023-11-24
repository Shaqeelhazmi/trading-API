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
        String password = "Pw1234";
        assertTrue(passwordValidator.passwordIsValid(password));
    }
}