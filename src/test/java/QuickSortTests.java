import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import sorts.QuickSort;
import sorts.ParallelQuickSort;
import functions.CheckSorted;

public class QuickSortTests extends ParentTests {

    @Test
    void testSmall() {

        int[] data = getData(16, 100);
        QuickSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }
    @Test
    void testSmallParallel() {

        int[] data = getData(SMALL_SIZE, SMALL_RANGE);
        ParallelQuickSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }

    @Test
    void testLarge() {

        int[] data = getData(10000000, 10000);
        QuickSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }
    @Test
    void testLargeParallel() {

        int[] data = getData(LARGE_SIZE, LARGE_SIZE);
        ParallelQuickSort.sort(data);
        assertTrue(CheckSorted.check(data));
    }

}
