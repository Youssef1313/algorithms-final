package median;

import java.util.Arrays;

public class NaiveSorting implements MedianCalculator {
    @Override
    public int getMedian(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        Arrays.sort(array);
        return array[array.length / 2];
    }
}
