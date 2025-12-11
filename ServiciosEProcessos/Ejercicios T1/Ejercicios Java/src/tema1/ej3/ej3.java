package tema1.ej3;

import tema1.ej2.RunnableOddRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ej3 {

    List<Thread> threadlist = new ArrayList<>();

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
        generateThreads(n1);
        threadState();
        }
        catch(InterruptedException e){
            System.out.println("Thread interrupted");
        }
    }

    private void generateThreads(int num) throws InterruptedException{
        for (int i = 0; i < num; i++) {
            Runnable task = new RunnableOddRandom(i);
            var t = new Thread(task, String.valueOf(i));
            threadlist.add(t);
            t.start();
        }
    }

    private void threadState() throws InterruptedException {
        Thread r = new Thread(() -> {
            try {
                while(!threadlist.isEmpty()){
                    Thread.sleep(3000);

                    System.out.println("\n=== Estado actual ===");

                    // No es muy bueno cambiar listado que esta iternado
                    Iterator<Thread> iterator = threadlist.iterator();
                    while(iterator.hasNext()){
                        Thread t = iterator.next();
                        System.out.println(t.getName() + " estado " + t.getState());

                        if(t.getState() == Thread.State.TERMINATED){
                            System.out.println(t.getName() + " esta terminado, eliminando de monitor");
                            iterator.remove();
                        }
                    }
                }



            } catch (InterruptedException e) {
               System.out.println("Hilo interumpido");
            }

        });
        r.start();
    }

}
