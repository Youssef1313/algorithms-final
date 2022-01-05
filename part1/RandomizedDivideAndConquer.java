package part1;

import java.util.Random;

public class RandomizedDivideAndConquer implements MedianCalculator {
    private static Random random = new Random();

    @Override
    public int getMedian(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        int medianIndex = array.length / 2;
        return randomizedSelect(array, 0, array.length - 1, medianIndex);
    }

    // Returns the ith smallest element in array[start..end] (start and end are inclusive)
    private int randomizedSelect(int[] array, int start, int end, int i) {
        if (start == end)
            return array[start];
        int q = randomizedPartition(array, start, end);

        int k = q - start;
        if (i == k)
            return array[q];
        else if (i < k)
            return randomizedSelect(array, start, q - 1, i);
        else
            return randomizedSelect(array, q + 1, end, i - k - 1);
    }

    private int randomizedPartition(int[] array, int start, int end) {
        int pivotIndex = random.nextInt(end - start + 1); // random integer in range [0, end - start]
        pivotIndex += start; // random integer in range [start, end]
        swap(array, end, pivotIndex);
        return partition2(array, start, end);
    }

    // Hoare partitioning algorithm, visualized here https://www.youtube.com/watch?v=NuQYFXmLUrM
//    private int partition(int[] array, int start, int end, int pivotIndex) {
//        int pivot = array[pivotIndex];
//        start--;
//        end++;
//
//        while (true) {
//            do {
//                start++;
//            }
//            while (pivot > array[start]);
//
//            do {
//                end--;
//            }
//            while (pivot < array[end]);
//
//            if (start >= end) {
//                return end;
//            }
//
//            // swap array[start] with array[end]
//            int temp = array[start];
//            array[start] = array[end];
//            array[end] = temp;
//        }
//    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // An inefficient implementation of partitioning :/
    // CLRS 7.1
    private int partition2(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, end);
        return i + 1;
    }
}
