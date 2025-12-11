package tema1.ej8.BoxFactory;

import java.util.Random;

public class BoxFactoryTypeD implements Runnable {
    @Override
    public void run() {
        System.out.println("Fabrica de Cajas empezo funcionar");
        while (true) {
            fabricaCaja.BuildBoxB();
            synchronized (lock){
                lock.notifyAll();
            }

        }
    }


    private final Random random = new Random();
    private final BoxFactoryTypeB fabricaCaja;
    private final Object lock;

    public BoxFactoryTypeD(BoxFactoryTypeB fabricaCaja, Object lock) {
        this.fabricaCaja= fabricaCaja;
        this.lock = lock;
    }
}
