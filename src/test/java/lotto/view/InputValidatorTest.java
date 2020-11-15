package lotto.view;

import lotto.exception.NanException;
import lotto.exception.NpeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class InputValidatorTest {

    @Test
    @DisplayName("숫자가 아닌 String 이면, NpeException 이 발생한다.")
    public void validateStrNan() {
        Assertions.assertAll(
                () -> assertThatExceptionOfType(NanException.class)
                        .isThrownBy(() -> InputValidator.validateNan(null)),
                () -> assertThatExceptionOfType(NanException.class)
                        .isThrownBy(() -> InputValidator.validateNan("NAN")),
                () -> Assertions.assertDoesNotThrow(() -> InputValidator.validateNan("123"))
        );
    }

    @Test
    @DisplayName("null 인 경우에, NpeException 이 발생한다.")
    public void validateNpe() {
        Assertions.assertAll(
                () -> assertThatExceptionOfType(NpeException.class)
                        .isThrownBy(() -> InputValidator.validateNpe(null)),
                () -> Assertions.assertDoesNotThrow(() -> InputValidator.validateNpe("NAN"))
        );
    }

    @Test
    @DisplayName("숫자가 아니거나 null 이면, Exception 이 발생한다.")
    public void validateNumber() {
        Assertions.assertAll(
                () -> assertThatExceptionOfType(NpeException.class)
                        .isThrownBy(() -> InputValidator.validateNumber(null)),
                () -> assertThatExceptionOfType(NanException.class)
                        .isThrownBy(() -> InputValidator.validateNumber("NAN"))
        );
    }
}
