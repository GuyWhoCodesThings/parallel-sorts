package functions;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CheckSorted {

    private static final ForkJoinPool POOL = new ForkJoinPool();
    private static final int CUTOFF = 5;

    public static boolean check(int[] array){
        return POOL.invoke(new CheckSortedTask(array, 0, array.length));
    }

    private static class CheckSortedTask extends RecursiveTask<Boolean> {
        int lo, hi;
        int[] array;
        public CheckSortedTask(int[] array, int lo, int hi){
            this.lo = lo;
            this.hi = hi;
            this.array = array;
        }
        protected Boolean compute(){

            if(hi - lo <= CheckSorted.CUTOFF){

                for(int i = lo; i < hi - 1; i++){
                    if (array[i] > array[i + 1]) return false;
                }
                return true;
            }

            int mid = lo + (hi - lo) / 2;
            CheckSortedTask left = new CheckSortedTask(array, lo, mid + 1);
            CheckSortedTask right = new CheckSortedTask(array, mid, hi);
            left.fork();
            return right.compute() && left.join();
        }

    }
}
