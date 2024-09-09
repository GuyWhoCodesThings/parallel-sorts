package sorts;
import functions.*;
import java.util.*;

public class BucketSort {

    public static void sort(int[] array){
        int maxValue = FindMaximum.find(array);
        int minValue = FindMinimum.find(array);
        int[] bucketArray = new int[maxValue - minValue + 1];
        for(int val: array){

            bucketArray[val - minValue]++;
        }
        int j = 0;
        for(int i = 0; i < bucketArray.length; i++){
            while(bucketArray[i] > 0) {
                array[j] = i + minValue;
                bucketArray[i]--;
                j++;
            }
        }

    }
}
