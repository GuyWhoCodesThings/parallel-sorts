import java.util.Random;

public class ParentTests {

    public static int SMALL_SIZE = 1000;
    public static int SMALL_RANGE = 100;

    public static final int LARGE_SIZE = 100000000;
    public static int LARGE_RANGE = 1000000;



    public int[] getData(int size, int range) {
        Random random = new Random(0);
        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(range);  // fill with int 0 - range
        }
        return data;
    }
    public int[] getSortedArray(int size){
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {

            data[i] = i;
        }
        return data;
    }
    public int[] getUnsortedArray(int size){
        if (size < 10) {
            throw new IllegalArgumentException("size must be greater than 10");
        }
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i % 8;
        }
        return data;
    }
}
