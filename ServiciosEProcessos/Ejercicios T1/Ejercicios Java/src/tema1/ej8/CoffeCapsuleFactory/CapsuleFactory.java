package tema1.ej8.CoffeCapsuleFactory;

import tema1.ej8.CoffeCapsule;

import java.util.List;
import java.util.Random;

public class CapsuleFactory implements Runnable {


    @Override
    public void run() {
        while(true){
            synchronized (capsulas){
                var capsulaNueva = new CoffeCapsule(capsula.nombreVariedad,capsula.intensidadCafe);

                capsulas.add(capsulaNueva);
                System.out.println(capsula+" creada, capsulas en almazen: "+capsulas.size());
                capsulas.notify();
            }
            try {
                Thread.sleep(random.nextInt(500,1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public final Random random = new Random();
    public final CoffeCapsule capsula;
    public final List<CoffeCapsule> capsulas;
    public CapsuleFactory(List<CoffeCapsule> capsulas, CoffeCapsule capsula) {
        this.capsulas = capsulas;
        this.capsula = capsula;
    }
}
