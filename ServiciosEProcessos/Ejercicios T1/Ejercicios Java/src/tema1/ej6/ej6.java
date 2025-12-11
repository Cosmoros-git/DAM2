package tema1.ej6;
import java.util.ArrayList;
import java.util.List;


public class ej6 {

    public void Start() {

        Log l = new Log();
        List<Thread> threads = new ArrayList<>();
        System.out.println("Iniciando ejercicio 6 threads. ");
        for(int i = 1; i <= 5; i++){
            Thread t = new Thread(new Escritor(i, l));
            t.start();
            threads.add(t);
        }

        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hilos finalizados");
        l.cerrar();
    }
}
