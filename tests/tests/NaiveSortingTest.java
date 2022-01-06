package tests;

import median.NaiveSorting;

public final class NaiveSortingTest extends MedianTestBase<NaiveSorting> {
    @Override
    protected NaiveSorting createInstance() {
        return new NaiveSorting();
    }
}
