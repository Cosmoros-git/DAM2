import tema1.*;
import tema1.ej1.ej1;
import tema1.ej10.ej10;
import tema1.ej2.ej2;
import tema1.ej3.ej3;
import tema1.ej4.ej4;
import tema1.ej5.ej5;
import tema1.ej6.ej6;
import tema1.ej7.ej7;
import tema1.ej8.ej8;
import tema1.ej9.ej9;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final int MAX_TASK_NUMBER = 10;


    public static void main(String[] args) {
        System.out.println("Que ejercicio a correr, da numero.");
        Scanner sc = new Scanner(System.in);


        var num = exerciseToRun();

        switch (num) {
            case 1:
                new ej1().Start();
                break;
            case 2:
                new ej2().Start();
                break;
            case 3:
                new ej3().Start();
            case 4:
                new ej4().Start();
                break;
            case 5:
                new ej5().Start();
                break;
            case 6:
                new ej6().Start();
                break;
            case 7:
                new ej7().Start();
                break;
            case 8:
                new ej8().Start();
                break;
            case 9:
                new ej9().Start();
                break;
            case 10:
                new ej10().Start();
                break;
        }
    }

    public static int exerciseToRun() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                var num = sc.nextInt();
                if (num > MAX_TASK_NUMBER) {
                    System.out.println("No hay ejercicio con este numero, el maximo es: " + MAX_TASK_NUMBER);
                } else {
                    return num;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input invalido. Prueba de nuevo.");
            }
        }
    }
}