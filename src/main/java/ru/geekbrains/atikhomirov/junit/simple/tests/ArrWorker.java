package ru.geekbrains.atikhomirov.junit.simple.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrWorker {
    public static int[] getElementsAfterNumber(int[] source, int number) {
        List<Integer> input = Arrays.stream(source).boxed().collect(Collectors.toList());
        if (input.isEmpty() || !input.contains(number)) {
            throw new RuntimeException("No number " + number + " in source array");
        }

        int lastPosOfNumber = IntStream.range(0, input.size())
                .filter(index -> input.get(index) == number)
                .reduce((first, second) -> second)
                .getAsInt();

        return input.stream().skip(lastPosOfNumber + 1).mapToInt(i -> i).toArray();
    }

    public static boolean checkArrForNumbers(int[] source, int... numbers) {
        List<Integer> input = Arrays.stream(source).boxed().collect(Collectors.toList());
        if (input.isEmpty() || numbers.length == 0) {
            return false;
        }

        List<Predicate<Integer>> filters = new ArrayList<>();
        for (int number:numbers) {
            if (!input.contains(number)) {
                return false;
            }
            filters.add(i -> i != number);
        }

        return input.stream().noneMatch(filters.stream().reduce(x -> true, Predicate::and));
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
