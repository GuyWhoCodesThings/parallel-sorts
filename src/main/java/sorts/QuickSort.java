package sorts;
import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] array){
        quickSort(array, 0, array.length - 1);
    }
    private static void quickSort(int[] array, int lo, int hi){
        if(lo < hi){
            int pivot = partition(array, lo, hi);
            quickSort(array, lo, pivot - 1);
            quickSort(array, pivot + 1, hi);
        }
    }
    public static int partition(int[] array, int lo, int hi){

        int randIndex = lo + rand.nextInt(hi - lo + 1);
        swap(array, randIndex, hi);
        int pivot = array[hi];
        int i = lo;
        for(int j = lo; j < hi; j++){
            if(array[j] < pivot){

                swap(array, i, j);
                i++;
            }
        }
        swap(array, hi, i);
        return i;
    }
    private static void swap(int[] array, int a, int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
