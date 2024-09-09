package comparisons;

import sorts.MergeSort;
import sorts.ParallelMergeSort;
import functions.CheckSorted;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SortingTimeToCSV {

    public static void main(String[] args) {

        int[] sizes = getSizes(50, 10000);
        final int MAX = 100000;
        final int TRIALS = 10;

        String csvFile = "merge-sort-times.csv";
        int i = 0;
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append("Elements, Seq, Par\n");
            for (int size: sizes) {
                int[] data = getData(size, MAX);

                long startTime = System.currentTimeMillis();
                for (int j = 0; j < TRIALS; j++) {
                    int[] dataCopy = Arrays.copyOf(data, data.length);
                    MergeSort.sort(dataCopy);
                    if (!CheckSorted.check(dataCopy)) {
                        System.out.println("Seq array not sorted correctly!");
                    }
                }
                long sequentialTime = (System.currentTimeMillis() - startTime) / (long)TRIALS;

                startTime = System.currentTimeMillis();
                for (int j = 0; j < TRIALS; j++) {
                    int[] dataCopy = Arrays.copyOf(data, data.length);
                    ParallelMergeSort.sort(dataCopy);
                    if (!CheckSorted.check(dataCopy)) {
                        System.out.println("Seq array not sorted correctly!");
                    }
                }
                long parallelTime = (System.currentTimeMillis() - startTime) / (long)TRIALS;

                writer.append(String.valueOf(size));
                writer.append(", ");
                writer.append(String.valueOf(sequentialTime));
                writer.append(", ");
                writer.append(String.valueOf(parallelTime));
                writer.append("\n");
                i++;
                System.out.println(i + "/" + sizes.length);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int[] getData(int size, int max) {
        int[] data = new int[size];
        for (int i = 100; i < size; i++) {
            data[i] = (int) (Math.random() * max);
        }
        return data;
    }
    private static int[] getSizes(int length, int stepSize){

        int[] sizes = new int[length];
        int currentSize = 0;
        for (int i = 0; i < length; i++) {
            sizes[i] = currentSize;
            currentSize += stepSize;
        }
        return sizes;
    }

}