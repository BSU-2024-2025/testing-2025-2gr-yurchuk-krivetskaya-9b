package by.yurchuk_krivetskaya.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calc {

    public double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", "");

        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Пустое выражение");
        }

        if (!isValidExpression(expression)) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        List<String> rpn = convertToRPN(expression);


        return evaluateRPN(rpn);
    }

    private boolean isValidExpression(String expression) {
        if (!expression.matches("[0-9+\\-*/.]+")) {
            return false;
        }


        if ("+*/".contains(String.valueOf(expression.charAt(0)))) {
            return false;
        }


        for (int i = 0; i < expression.length() - 1; i++) {
            char current = expression.charAt(i);
            char next = expression.charAt(i + 1);
            if ("+-*/".contains(String.valueOf(current)) && "+*/".contains(String.valueOf(next))) {
                return false;
            }
        }

        return true;
    }

    private List<String> convertToRPN(String expression) {
        List<String> output = new ArrayList<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {

                StringBuilder number = new StringBuilder();
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) ||  expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                output.add(number.toString());
                continue;
            }

            if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && getPrecedence(operators.peek()) >= getPrecedence(c)) {
                    output.add(String.valueOf(operators.pop()));
                }
                operators.push(c);
            }

            i++;
        }

        while (!operators.isEmpty()) {
            output.add(String.valueOf(operators.pop()));
        }

        return output;
    }

    private int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private double evaluateRPN(List<String> rpn) {
        Stack<Double> stack = new Stack<>();

        for (String token : rpn) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {

                stack.push(Double.parseDouble(token));
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Недостаточно операндов для операции");
                }

                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        stack.push(a / b);
                        break;
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        return stack.pop();
    }
}