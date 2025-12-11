package tema1.ej9;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ej9 {


    public static final int MAX_DICE_VALUE = 10;
    private boolean exit;

    public void Start() {
        try (ExecutorService exec = Executors.newFixedThreadPool(2)) {
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("Cuantos dados a tirar (o 'salir' para terminar):");

                String input = sc.nextLine().trim().toLowerCase();
                if (input.equals("salir")) {
                    exit = true;
                    break;
                }
                int num = validateDiceInput(input);
                if (num <= 0) continue;

                Dice[] dices = new Dice[num];
                for (int i = 0; i < num; i++) {
                    while (true) {
                        System.out.printf("Que resultado quieres para el dado %d/%d (1-%d): ", i+1, num, MAX_DICE_VALUE);
                        try {
                            var temp = sc.nextLine().trim().toLowerCase();
                            if (temp.equals("salir")) {
                                return;
                            }
                            var diceNum = validateDiceInputInRange(temp);
                            if (diceNum > 0) {
                                dices[i] = new Dice(i, diceNum);
                                break;
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("No es valido numero, prueba de nuevo");
                        }

                    }
                }

                var task = new DiceThrower(dices);
                exec.execute(task);

                System.out.println("Partida iniciada. Puedes iniciar otra o escribir 'salir'");

            } while (!exit);
            exec.shutdown();
            try {
                if (!exec.awaitTermination(15, TimeUnit.SECONDS)) {
                    exec.shutdownNow();
                }
            } catch (InterruptedException e) {
                exec.shutdownNow();
                Thread.currentThread().interrupt();
            }

            sc.close();
            System.out.println("Programa terminado.");
        }


    }

    private int validateDiceInput(String input) {
        try {
            var in = Integer.parseInt(input);
            if (in > 0) {
                return in;
            } else {
                System.out.println("No puede ser menor de 1, prueba de nuevo");
            }
            return 0;
        } catch (InputMismatchException e) {
            System.out.println("No es un numero valido, prueba de nuevo");
        }
        return 0;
    }

    private int validateDiceInputInRange(String input) {

        try {
            var in = Integer.parseInt(input);
            if (in < 1) {
                System.out.println("No puede ser menor de 1, prueba de nuevo");
                return 0;
            }
            if (in > MAX_DICE_VALUE) {
                System.out.println("No puede ser mayor de " + MAX_DICE_VALUE + ", prueba de nuevo");
                return 0;
            }
            return in;
        } catch (InputMismatchException e) {
            System.out.println("No es un numero valido, prueba de nuevo");
        }
        return 0;
    }
}
