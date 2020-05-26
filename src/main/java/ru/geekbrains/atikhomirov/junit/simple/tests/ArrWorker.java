package ru.geekbrains.atikhomirov.junit.simple.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Objects;  // for another option
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrWorker {
    private static List<Integer> getListFromIntArr(int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    public static int[] getElementsAfterNumberStream(int[] source, int number) {
        List<Integer> input = getListFromIntArr(source);
        if (input.isEmpty() || !input.contains(number)) {
            throw new RuntimeException("No number " + number + " in source array");
        }

        int lastPosOfNumber = IntStream.range(0, input.size())
                .filter(index -> input.get(index) == number)
                .reduce((first, second) -> second)
                .getAsInt();

        return input.stream().skip(lastPosOfNumber + 1).mapToInt(i -> i).toArray();
    }

    public static boolean checkArrForNumbersStream(int[] source, int... numbers) {
        List<Integer> input = getListFromIntArr(source);
        if (input.isEmpty() || numbers.length == 0) {
            return false;
        }

        List<Predicate<Integer>> filters = new ArrayList<>();
        for (int number:numbers) {
            if (!input.contains(number)) {
                return false;
            }
            //input.removeIf(i -> Objects.equals(number, i)); // another option
            filters.add(i -> i != number);
        }

        //return input.isEmpty(); // another option
        return input.stream().noneMatch(filters.stream().reduce(x -> true, Predicate::and));
    }

    private static int[] getUniqueSorted(int[] source) {
        return Arrays.stream(source).distinct().sorted().toArray();
    }

    public static boolean checkArrForNumbersStream2(int[] source, int... numbers) {
        if (numbers.length == 0 || numbers.length > source.length) {
            return false;
        }
        return Arrays.equals(getUniqueSorted(source), getUniqueSorted(numbers));
    }

    public static int[] getElementsAfterNumber(int[] source, int number) {
        for (int i = source.length; i > 0; --i){
            if (source[i - 1] == number) {
                return Arrays.copyOfRange(source, i, source.length);
            }
        }
        throw new RuntimeException("No number " + number + " in source array");
    }

    public static boolean checkArrForNumbers(int[] source, int... numbers) {
        if (numbers.length == 0 || numbers.length > source.length) {
            return false;
        }

        boolean[] numbersPresented = new boolean[numbers.length];
        for (int sourceElement:source) {
            boolean numberFound = false;
            for (int i = 0; i < numbers.length; ++i){
                if (sourceElement == numbers[i]) {
                    numbersPresented[i] = true;
                    numberFound = true;
                    break;
                }
            }
            if (!numberFound) {
                return false;
            }
        }

        boolean allNumbersPresented = true;
        for (boolean numberPresented: numbersPresented) {
            allNumbersPresented &= numberPresented;
        }
        return allNumbersPresented;
    }

    public static int[] getRandomArray(int len, int min, int max) {
        int[] arr = new int[len];
        max = max - min + 1;
        for (int i = 0; i < len; i++){
            arr[i] = (int) (Math.random() * max) + min;
        }
        return arr;
    }
}
