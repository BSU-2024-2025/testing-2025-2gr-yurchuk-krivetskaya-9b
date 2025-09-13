package by.yurchuk_krivetskaya.main;

import by.yurchuk_krivetskaya.calc.Calc;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calc calculator = new Calc();

        System.out.println("Простой калькулятор");
        System.out.println("Введите выражение (например: 2+3+5*7) или 'выход' для завершения:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("выход")) {
                System.out.println("Калькулятор завершает работу.");
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            try {
                double result = calculator.evaluateExpression(input);
                System.out.println("Результат: " + result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Пожалуйста, введите корректное выражение.");
            }
        }

        scanner.close();
    }
}