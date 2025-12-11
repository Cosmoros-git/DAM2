package tema1.ej8;

import tema1.ej8.BoxFactory.BoxFactory;
import tema1.ej8.BoxFactory.BoxFactoryTypeB;
import tema1.ej8.BoxFactory.BoxFactoryTypeD;
import tema1.ej8.CoffeCapsuleFactory.CapsuleFactory;
import tema1.ej8.CoffeCapsuleFactory.CapsuleFactoryTypeC;

import java.util.ArrayList;
import java.util.Scanner;

import static staticMethods.GlobalStaticMethods.exerciseVariant;

public class ej8 {

        // I cant sent my mind if use spanish for variables, or english. VEry annoying when intelj is running on eng loc.
    public final String CAFE_NOMBRE = "Americano";
    public final int CAFE_INTENSIDAD = 5;
    public final CoffeCapsule capsula = new CoffeCapsule(CAFE_NOMBRE, CAFE_INTENSIDAD);
    public final ArrayList<CoffeCapsule> capsulaList = new ArrayList<>();
    public final Object lock = new Object();

    private final int MIN_TASK_NUMBER = 1;
    private final int MAX_TASK_TYPES = 4;

    public void Start() {

        System.out.println("Dame numero de variante de ejercicio\n" + "Sin delay, tipo 8 [1]\n" + "Con delay, tipo 8b [2]\n" + "Con multiplos hilos productores, tipo 8c [3]\n" + "Con limite de 100 unidades, tipo 8d [4]\n");
        Scanner sc = new Scanner(System.in);
        int type = exerciseVariant(sc, MIN_TASK_NUMBER, MAX_TASK_TYPES);


        startCapsuleFactory(type);
        var fabricaCaja = new BoxFactory(capsulaList);
        startBoxFactory(type, fabricaCaja);
    }

    private void startCapsuleFactory(int type) {
        switch (type) {
            case 1, 2:
                var fabricaCapsula = new CapsuleFactory(capsulaList, capsula);
                var fabricaCapsulaThread = new Thread(fabricaCapsula);
                fabricaCapsulaThread.start();
                break;
            case 3:
                for (int i = 0; i < 4; i++) { // Type 8c. 4 Threads start
                    var fabricaCapsulaMany = new CapsuleFactory(capsulaList, capsula);
                    var fabricaCapsulaThreadMany = new Thread(fabricaCapsulaMany);
                    fabricaCapsulaThreadMany.start();

                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) { // Type 8d. Limited to 100 units
                    var fabricaCapsulaManyCapped = new CapsuleFactoryTypeC(capsulaList, capsula, lock);
                    var fabricaCapsulaThreadManyCapped = new Thread(fabricaCapsulaManyCapped);
                    fabricaCapsulaThreadManyCapped.start();

                }
                break;
        }
    }

    private void startBoxFactory(int type, BoxFactory fabricaCaja) {
        switch (type) {
            case 1: // Sin delay
                var fabricaCajaThread = new Thread(fabricaCaja);
                fabricaCajaThread.start();
                break;
            case 2, 3: // Con delay
                var fabricaCajaMod = new BoxFactoryTypeB(fabricaCaja);
                var fabricaCajaModThread = new Thread(fabricaCajaMod);
                fabricaCajaModThread.start();
                break;
            case 4: // Con limite
                var fabricaCajaModB = new BoxFactoryTypeB(fabricaCaja);
                var fabricaCajaModC = new BoxFactoryTypeD(fabricaCajaModB, lock);
                var fabricaCajaModCThread = new Thread(fabricaCajaModC);
                fabricaCajaModCThread.start();
                break;
        }
    }

}
