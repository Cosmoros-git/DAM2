package tema1.ej4;

import java.util.Random;

public class Counter implements Runnable {


    private int count;
    private final int randomNum;
    public Counter() {
        Random random = new Random();
        randomNum = random.nextInt(10,20);
    }

    private boolean Stopped = false;
    public void Stop() {
        Stopped = true;
    }

    public Boolean GuessedNumber() {
        return count == randomNum;
    }

    @Override
    public void run() {
        System.out.println("Pulsa enter cuando creas que el contador ha llegado a "+randomNum);
        try {
            startCount();
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }

    public void startCount() throws InterruptedException {
        while (!Stopped){
            if(count == randomNum){
                System.out.println("Has fallado en detener contador al tiempo");
                Thread.sleep(1000);
                count=0;
            }
            if(count <= 5){
                System.out.println(count);
            }

            count++;
            Thread.sleep(1000);
        }
        System.out.println("Contador detenido en: "+count+"/"+randomNum);

    }

}
