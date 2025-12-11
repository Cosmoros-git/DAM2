package tema1.ej1;

import tema1.T1StaticMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static staticMethods.GlobalStaticMethods.exerciseVariant;

public class ej1 {

    public void Start() {
        System.out.println("Dame numero de variante de ejercicio," +
                " Runnable [1]," +
                " Clase anonima [2]," +
                " Expresion Lambda [3]. ");
        Scanner sc = new Scanner(System.in);


        int max = 3; int min = 1;


        int type = exerciseVariant(sc, min, max);
        int num1; int num2;


        while (true) {
            System.out.println("Dame 2 numeros");
            num1 = sc.nextInt();
            num2 = sc.nextInt();
            if(num1==num2){
                System.out.println("El numero es igual");
            }else{
                break;
            }
        }


        boolean num1IsSmaller = num1 < num2;
        List<Integer> randomTiming = new ArrayList<>();
        T1StaticMethods.PrepareRandomListEj1(num1, num2, randomTiming);

        int finalNum = num1;
        int finalNum1 = num2;

        try {
            switch(type){
                case 1: // Runnable
                    RunWithRandomLatencyRunnable runnable = new RunWithRandomLatencyRunnable(num1, num2, randomTiming);
                    runnable.run();
                    break;

                case 2: // Clase anonima

                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                T1StaticMethods.RunWithRandomLatency(finalNum, finalNum1, randomTiming);
                            } catch (InterruptedException e) {
                                System.out.println("Hilo interrumpido");;
                            }
                        }
                    });
                    t.start();
                    t.join();
                    break;

                case 3: // Clase lambda.
                        Thread b = new Thread(() -> {
                            try {
                                T1StaticMethods.RunWithRandomLatency(finalNum, finalNum1, randomTiming);
                            } catch (InterruptedException e) {
                                System.out.println("Hilo interrumpido");;
                            }
                        });
                        b.start();
                        b.join();
                    break;

            }
        }catch (InterruptedException e){
            System.out.println("Hilo interrumpido");
            Thread.currentThread().interrupt();
        } finally{
            sc.close();
        }
    }




}
