package benchmarks;

import median.MedianOfMedians;
import median.NaiveSorting;
import median.RandomizedDivideAndConquer;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(value = Scope.Benchmark)
public class Benchmarks10000 {
    private static int[] arr = new int[10000];

    @Setup(Level.Invocation)
    public static void initData() {
        var random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void sort10000_naive() {
        var naive = new NaiveSorting();
        naive.getMedian(arr);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void sort10000_medianOfMedians() {
        var medianOfMedians = new MedianOfMedians();
        medianOfMedians.getMedian(arr);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void sort10000_randomized() {
        var randomizedDivideAndConquer = new RandomizedDivideAndConquer();
        randomizedDivideAndConquer.getMedian(arr);
    }
}
