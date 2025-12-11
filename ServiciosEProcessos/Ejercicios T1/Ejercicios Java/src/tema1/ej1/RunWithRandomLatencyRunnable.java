package tema1.ej1;

import tema1.T1StaticMethods;

import java.util.List;

public class RunWithRandomLatencyRunnable implements Runnable {

    private final int a;
    private final int b;
    private final List<Integer> list;



    public RunWithRandomLatencyRunnable(int a, int b, List<Integer> list) {
        this.a = a;
        this.b = b;
        this.list = list;
    }


    @Override
        public void run() {
        try {
            RunWithRandomLatency(a,b,list);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void RunWithRandomLatency(int a, int b, List<Integer> list) throws InterruptedException {
        T1StaticMethods.RunWithRandomLatency(a, b, list);
    }
}
