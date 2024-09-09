import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import sorts.MergeSort;
import sorts.ParallelMergeSort;
import functions.CheckSorted;

public class MergeSortTests extends ParentTests {

    @Test
    void testSmall() {

        int[] data = getData(1000, 100);
        MergeSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }
    @Test
    void testSmallParallel() {

        int[] data = getData(1000, 100);
        ParallelMergeSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }

    @Test
    void testLarge() {

        int[] data = getData(10000000, 10000);
        MergeSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }
    @Test
    void testLargeParallel() {

        int[] data = getData(LARGE_SIZE, LARGE_SIZE);
        ParallelMergeSort.sort(data);
        assertTrue(CheckSorted.check(data));
    }

}
