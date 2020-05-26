package ru.geekbrains.atikhomirov.junit.simple.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for ArrWorker.checkArrForNumbers(...) method")
public class CheckArrForNumbersTest {

    private static Stream<Arguments> testArraysDataSupplier() {
        return Stream.of(
                Arguments.of(new int[] {1, 4, 1, 1, 4, 4, 1, 1, 4 }, new int[] {1, 4}, true),
                Arguments.of(new int[] {4, 4, 4, 4, 4, 4, 4, 4, 4 }, new int[] {1, 4}, false),
                Arguments.of(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1 }, new int[] {1, 4}, false),
                Arguments.of(new int[] {1, 1, 4, 1, 4, 1, 4, 1, 3 }, new int[] {1, 4}, false),
                Arguments.of(new int[] {}, new int[] {1, 4}, false),
                Arguments.of(new int[] {1, 4}, new int[] {}, false)
        );
    }

    @DisplayName("Check method results for some arrays")
    @ParameterizedTest(name = "{index} ==> Input array: {0}, numbers: {1}; Expected result: {2}")
    @MethodSource("testArraysDataSupplier")
    public void testArrays(int[] sourceArray, int[] numbers, boolean expectedResult) {
        assertEquals(expectedResult, ArrWorker.checkArrForNumbersStream(sourceArray, numbers));
        assertEquals(expectedResult, ArrWorker.checkArrForNumbersStream2(sourceArray, numbers));
        assertEquals(expectedResult, ArrWorker.checkArrForNumbers(sourceArray, numbers));
    }
}
