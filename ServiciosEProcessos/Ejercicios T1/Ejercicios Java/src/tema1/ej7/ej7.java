package tema1.ej7;

public class ej7 {
    public void Start() {
        Vehiculo v = new Vehiculo();

        Thread fm = new Thread(new FabricarMotor(v));
        Thread fb = new Thread(new FabricarBateria(v));
        Thread fc = new Thread(new FabricarCarroceria(v));

        fm.start();
        fb.start();
        fc.start();

    }
}
