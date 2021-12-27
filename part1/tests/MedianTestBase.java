package part1.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part1.MedianCalculator;

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

    // TODO: Add more tests.
}
