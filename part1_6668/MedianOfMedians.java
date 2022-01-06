import java.util.Arrays;

public class MedianOfMedians implements MedianCalculator {
    // From CLRS 9.3:
    // 1. Divide the n elements of the input array into floor(n/5) groups of 5 elements each
    //    and at most one group made up of the remaining n mod 5 elements
    // 2. Find the median of each of the ceil(n/5) groups by first insertion-sorting the elements
    //    of each group (of which there are at most 5) and then picking the median
    //    from the sorted list of group elements
    // 3. Use SELECT recursively to find the median x of the ceil(n/5) medians found in
    //    step 2. (If there are an even number of medians, then by our convention, x is
    //    the lower median.)
    // 4. Partition the input array around the median-of-medians x using the modified
    //    version of PARTITION. Let k be one more than the number of elements on the
    //    low side of the partition, so that x is the kth smallest element and there are n-k
    //    elements on the high side of the partition.
    // 5. If i = k, then return x. Otherwise, use SELECT recursively to find the ith
    //    smallest element on the low side if i < k, or the (i - k)th smallest element on
    //    the high side if i>k
    @Override
    public int getMedian(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        return select(array, 0, array.length - 1, array.length / 2);
    }

    // kth smallest (k is 0-indexed)
    private int select(int[] array, int start, int end, int k) {
        int length = end - start + 1;
        if (length <= 5) {
            Arrays.sort(array, start, end + 1);
            return array[start + k];
        }

        var medians = new int[(int)Math.ceil(length / 5.0)];

        for (int i = 0; i < medians.length; i++) {
            int fromIndex = i * 5;

            // Arrays.sort parameters are 'fromIndex' (inclusive) and 'toIndex' (**exclusive**).
            int toIndex = Math.min(fromIndex + 5, end + 1);
            Arrays.sort(array, fromIndex, toIndex);

            int medianIndex = fromIndex + (toIndex - fromIndex) / 2;
            medians[i] = array[medianIndex];
        }

        int medianOfMedians = select(medians, 0, medians.length - 1, medians.length / 2);
        int partitionIndex = partition2(array, start, end, medianOfMedians);
        if (partitionIndex == k) {
            return medianOfMedians;
        }
        else if (partitionIndex < k) {
            // search right.
            return select(array, start + partitionIndex + 1, end, k - partitionIndex - 1);
        }
        else {
            // search left.
            return select(array, start, partitionIndex - 1, k);
        }
    }

    // TODO: These are almost exact copies from RandomizedDivideAndConquer. Refactor!
    // An inefficient implementation of partitioning :/
    // CLRS 7.1
    private int partition2(int[] array, int start, int end, int pivot) {
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

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
