package by.yurchuk_krivetskaya.calc;


import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class CalcTest {

    private Calc calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calc();
    }

    @ParameterizedTest
    @MethodSource("provideBasicOperations")
    @DisplayName("Тест основных математических операций")
    void testBasicOperations(String expression, double expectedResult) {
        assertEquals(expectedResult, calculator.evaluateExpression(expression), 0.001);
    }

    private static Stream<Arguments> provideBasicOperations() {
        return Stream.of(
                // Сложение
                Arguments.of("2+3", 5.0),
                Arguments.of("5+5", 10.0),
                Arguments.of("2.5+1", 3.5),

                // Вычитание
                Arguments.of("5-3", 2.0),
                Arguments.of("2-3", -1.0),

                // Умножение
                Arguments.of("5*3", 15.0),
                Arguments.of("0*5", 0.0),
                Arguments.of("2.5*3", 7.5),

                // Деление
                Arguments.of("5/2", 2.5),
                Arguments.of("9/3", 3.0),
                Arguments.of("3/2", 1.5),

                // Приоритет операций
                Arguments.of("2+3*5", 17.0),
                Arguments.of("2+3+5*7", 40.0),
                Arguments.of("2*3+4*2", 14.0),
                Arguments.of("20/2-2", 8.0),

                // Длинные выражения
                Arguments.of("2+3*4+6*2", 26.0),
                Arguments.of("10+5*4", 30.0),
                Arguments.of("1+2+3-6", 0.0),
                Arguments.of("1+2*3+4*3", 19.0),

                // Граничные случаи
                Arguments.of("5", 5.0),
                Arguments.of("1000*1000", 1000000.0)
        );
    }

}