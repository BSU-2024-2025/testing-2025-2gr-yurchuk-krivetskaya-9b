package by.yurchuk_krivetskaya.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calc {
    public double evaluateExpression(String expression){
        return 0;
    }

    private List<String> convertToRPN(String expression){
        List<String> result = new ArrayList<String>();
        return result;
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
                // Если токен - число, добавляем в стек
                stack.push(Double.parseDouble(token));
            } else {
                // Если токен - оператор, извлекаем два операнда и выполняем операцию
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Недостаточно операндов для операции");
                }

                double b = stack.pop();
                double a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
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
