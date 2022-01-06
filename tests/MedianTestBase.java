package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part1_6668.MedianCalculator;

public abstract class MedianTestBase<T extends MedianCalculator> {

    private T instance;
    protected abstract T createInstance();

    @BeforeEach
    public void setUp() {
        instance = createInstance();
    }

    @Test
    public void testEmptyArray() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> instance.getMedian(new int[] { }), "Array cannot be null or empty.");
    }

    @Test
    public void testNullArray() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> instance.getMedian(null), "Array cannot be null or empty.");
    }

    @Test
    public void testSingleElement() {
        var median = instance.getMedian(new int[] { 10 });
        Assertions.assertEquals(10, median);
    }

    @Test
    public void testThreeElements() {
        var median = instance.getMedian(new int[] { 10, 5, 20 });
        Assertions.assertEquals(10, median);
    }

    @Test
    public void testTenElements () {
        var median = instance.getMedian(new int[] { 6, 1, 4, 2, 3, 5, 8, 7, 9, 10 }); // 1 2 3 4   5 6   7 8 9 10
        Assertions.assertEquals(6, median);
    }

    // The following tests are taken from https://www.codinghelmet.com/articles/unit-testing-case-study-calculating-median
    @Test
    public void testEvenElements() {
        var median = instance.getMedian(new int[] { 6, 1, 4, 2, 3, 5 });
        Assertions.assertEquals(4, median);
    }

    @Test
    public void testOddElements() {
        var median = instance.getMedian(new int[] { 2, 5, 1, 3, 4 });
        Assertions.assertEquals(3, median);
    }

    @Test
    public void testTwoEqualElements() {
        var median = instance.getMedian(new int[] { 1, 1 });
        Assertions.assertEquals(1, median);
    }

    @Test
    public void testRepeatedLeft() {
        var median = instance.getMedian(new int[] { 3, 5, 1, 4, 3, 2 }); // 1 2 3 3 4 5
        Assertions.assertEquals(3, median);
    }

    @Test
    public void testRepeatedRight() {
        var median = instance.getMedian(new int[] { 4, 2, 4, 3, 1, 5 }); // 1 2 3 4 4 5
        Assertions.assertEquals(4, median);
    }

    @Test
    public void testRepeatedBoth() {
        var median = instance.getMedian(new int[] { 2, 3, 3, 4, 1, 3 }); // 1 2 3 3 3 4
        Assertions.assertEquals(3, median);
    }

    @Test
    public void testRepeatedLeft_Odd() {
        var median = instance.getMedian(new int[] { 2, 1, 4, 2, 3 }); // 1 2 2 3 4
        Assertions.assertEquals(2, median);
    }

    @Test
    public void testRepeatedRight_Odd() {
        var median = instance.getMedian(new int[] { 2, 1, 3, 4, 3 }); // 1 2 3 3 4
        Assertions.assertEquals(3, median);
    }

    @Test
    public void testRepeatedBoth_Odd() {
        var median = instance.getMedian(new int[] { 2, 3, 2, 2, 1 }); // 1 2 2 2 3
        Assertions.assertEquals(2, median);
    }
}
