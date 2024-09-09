import org.junit.jupiter.api.Test;

import functions.CheckSorted;

import static org.junit.jupiter.api.Assertions.*;

public class CheckSortedTests extends ParentTests {
    @Test
    void testSmall() {

        int[] sortedData = getSortedArray(20);
        int[] unsortedData = getUnsortedArray(20);

        assertTrue(CheckSorted.check(sortedData));
        assertFalse(CheckSorted.check(unsortedData));
    }
    @Test
    void testMedium() {

        int[] sortedData = getSortedArray(10000);
        int[] unsortedData = getUnsortedArray(10000);
        assertTrue(CheckSorted.check(sortedData));
        assertFalse(CheckSorted.check(unsortedData));
    }
    @Test
    void testLarge() {

        int[] sortedData = getSortedArray(100000000);
        int[] unsortedData = getUnsortedArray(100000000);
        assertTrue(CheckSorted.check(sortedData));
        assertFalse(CheckSorted.check(unsortedData));
    }
}
