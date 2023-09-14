package ru.kata.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    @Test
    void shouldThrowIllegalArgumentException() {
        String testInput = "1 + 1 + 1";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Main.calc(testInput));
        assertEquals("incoming expression is too long: " + testInput, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 + ", "+1", "+ 1", " ", "bad"})
    void shouldThrowIllegalArgumentExceptionWithMessage(String badExpression) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Main.calc(badExpression));
        assertEquals("string is not a mathematical expression: " + badExpression, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1 + 1", "11 + 1"})
    void shouldThrowIllegalArgumentExceptionIfFirstOperandNoValid(String badExpression) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Main.calc(badExpression));
        String arg = badExpression.split(" ")[0];
        assertEquals(arg + " is not in range [1,10]", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 + -1", "1 + 11"})
    void shouldThrowIllegalArgumentExceptionIfSecondOperandNoValid(String badExpression) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Main.calc(badExpression));
        String arg = badExpression.split(" ")[2];
        assertEquals(arg + " is not in range [1,10]", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 f 1", "1 1 1", "1 & 1"})
    void shouldThrowIllegalStateExceptionIfUnexpectedValueOperation(String badExpression) {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> Main.calc(badExpression));
        String operation = badExpression.split(" ")[1];
        assertEquals("Unexpected value: " + operation, exception.getMessage());
    }

    @Test
    void additionArabic() {
        String expression = "10 + 10";
        String actual = Main.calc(expression);
        assertEquals("20", actual);
    }

    @Test
    void subtractionArabic() {
        String expression = "10 - 10";
        String actual = Main.calc(expression);
        assertEquals("0", actual);
    }

    @Test
    void multiplicationArabic() {
        String expression = "10 * 10";
        String actual = Main.calc(expression);
        assertEquals("100", actual);
    }

    @Test
    void divisionArabic() {
        String expression = "10 / 10";
        String actual = Main.calc(expression);
        assertEquals("1", actual);
    }

    @Test
    void additionRoman() {
        String expression = "X + X";
        String actual = Main.calc(expression);
        assertEquals("XX", actual);
    }

    @Test
    void subtractionRoman() {
        String expression = "X - V";
        String actual = Main.calc(expression);
        assertEquals("V", actual);
    }

    @Test
    void multiplicationRoman() {
        String expression = "X * X";
        String actual = Main.calc(expression);
        assertEquals("C", actual);
    }

    @Test
    void divisionRoman() {
        String expression = "X / X";
        String actual = Main.calc(expression);
        assertEquals("I", actual);
    }
}
