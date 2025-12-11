package tema1;

import java.util.List;
import java.util.Random;

public class T1StaticMethods {

    public static void RunWithRandomLatency(int a, int b, List<Integer> list) throws InterruptedException {
        var index = 0;
        int step = a<b ? 1 : -1; // Count up or down

        for (int i = a; a < b ? i <= b : i >= b; i += step ) {
            System.out.println(i);
            Thread.sleep(list.get(index));
            index++;
        }

        /*if (num1IsSmaller) {
            // Count up: a → b
            for (int i = a; i <= b; i++) {
                System.out.println(i);
                Thread.sleep(list.get(temp));
                temp++;
            }
        }else{
            // Count down: a → b (a > b)
            for (int i = a; i >= b; i--) {
                System.out.println(i);
                Thread.sleep(list.get(temp));
                temp++;
            }
        }
        Old implementation before I got shown the way.
        */
        System.out.println("Timings list, for debug:" +list.toString());
    }

    public static void PrepareRandomListEj1(int num1, int num2, List<Integer> randomTiming){
        var random = new Random();
        int count = Math.abs(num1 - num2);
        for  (int i = 0; i<=count; i++ ) {
            randomTiming.add(random.nextInt(1, 1000));
        }
    }

    public static void PrepareRandomTimings(int amount, int min, int max, Random random, List<Integer> randomTiming){
        for  (int i = 0; i<=amount; i++ ) {
            randomTiming.add(random.nextInt(min, max));
        }
    }

}
