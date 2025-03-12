import java.nio.file.WatchEvent;
import java.util.Arrays;
import java.util.Random;

public class ArrayMethods {

    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";

    public static float randomPower() {
        Random r = new Random();
        float fNumber = r.nextFloat(0, 101);
        return fNumber;
    }

    public static String[] firstArray() throws ArrayIndexOutOfBoundsException {
        String[] racesWarhammer = new String[5];
        racesWarhammer[0] = "Orc";
        racesWarhammer[1] = "Eldar";
        racesWarhammer[2] = null;
        racesWarhammer[3] = "Tyranids";
        racesWarhammer[4] = "Necrons";

        return racesWarhammer;
    }

    public static String[] secondArray() throws ArrayIndexOutOfBoundsException {
        String[] racesWarhammer = {"Orc" , "Eldar" , "Tyranids" , "Necrons"};
        return racesWarhammer;
    }

    public static void showArray(String[] arr) {
        Arrays.sort(arr);
        for (String item : arr) {
            System.out.println(String.format("Warhammer Race: %s", YELLOW+item+RESET));
        }
    }


    public static void fillRandomArray() {
        float[] weapons = new float[5];
        for (int c = 0; c < weapons.length; c++){
            weapons[c] = randomPower();
            System.out.println(weapons[c]);
        }
    }



    public static void main(String[] args) {
        showArray(secondArray());
        fillRandomArray();
    }
    
}
