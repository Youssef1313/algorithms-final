package benchmarks;

import median.MedianOfMedians;
import median.NaiveSorting;
import median.RandomizedDivideAndConquer;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;

@State(value = Scope.Benchmark)
public class Benchmarks {
    private static int[] arr1000 = new int[50];
    private static int[] arr10000 = new int[10000];
    private static int[] arr100000 = new int[100000];
    private static int[] arr1000000 = new int[1000000];
    private static int[] arr10000000 = new int[10000000];

    @Setup(Level.Invocation)
    public static void initData() {
        var random = new Random();

        for (int i = 0; i < arr1000.length; i++) {
            arr1000[i] = random.nextInt();
        }
        for (int i = 0; i < arr10000.length; i++) {
            arr10000[i] = random.nextInt();
        }
        for (int i = 0; i < arr100000.length; i++) {
            arr100000[i] = random.nextInt();
        }
        for (int i = 0; i < arr1000000.length; i++) {
            arr1000000[i] = random.nextInt();
        }
        for (int i = 0; i < arr10000000.length; i++) {
            arr10000000[i] = random.nextInt();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort1000_naive() {
        var naive = new NaiveSorting();
        naive.getMedian(arr1000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort1000_medianOfMedians() {
        var medianOfMedians = new MedianOfMedians();
        System.out.println(Arrays.toString(arr1000));
        medianOfMedians.getMedian(arr1000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort1000_randomized() {
        var randomizedDivideAndConquer = new RandomizedDivideAndConquer();
        randomizedDivideAndConquer.getMedian(arr1000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort10000_naive() {
        var naive = new NaiveSorting();
        naive.getMedian(arr10000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort10000_medianOfMedians() {
        var medianOfMedians = new MedianOfMedians();
        medianOfMedians.getMedian(arr10000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort10000_randomized() {
        var randomizedDivideAndConquer = new RandomizedDivideAndConquer();
        randomizedDivideAndConquer.getMedian(arr10000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort100000_naive() {
        var naive = new NaiveSorting();
        naive.getMedian(arr100000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort100000_medianOfMedians() {
        var medianOfMedians = new MedianOfMedians();
        medianOfMedians.getMedian(arr100000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort100000_randomized() {
        var randomizedDivideAndConquer = new RandomizedDivideAndConquer();
        randomizedDivideAndConquer.getMedian(arr100000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort1000000_naive() {
        var naive = new NaiveSorting();
        naive.getMedian(arr1000000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort1000000_medianOfMedians() {
        var medianOfMedians = new MedianOfMedians();
        medianOfMedians.getMedian(arr1000000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort1000000_randomized() {
        var randomizedDivideAndConquer = new RandomizedDivideAndConquer();
        randomizedDivideAndConquer.getMedian(arr1000000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort10000000_naive() {
        var naive = new NaiveSorting();
        naive.getMedian(arr10000000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort10000000_medianOfMedians() {
        var medianOfMedians = new MedianOfMedians();
        medianOfMedians.getMedian(arr10000000);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void sort10000000_randomized() {
        var randomizedDivideAndConquer = new RandomizedDivideAndConquer();
        randomizedDivideAndConquer.getMedian(arr10000000);
    }
}
