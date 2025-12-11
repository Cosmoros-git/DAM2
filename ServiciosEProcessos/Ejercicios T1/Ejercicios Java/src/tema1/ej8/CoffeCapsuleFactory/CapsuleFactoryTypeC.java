package tema1.ej8.CoffeCapsuleFactory;

import tema1.ej8.CoffeCapsule;

import java.util.List;
import java.util.Random;

public class CapsuleFactoryTypeC implements Runnable {

    private final Object lock;
    public final Random random = new Random();
    public final CoffeCapsule capsula;
    public final List<CoffeCapsule> capsulas;


    public CapsuleFactoryTypeC(List<CoffeCapsule> capsulas, CoffeCapsule capsula, Object lock) {
        this.lock = lock;
        this.capsulas = capsulas;
        this.capsula = capsula;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (capsulas){
                var capsulaNueva = new CoffeCapsule(capsula.nombreVariedad,capsula.intensidadCafe);

                capsulas.add(capsulaNueva);

                System.out.println(capsula+" creada, capsulas en almazen: "+capsulas.size());
                capsulas.notify();
            }
            synchronized (lock){
                if(capsulas.size()>=100){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try {
                Thread.sleep(random.nextInt(500,1000));

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}


