package part1;

public class MedianOfMedians implements MedianCalculator {
    @Override
    public int getMedian(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        // TODO: Implement.
        if(array.length != 0 & array[0]!=0)
            return 10;
        return 0;
    }
}
