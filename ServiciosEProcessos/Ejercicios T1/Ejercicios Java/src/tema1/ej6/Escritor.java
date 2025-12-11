package tema1.ej6;

import java.util.Random;

public class Escritor implements Runnable {

    final Log log;
    int id;


    public Escritor(int id, Log log) {
        this.log = log;
        this.id = id;
    }


    // Fix 2. Using object lock with Id. Can be any other object passed into escritor.
    @Override
    public void run() {
        Random r = new Random();
        // Escribimos en el log 100 mensajes
        for (int i = 1; i <= 100; i++) {
            try {
                synchronized (log) {
                    log.escribir(id, "Este es mi mensaje número " + i);
                }
                Thread.sleep(r.nextInt(0, 11)); //Haciendo esperas entre 0 y 10 milisegunods
                // If sleep is inside block log is more orderly? Less mixed up.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
