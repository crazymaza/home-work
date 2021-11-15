package com.sbrf.reboot.calculator;

public class Calculator {
    public static int getAddition(int firstValue, int secondValue) {
        return firstValue + secondValue;
    }

    public static int getSubtraction(int firstValue, int secondValue) {
        return firstValue - secondValue;
    }

    public static int getMultiplication(int firstValue, int secondValue) {
        return firstValue * secondValue;
    }

    public static int getDivision(int firstValue, int secondValue) {
        return firstValue / secondValue;
    }

    public static int getRemainder(int firstValue, int secondValue) {
        return firstValue % secondValue;
    }

    public static double getExponentiation(int firstValue, int exp) {
        return Math.pow(firstValue, exp);
    }

    public static int getSquare(int value) {
        return value * value;
    }
}
