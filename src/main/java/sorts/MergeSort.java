package sorts;

public class MergeSort {

    public static void sort(int[] array){

        mergeSort(array, 0, array.length);

    }
    public static void mergeSort(int[] array, int lo, int hi){

        if(hi - lo <= 1){
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(array, lo, mid);
        mergeSort(array, mid, hi);
        merge(array, lo, mid, hi);

    }
    public static void merge(int[] array, int lo, int mid, int hi){

        int[] left = new int[mid - lo];
        int[] right = new int[hi - mid];

        for(int i = lo; i < mid; i++){
            left[i - lo] = array[i];
        }
        for(int i = mid; i < hi; i++){
            right[i - mid] = array[i];
        }

        int lPointer = 0;
        int rPointer = 0;
        int mergedPointer = lo;
        while(lPointer < left.length || rPointer < right.length){
            if(rPointer == right.length){
                array[mergedPointer] = left[lPointer];
                lPointer++;
            }
            else if(lPointer == left.length){
                array[mergedPointer] = right[rPointer];
                rPointer++;
            }
            else if(left[lPointer] < right[rPointer]){
                array[mergedPointer] = left[lPointer];
                lPointer++;
            } else {
                array[mergedPointer] = right[rPointer];
                rPointer++;
            }
            mergedPointer++;
        }
    }
}

