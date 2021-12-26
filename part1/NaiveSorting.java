package part1;

import java.util.Arrays;

public class NaiveSorting implements MedianCalculator {
    @Override
    public int getMedian(int[] array) {
        Arrays.sort(array);
        return array[array.length / 2];
    }
}
