import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import sorts.InsertionSort;
import functions.CheckSorted;

public class InsertionSortTests extends ParentTests {

    @Test
    void testSmall() {

        int[] data = getData(15, 10);
        InsertionSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }


    @Test
    void testMedium() {

        int[] data = getData(100, 100);
        InsertionSort.sort(data);
        assertTrue(CheckSorted.check(data));

    }
}
