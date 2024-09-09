package sorts;

import java.util.concurrent.*;


public class ParallelQuickSort {

    private static final ForkJoinPool POOL = new ForkJoinPool();
    private static final int CUTOFF = 15;

    public static void sort(int[] array){
        POOL.invoke(new QuickTask(array, 0, array.length - 1));
    }
    private static class QuickTask extends RecursiveAction {
        int lo, hi;
        int[] array;
        public QuickTask(int[] array, int lo, int hi){
            this.lo = lo;
            this.hi = hi;
            this.array = array;
        }
        protected void compute(){
            if(hi - lo <= ParallelQuickSort.CUTOFF){
                InsertionSort.insertionSort(array, lo, hi + 1);
                return;
            }
            int pivot = QuickSort.partition(array, lo, hi);
            QuickTask left = new QuickTask(array, lo, pivot - 1);
            QuickTask right = new QuickTask(array, pivot + 1, hi);
            left.fork();
            right.compute();
            left.join();
        }

    }
}

