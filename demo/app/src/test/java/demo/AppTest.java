/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package demo;

import demo.validations.ValidationUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
        @Test void validateRun() {
                // Basic
                assertFalse(ValidationUtils.validateRun(""));
                assertFalse(ValidationUtils.validateRun(" "));
                assertFalse(ValidationUtils.validateRun("12345678912"));
                assertFalse(ValidationUtils.validateRun("1234567891"));

                // Must pass
                assertTrue(ValidationUtils.validateRun("11.123.432-6"));
                assertTrue(ValidationUtils.validateRun("1.123.432-6"));
                assertTrue(ValidationUtils.validateRun("1.123.432-K"));

                // Will it break?
                assertFalse(ValidationUtils.validateRun("1.123.432-H"));
                assertFalse(ValidationUtils.validateRun("11123.432-99"));
                assertFalse(ValidationUtils.validateRun("11.123.43299"));
                assertFalse(ValidationUtils.validateRun("11.123.432-99"));
        }

        @Test void validateTel() {
                // Basic
                assertFalse(ValidationUtils.validateTel(""));
                assertFalse(ValidationUtils.validateTel(" "));
                assertFalse(ValidationUtils.validateTel("1234"));
                assertFalse(ValidationUtils.validateTel("abc"));

                // Must pass
                assertTrue(ValidationUtils.validateTel("12345678"));
                assertTrue(ValidationUtils.validateTel("+5290909023"));
                assertTrue(ValidationUtils.validateTel("+569 90909023"));

                // Will it break?
                assertFalse(ValidationUtils.validateTel("12345678901234567"));
                assertFalse(ValidationUtils.validateTel("+1abc123456"));
                assertFalse(ValidationUtils.validateTel("+569 12312321321321321"));
        }
}
