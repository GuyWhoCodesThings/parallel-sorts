package functions;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ParallelPrefixSum {
    public static final ForkJoinPool POOL = new ForkJoinPool();
    private static final int CUTOFF = 15;

    public static int[] prefixSum(int[] input) {
        /* Make the tree */
        ParallelPrefixSum.PSTNode root = POOL.invoke(new ParallelPrefixSum.ProcessInputTask(input, 0, input.length));

        /* Use the tree */
        int[] output = new int[input.length];
        POOL.invoke(new ParallelPrefixSum.CreateOutputTask(input, output, root, 0));

        return output;
    }

    private static class PSTNode {
        public int lo, hi;
        public int sum;
        private ParallelPrefixSum.PSTNode left, right;

        public PSTNode(int sum, int lo, int hi) {
            this.sum = sum;
            this.lo = lo;
            this.hi = hi;
        }

        public PSTNode(int sum, int lo, int hi, ParallelPrefixSum.PSTNode left, ParallelPrefixSum.PSTNode right) {
            this(sum, lo, hi);
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public String toString() {
            return "[" + lo + ", " + hi + ") = " + sum;

        }
    }
    public static int sum(int[] input, int lo, int hi){
        int sum = 0;
        for(int i = lo; i < hi; i++){

            sum += input[i];

        }
        return sum;
    }


    private static class ProcessInputTask extends RecursiveTask<ParallelPrefixSum.PSTNode> {

        private final int lo, hi;
        private final int[] input;
        public ProcessInputTask(int[] input, int lo, int hi) {
            this.input = input;
            this.lo = lo;
            this.hi = hi;
        }

        protected ParallelPrefixSum.PSTNode compute() {
            if(hi - lo <= CUTOFF){
                return new PSTNode(sum(input, lo, hi), lo, hi);
            }
            int mid = lo + (hi - lo) / 2;
            ProcessInputTask leftTask = new ProcessInputTask(input, lo, mid);
            ProcessInputTask rightTask = new ProcessInputTask(input, mid, hi);
            leftTask.fork();
            PSTNode right = rightTask.compute();
            PSTNode left = leftTask.join();
            return new PSTNode(left.sum + right.sum, lo, hi, left, right);
        }
    }

    public static class CreateOutputTask extends RecursiveAction {

        private final int[] input;
        private final int[] output;
        private final PSTNode current;
        private final int prescan;
        public CreateOutputTask(int[] input, int[] output, PSTNode current, int prescan) {
            this.input = input;
            this.current = current;
            this.prescan = prescan;
            this.output = output;
        }

        protected void compute() {
            if(current.isLeaf()){
                int sum = prescan;
                for(int i = current.lo; i < current.hi; i++){

                    sum += input[i];

                    output[i] = sum;

                }
            } else {
                CreateOutputTask leftTask = new CreateOutputTask(input, output, current.left, prescan);
                CreateOutputTask rightTask = new CreateOutputTask(input, output, current.right, prescan + current.left.sum);
                leftTask.fork();
                leftTask.join();
                rightTask.compute();
            }

        }
    }
}
