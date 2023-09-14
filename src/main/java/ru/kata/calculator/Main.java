package ru.kata.calculator;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String result = calc(input);
        System.out.println(result);
    }

    public static String calc(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        try {
            if (tokenizer.countTokens() > 3) {
                throw new IOException("incoming expression is too long: " + input);
            }
            if (tokenizer.countTokens() < 3) {
                throw new IOException("string is not a mathematical expression: " + input);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        int first = 0;
        int second = 0;
        String leftOperand = tokenizer.nextToken();
        String operation = tokenizer.nextToken();
        String rightOperand = tokenizer.nextToken();
        boolean isRomanExpression = false;

        try {
            first = Integer.parseInt(leftOperand);
            second = Integer.parseInt(rightOperand);
        } catch (Exception e) {
            first = RomanNumeral.romanToArabic(leftOperand);
            second = RomanNumeral.romanToArabic(rightOperand);
            isRomanExpression = true;
        }

        rangeValidation(first);
        rangeValidation(second);

        int result = switch (operation) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> {
                if (second == 0) {
                    throw new ArithmeticException("division by zero: " + input);
                }
                yield first / second;
            }
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };

        return isRomanExpression ? RomanNumeral.arabicToRoman(result) : String.valueOf(result);
    }

    private static void rangeValidation(int operand) {
        if (operand < 1 || operand > 10) {
            throw new IllegalArgumentException(operand + " is not in range [1,10]");
        }
    }
}
