package tema1.ej8.BoxFactory;

import tema1.ej8.CoffeCapsule;

import java.util.List;

public class BoxFactory implements Runnable {
    @Override
    public void run() {
        System.out.println("Fabrica de Cajas empezo funcionar");
        while (true) {
            BuildBox();
        }

    }

    private final List<CoffeCapsule> capsulas;

    public BoxFactory(List<CoffeCapsule> capsulas) {
        this.capsulas = capsulas;
    }

    public void BuildBox() {
        synchronized (capsulas) {
            while (capsulas.size() < 6) {
                try {
                    //System.out.println("Insuficiente capsulas: " + capsulas.size());
                    capsulas.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            var capsule = capsulas.getFirst();

            System.out.println("\n========== Caja fabricada con capsula:" + capsule.nombreVariedad + " de intensidad:" + capsule.intensidadCafe+
                    "==========\n");
            capsulas.subList(0, 6).clear();
        }
    }
}
