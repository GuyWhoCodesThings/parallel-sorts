package sorts;

import java.util.concurrent.*;


public class ParallelMergeSort {
    private static final ForkJoinPool POOL = new ForkJoinPool();
    private static final int CUTOFF = 1;
    public static void sort(int[] array){
        POOL.invoke(new MergeSortTask(array, 0, array.length));
    }

    private static class MergeSortTask extends RecursiveAction {
        int lo, hi;
        int[] array;
        public MergeSortTask(int[] array, int lo, int hi){
            this.lo = lo;
            this.hi = hi;
            this.array = array;
        }
        protected void compute(){
            if(hi - lo <= ParallelMergeSort.CUTOFF){
                InsertionSort.insertionSort(array, lo, hi);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            MergeSortTask left = new MergeSortTask(array, lo, mid);
            MergeSortTask right = new MergeSortTask(array, mid, hi);
            left.fork();
            right.compute();
            left.join();
            MergeSort.merge(array, lo, mid, hi);

        }

    }
}
