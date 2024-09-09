package sorts;

public class InsertionSort {
    public static void sort(int[] array){
        insertionSort(array, 0, array.length);
    }
    public static void insertionSort(int[] array, int lo, int hi){
        int i, j, key;
        for(i = lo + 1; i < hi; i++){
            key = array[i];
            j = i - 1;
            while(j >= lo && array[j] > key){
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
