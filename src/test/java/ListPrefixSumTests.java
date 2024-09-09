import functions.ParallelPrefixSum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ListPrefixSumTests {


    @Test
    void testSmall () {
        int[] data = new int[6];
        data[0] = 1;
        data[2] = 3;
        data[3] = 4;
        data[5] = 2;

        int[] pref = ParallelPrefixSum.prefixSum(data);
        for(int i: pref) {
            System.out.print(i + ", ");
        }
//        [1, 0, 3, 4, 0, 2]
//        [0,2,2,2,3,3,3,3,5,5]


    }
}
