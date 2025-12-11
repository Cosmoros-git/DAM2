package tema1.ej7;

import tema1.ej7.Objetos.Bateria;
import tema1.ej7.Objetos.Carroceria;
import tema1.ej7.Objetos.Motor;

public class Vehiculo {
    private Motor motor = null;
    private Bateria bateria = null;
    private Carroceria carroceria = null;

    public synchronized void ensamblarMotor(Motor motor) {
        while (carroceria == null) {
            try {
                System.out.println("Error, no se puede ensamblar el motor sin carrocería");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        this.motor = motor;
        System.out.println("Motor ensamblado: " + motor);
    }

    public synchronized void ensamblarBateria(Bateria bateria) {

        while (carroceria == null) {
            try {
                System.out.println("Error, no se puede ensamblar la batería sin carrocería");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.bateria = bateria;
        System.out.println("Bateria ensamblado: " + bateria);
    }

    public synchronized void ensamblarCarroceria(Carroceria carroceria) {
        this.carroceria = carroceria;
        System.out.println("Carroceria ensamblado: " + carroceria);
        notifyAll();
    }

    public Motor getMotor() {
        return motor;
    }

    public Bateria getBateria() {
        return bateria;
    }

    public Carroceria getCarroceria() {
        return carroceria;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "motor=" + motor + ", bateria=" + bateria + ", carroceria=" + carroceria + '}';
    }
}
