package by.yurchuk_krivetskaya.calc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


class CalcTest {

    private Calc calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calc();
    }

    @Test
    @DisplayName("Тест сложения простых чисел")
    void testSimpleAddition() {
        assertEquals(5.0, calculator.evaluateExpression("2+3"));
        assertEquals(10.0, calculator.evaluateExpression("5+5"));
    }

    @Test
    @DisplayName("Тест вычитания простых чисел")
    void testSimpleSubtraction() {
        assertEquals(2.0, calculator.evaluateExpression("5-3"));
        assertEquals(-1.0, calculator.evaluateExpression("2-3"));
    }

    @Test
    @DisplayName("Тест умножения простых чисел")
    void testSimpleMultiplication() {
        assertEquals(15.0, calculator.evaluateExpression("5*3"));
        assertEquals(0.0, calculator.evaluateExpression("0*5"));
    }

    @Test
    @DisplayName("Тест деления простых чисел")
    void testSimpleDivision() {
        assertEquals(2.5, calculator.evaluateExpression("5/2"));
        assertEquals(3.0, calculator.evaluateExpression("9/3"));
    }

    @Test
    @DisplayName("Тест приоритета операций (умножение перед сложением)")
    void testOperationPriority() {
        assertEquals(17.0, calculator.evaluateExpression("2+3*5"));
        assertEquals(40.0, calculator.evaluateExpression("2+3+5*7"));
        assertEquals(14.0, calculator.evaluateExpression("2*3+4*2"));
    }

    @Test
    @DisplayName("Тест сложных выражений с приоритетом")
    void testComplexExpressions() {
        assertEquals(23.0, calculator.evaluateExpression("2+3*7"));
        assertEquals(25.0, calculator.evaluateExpression("10+3*5"));
        assertEquals(8.0, calculator.evaluateExpression("20/2-2"));
    }

    @Test
    @DisplayName("Тест дробных чисел")
    void testDecimalNumbers() {
        assertEquals(7.5, calculator.evaluateExpression("2.5*3"));
        assertEquals(3.5, calculator.evaluateExpression("2.5+1"));
        assertEquals(1.5, calculator.evaluateExpression("3/2"));
    }

    @Test
    @DisplayName("Тест выражений с пробелами")
    void testExpressionsWithSpaces() {
        assertEquals(5.0, calculator.evaluateExpression("2 + 3"));
        assertEquals(15.0, calculator.evaluateExpression("5 * 3"));
        assertEquals(40.0, calculator.evaluateExpression("2 + 3 + 5 * 7"));
    }

    @Test
    @DisplayName("Тест длинных выражений")
    void testLongExpressions() {
        assertEquals(26.0, calculator.evaluateExpression("2+3*4+6*2"));
        assertEquals(30.0, calculator.evaluateExpression("10+5*4"));
        assertEquals(0.0, calculator.evaluateExpression("1+2+3-6"));
    }

    @Test
    @DisplayName("Тест деления на ноль")
    void testDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.evaluateExpression("5/0");
        });
        assertEquals("Деление на ноль", exception.getMessage());
    }

    @Test
    @DisplayName("Тест некорректных выражений")
    void testInvalidExpressions() {
        // Выражение начинается с оператора
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluateExpression("*5+3"));

        // Два оператора подряд
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluateExpression("5++3"));

        // Пустое выражение
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluateExpression(""));

        // Только пробелы
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluateExpression("   "));

        // Недопустимые символы
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluateExpression("2+a*3"));
    }

    @Test
    @DisplayName("Тест выражений с отрицательными числами")
    void testNegativeNumbers() {
        assertEquals(2.0, calculator.evaluateExpression("5-3"));
        assertEquals(-1.0, calculator.evaluateExpression("2-3"));
    }

    @Test
    @DisplayName("Тест граничных случаев")
    void testEdgeCases() {
        // Одно число
        assertEquals(5.0, calculator.evaluateExpression("5"));

        // Большие числа
        assertEquals(1000000.0, calculator.evaluateExpression("1000*1000"));

        // Много операций
        assertEquals(19.0, calculator.evaluateExpression("1+2*3+4*3"));
    }
}