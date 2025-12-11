package tema1.ej4;

import java.util.Scanner;

public class ej4 {


    public void Start() {

        Scanner sc = new Scanner(System.in);
        try{


        while(true){
            var counter = new Counter();
            Thread thread = new Thread(counter);
            thread.start();
            sc.nextLine();
            counter.Stop();
            thread.join();
            if(counter.GuessedNumber()) {
                System.out.println("Has detenido el contador correctamente");
                break;};
        }
        }catch(InterruptedException e){
            System.err.println("Thread interrupted");
        }
    }
}
