package part1;

public class RandomizedDivideAndConquer implements MedianCalculator {
    @Override
    public int getMedian(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }

        // TODO: Implement.
        int i = array.length / 2;
        return medianRandomized(array, 0, array.length-1, i);
    }

    private int medianRandomized(int[] array, int p, int r, int i) {
        if (p == r)
            return array[p];
        int q = randomizedPartition(array, p, r);

        int k = q - p + 1;
        if (i == k)
            return array[q];
        else if (i < k)
            return medianRandomized(array, p, q-1, i);
        else
            return  medianRandomized(array, q+1, r, i-k);
    }

    private int randomizedPartition(int[] array, int p, int r) {
        int i = (int) (Math.random() * p); //Get A Random Number Between p and r
        int temp = array[i];
        array[i] = array[r];
        array[r] = temp;
        return partition(array, p, r);
    }

    private int partition(int[] array, int p, int r) {
        int x = array[p];
        int i = p - 1;
        int j = r + 1;
        while (true){
            while (array[j] <= x)
                j-=1;
            while (array[i] >= x)
                i+=1;

            if (i < j){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }else
                return j;

        }

    }
}
