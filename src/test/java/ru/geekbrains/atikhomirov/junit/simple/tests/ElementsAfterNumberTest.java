package ru.geekbrains.atikhomirov.junit.simple.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for ArrWorker.getElementsAfterNumber(...) method")
public class ElementsAfterNumberTest {

    private static Stream<Arguments> testExceptionDataSupplier() {
        return Stream.of(
                Arguments.of(ArrWorker.getRandomArray(8,-10,3), 4),
                Arguments.of(ArrWorker.getRandomArray(8,5,20), 4),
                Arguments.of(ArrWorker.getRandomArray(8,20,40), 4)
        );
    }

    @DisplayName("Check that RuntimeException is thrown")
    @ParameterizedTest(name = "{index} ==> Input array: {0}, number: {1}")
    @MethodSource("testExceptionDataSupplier")
    public void testException(int[] sourceArray, int number) {
        assertThrows(RuntimeException.class, () -> ArrWorker.getElementsAfterNumber(sourceArray, number));
    }

    private static Stream<Arguments> testArraysDataSupplier() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7 }, 4, new int[] {1, 7}),
                Arguments.of(new int[] {1, 2, 4, 4, 2, 3, 8, 1, 7 }, 4, new int[] {2, 3, 8, 1, 7}),
                Arguments.of(new int[] {1, 2, 4, 4, 2, 3, 8, 1, 4 }, 4, new int[] {})
        );
    }

    @DisplayName("Check method results for some arrays")
    @ParameterizedTest(name = "{index} ==> Input array: {0}, number: {1}; Expected output array: {2}")
    @MethodSource("testArraysDataSupplier")
    public void testArrays(int[] sourceArray, int number, int[] expectedArray) {
        assertArrayEquals(expectedArray, ArrWorker.getElementsAfterNumber(sourceArray, number));
    }
}
