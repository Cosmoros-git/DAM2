package tema1.ej2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ej2 {

    public void Start() {
        Scanner sc = new Scanner(System.in);
        int n1;
        while (true) {
            System.out.println("Dame el numero de hilos a generar.");
            n1 = sc.nextInt();
            if (n1 > 0) break;
            else {
                System.out.println("Error: numero no puede ser menor a 1");
            }
        }
        sc.close();
        try{
        generateThreads(n1);}
        catch(InterruptedException e){
            System.out.println("Thread interrupted");
        }
    }

    private void generateThreads(int num) throws InterruptedException{
        for (int i = 0; i < num; i++) {
            var t = new Thread(new RunnableOddRandom(i));
            t.start();
        }
    }

}
