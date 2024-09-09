package functions;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class FindMaximum {
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
            if(hi - lo <= FindMaximum.CUTOFF){
                int maximum = Integer.MIN_VALUE;
                for(int i = lo; i < hi; i++){
                    maximum = Math.max(array[i], maximum);
                }
                return maximum;
            }
            int mid = lo + (hi - lo) / 2;
            FindMaxTask left = new FindMaxTask(array, lo, mid);
            FindMaxTask right = new FindMaxTask(array, mid, hi);
            left.fork();
            return Math.max(right.compute(), left.join());
        }

    }
}
