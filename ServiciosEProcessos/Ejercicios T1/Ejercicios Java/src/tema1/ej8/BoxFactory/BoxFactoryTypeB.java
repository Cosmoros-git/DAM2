package tema1.ej8.BoxFactory;

import java.util.Random;

public class BoxFactoryTypeB implements Runnable {
    @Override
    public void run() {
        System.out.println("========== Fabrica de Cajas empezo funcionar ==========");
        while (true) {
            BuildBoxB();
        }
    }


    private final Random random = new Random();
    private final BoxFactory fabricaCaja;

    public BoxFactoryTypeB(BoxFactory fabricaCaja) {
        this.fabricaCaja= fabricaCaja;
    }

    public void BuildBoxB(){
        fabricaCaja.BuildBox();
        try {
            System.err.println("\n========== Fabrica de Cajas offline ==========\n");
            Thread.sleep(random.nextInt(300,1000)); // Con 5s se nota mas que no funciona todo tiempo.
            System.out.println("\n========== Fabrica de Cajas online de nuevo ==========\n");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
