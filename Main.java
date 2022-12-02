
import java.util.Scanner;

public class Main {

    private final static String[] arrayRomanDigits = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private final static String[] arrayRomanTen = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) {

        int num1 = 0;
        int num2 = 0;
        String sign = "";
        String answer = "";
        String romanDigit1 = null;
        String romanDigit2 = null;
        boolean romanIsTrue = false;

        try {
            String[] inputArray = checkInput(input);
            try {
                num1 = Integer.parseInt(inputArray[0]);
                num2 = Integer.parseInt(inputArray[2]);
            } catch (RuntimeException e) {
                romanDigit1 = inputArray[0];
                romanDigit2 = inputArray[2];
                romanIsTrue = true;
            }
            sign = inputArray[1];

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        try {
            if (romanIsTrue) {
                answer = convertArabDigitToRoman(calculate(sign, convertRomanDigitToArab(romanDigit1), convertRomanDigitToArab(romanDigit2)));
            } else {
                try {
                    checkInt(num1);
                    checkInt(num2);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
                answer = calculate(sign, num1, num2);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return answer;
    }

    public static String calculate(String sign, int num1, int num2) {
        String temp;
        temp = switch (sign) {
            case "+" -> "" + (num1 + num2);
            case "-" -> "" + (num1 - num2);
            case "*" -> "" + (num1 * num2);
            case "/" -> "" + (num1 / num2);
            default -> null;
        };
        if (temp == null)
            throw new RuntimeException("Введен неверный знак");
        return temp;
    }

    public static String[] checkInput(String input) {
        String[] inputArray = input.split(" ");
        if (inputArray.length != 3)
            throw new RuntimeException("Введено неверное выражение");
        return inputArray;
    }

    public static void checkInt(int num) {
        if (num < 1 || num > 10)
            throw new RuntimeException("Операнды должны быть от 1 до 10 включительно");
    }

    public static int convertRomanDigitToArab(String romanDigit) {
        int number = 0;
        if (romanDigit.equals("X"))
            number = 10;
        for (int i = 0; i < arrayRomanDigits.length; i++) {
            if (arrayRomanDigits[i].equals(romanDigit)) {
                number = i + 1;
                break;
            }
        }
        if (number == 0)
            throw new RuntimeException("Введены неверные операнды");
        return number;
    }

    public static String convertArabDigitToRoman(String intDigit) {
        String answer;
        int temp = Integer.parseInt(intDigit);
        if (temp < 1)
            throw new RuntimeException("Римские цифры могут быть только положительными");
        if (temp >= 10 && temp % 10 != 0)
            answer = arrayRomanTen[temp / 10 - 1] + arrayRomanDigits[temp % 10 - 1];
        else if (temp >= 10)
            answer = arrayRomanTen[temp / 10 - 1];
        else
            answer = arrayRomanDigits[temp - 1];
        return answer;
    }

}