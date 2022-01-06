package tests;

import median.MedianCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    public void largeRandomArray() {
        var array = new int[] { 314144227, -196011574, 156218098, -123051864, 1894537203, -189204624, 288939164, 860037497, 1935419406, 1892803452, 2093583086, 380035010, 1521889204, 1091131457, 1028877320, -450003478, -512520416, 348695665, -174162813, 909116497, 1104963481, -1220177694, -2141410326, 1307958965, 459462407, -1179244908, -137440152, 124652504, 2054789320, -1180569357, -1365968445, 2119770975, 871587780, 1440295000, 986645384, -210545155, 540360126, -986697515, -2058197368, 1266404304, -1940411411, -273336015, -1039371902, -269941494, 1157040331, 837961265, 1139814689, -2098192172, -651321556, 1647193625 };
        var median = instance.getMedian(array);
        Assertions.assertEquals(348695665, median);
    }

    @Test
    public void largeRandomArraySorted() {
        var array = new int[] { 314144227, -196011574, 156218098, -123051864, 1894537203, -189204624, 288939164, 860037497, 1935419406, 1892803452, 2093583086, 380035010, 1521889204, 1091131457, 1028877320, -450003478, -512520416, 348695665, -174162813, 909116497, 1104963481, -1220177694, -2141410326, 1307958965, 459462407, -1179244908, -137440152, 124652504, 2054789320, -1180569357, -1365968445, 2119770975, 871587780, 1440295000, 986645384, -210545155, 540360126, -986697515, -2058197368, 1266404304, -1940411411, -273336015, -1039371902, -269941494, 1157040331, 837961265, 1139814689, -2098192172, -651321556, 1647193625 };
        Arrays.sort(array);
        var median = instance.getMedian(array);
        Assertions.assertEquals(348695665, median);
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
