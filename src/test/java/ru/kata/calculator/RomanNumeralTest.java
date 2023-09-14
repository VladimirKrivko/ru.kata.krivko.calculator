package ru.kata.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RomanNumeralTest {

    @ParameterizedTest
    @ValueSource(strings = {"IIV", "1", "LSPD"})
    void shouldThrowIllegalArgumentExceptionWhenGiveBadRomanForConvertToArabic(String badRoman) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RomanNumeral.romanToArabic(badRoman));
        assertEquals(badRoman + " cannot be converted to a Roman Numeral", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 101})
    void shouldThrowIllegalArgumentExceptionWhenGiveNotConvertibleArabic(int badArabic) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> RomanNumeral.arabicToRoman(badArabic));
        assertEquals(badArabic + " is not in range (0,100], for this reason cannot be converted to a Roman Numeral", exception.getMessage());
    }

    @Test
    void romanToArabic() {
        String roman = "XCIX";
        int actual = RomanNumeral.romanToArabic(roman);
        assertEquals(99, actual);
    }

    @Test
    void arabicToRoman() {
        int arabic = 1;
        String actual = RomanNumeral.arabicToRoman(arabic);
        assertEquals("I", actual);
    }
}
