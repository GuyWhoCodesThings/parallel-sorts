package functions;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindMinimum {
    private static final ForkJoinPool POOL = new ForkJoinPool();
    private static final int CUTOFF = 25;

    public static int find(int[] array){
        return POOL.invoke(new FindMaxTask(array, 0, array.length));
    }

    private static class FindMaxTask extends RecursiveTask<Integer> {
        int lo, hi;
        int[] array;
        public FindMaxTask(int[] array, int lo, int hi){
            this.lo = lo;
            this.hi = hi;
            this.array = array;
        }
        protected Integer compute(){
            if(hi - lo <= FindMinimum.CUTOFF){
                int minimum = Integer.MAX_VALUE;
                for(int i = lo; i < hi; i++){
                    minimum = Math.min(array[i], minimum);
                }
                return minimum;
            }
            int mid = lo + (hi - lo) / 2;
            FindMaxTask left = new FindMaxTask(array, lo, mid);
            FindMaxTask right = new FindMaxTask(array, mid, hi);
            left.fork();
            return Math.min(right.compute(), left.join());
        }

    }
}

