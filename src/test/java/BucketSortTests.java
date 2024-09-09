import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import sorts.BucketSort;
import sorts.ParallelBucketSort;
import functions.CheckSorted;

public class BucketSortTests extends ParentTests {

    @Test
    void testSmall() {

        int[] data = getData(SMALL_SIZE, SMALL_RANGE);
        BucketSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }
    @Test
    void testSmallParallel() {

        int[] data = getData(10000, 100);
//        for(int i: data){
//            System.out.print(i + " ");
//        }
//        System.out.println();
        ParallelBucketSort.sort(data);
        for(int i: data){
            System.out.print(i + " ");
        }
        assertTrue(CheckSorted.check(data));

    }
    @Test
    void testLargeSmall() {
        // Example test
        int[] data = getData(LARGE_SIZE, SMALL_RANGE);
        BucketSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }
    @Test
    void testLargeSmallParallel() {

        int[] data = getData(10, SMALL_RANGE);
        ParallelBucketSort.sort(data);
        assertTrue(CheckSorted.check(data));
    }
    @Test
    void testLargeLarge() {

        int[] data = getData(LARGE_SIZE, LARGE_SIZE);
        BucketSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }
    @Test
    void testLargeLargeParallel() {

        int[] data = getData(LARGE_SIZE, LARGE_SIZE);
        ParallelBucketSort.sort(data);
        assertTrue(CheckSorted.check(data));
    }
}
