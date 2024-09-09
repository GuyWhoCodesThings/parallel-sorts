package sorts;


import functions.*;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelBucketSort {

    /*
    Algorithm has 2 main parts:
    1) put items into correct buckets in parallel (easier to do)
    2) place items back into original array in parallel (harder to do)

    The issue I encountered was the buckets varied in size, so just knowing the position of bucket
    was not enough. To overcome this, I first did a parallel prefix sum of the bucket lengths to
    get the starting index of where each bucket would go to when placing back into the original array
     */

    private static final ForkJoinPool POOL = new ForkJoinPool();
    private static final int CUTOFF = 15;

    public static void sort(int[] array){
        // to minimize # of buckets
//        int maximum = FindMaximum.find(array);
//        System.out.println("maximum: " + maximum);
//        int minimum = FindMinimum.find(array);
//        System.out.println("minimum: " + minimum);
//        int[] bucketArray = new int[maximum - minimum + 1];
        int[] bucketArray = new int[array.length];
        // locks to be safe when putting items into buckets,
        // if two threads try to create an arraylist at same index at same time, could cause issue
        POOL.invoke(new BucketTask(bucketArray, array, 0, array.length, 0));
//        for(int b: bucketArray){
//            System.out.print(b + " ");
//        }
//        System.out.println("");
        // normal prefix sum instead
        int[] prefixSum = ParallelPrefixSum.prefixSum(bucketArray);
//        for(int b: prefixSum){
//            System.out.print(b + " ");
//        }
//        System.out.println("");
        // sorted array
        POOL.invoke(new PlaceTask(array, prefixSum, bucketArray, 0, bucketArray.length, 0));

    }



    private static class BucketTask extends RecursiveAction {
        int lo, hi, minimum;
        int[] array;
        int[] bucketArray;

        public BucketTask(int[] bucketArray, int[] array, int lo, int hi, int minimum){
            this.lo = lo;
            this.hi = hi;
            this.array = array;
            this.minimum = minimum;
            this.bucketArray = bucketArray;
        }
        protected void compute() {
            if (hi - lo <= CUTOFF) {
                for (int i = lo; i < hi; i++) {
                    bucketArray[array[i]]++;
                }
            } else {
                // Divide the array into two halves
                int mid = lo + (hi - lo) / 2;
                BucketTask left = new BucketTask(bucketArray, array, lo, mid, minimum);
                BucketTask right = new BucketTask(bucketArray, array, mid, hi, minimum);

                left.fork();
                right.compute();
                left.join();
            }
        }

    }
    private static class PlaceTask extends RecursiveAction {
        int lo, hi, minimum;
        int[] array, prefixSizeSum;
        int[] bucketArray;

        public PlaceTask(int[] array, int[] prefixSizeSum, int[] bucketArray, int lo, int hi, int minimum){
            this.array = array;
            this.prefixSizeSum = prefixSizeSum;
            this.bucketArray = bucketArray;
            this.lo = lo;
            this.hi = hi;
            this.minimum = minimum;

        }

        protected void compute() {

            if (hi - lo <= CUTOFF) {

                for(int i = lo; i < hi; i++){
                    int j = 0;
                    while(bucketArray[i] > 0) {
                        array[prefixSizeSum[i] - 1 - j] = i;
                        bucketArray[i]--;
                        j++;
                    }
                }
            } else {
                // Divide the array into two halves
                int mid = lo + (hi - lo) / 2;
                PlaceTask left = new PlaceTask(array, prefixSizeSum, bucketArray, lo, mid, minimum);
                PlaceTask right = new PlaceTask(array, prefixSizeSum, bucketArray, mid, hi, minimum);
                left.fork();
                right.compute();
                left.join();

            }
        }

}}

