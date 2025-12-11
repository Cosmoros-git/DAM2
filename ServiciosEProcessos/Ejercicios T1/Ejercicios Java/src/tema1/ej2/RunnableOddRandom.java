package tema1.ej2;

import staticMethods.GlobalStaticMethods;
import tema1.T1StaticMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RunnableOddRandom implements Runnable {
    String threadName;

    public RunnableOddRandom(int threadNumber) {
        this.threadName = "Hilo " + threadNumber;
    }
    public String GetThreadName() {
        return threadName;
    }

    @Override
    public void run() {
        try {
        Random r = new Random();
        var limit = r.nextInt(1,101);


        List<Integer> primes = GlobalStaticMethods.arrayPrimesEratosthenes(limit); // Gets me all primes in a list
        System.out.println("Primos sacados del hilo "+threadName+" totales: "+primes.size()+" hasta limite:" + limit);

        List<Integer> delays = new ArrayList<>();
        T1StaticMethods.PrepareRandomTimings(primes.size(),500,1000,r,delays);
        // Creates array of random delays at once


            showNumbers(primes,delays, limit);
        }catch(InterruptedException e){
            System.out.println("Thread interrupted");
        }
    }

    private void showNumbers(List<Integer> primes, List<Integer> delays, int limit) throws InterruptedException {
        System.out.println(threadName + ": Numero de primos encontrados " + primes.size() +
                " hasta limite generado: "+limit);


        for (int i = 0; i < primes.size(); i++) {
            int prime = primes.get(i);
            int delay = delays.get(i);

            System.out.println(threadName + ": Primo es " + prime);
            Thread.sleep(delay);
        }
        System.out.println(threadName + " Finalizo el trabajo\n" +
                threadName + ", todos primos: " + primes + "\n" +
                threadName + ", todos delays: " + delays);


    }
}
