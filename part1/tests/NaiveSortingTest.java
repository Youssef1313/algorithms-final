package part1.tests;

import part1.NaiveSorting;

public final class NaiveSortingTest extends MedianTestBase<NaiveSorting> {
    @Override
    protected NaiveSorting createInstance() {
        return new NaiveSorting();
    }
}
