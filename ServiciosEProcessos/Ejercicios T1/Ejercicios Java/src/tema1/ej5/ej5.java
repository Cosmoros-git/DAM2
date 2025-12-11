package tema1.ej5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ej5 {

    public void Start() {
        System.out.println("Indica cada cuántos segundos quieres que se guarde el saludo:");
        Scanner sc = new Scanner(System.in);
        int temp = 0;
        do {
            try {
                temp = sc.nextInt() * 1000;
                if (temp < 1000) {
                    System.out.println("Debe ser mayor de 1s (1000ms)");
                    temp = 0;  // Force retry
                }
            }catch (InputMismatchException e){
                System.err.println("Numero introducido no es valido como int");
                temp = 0;
                sc.nextLine();
            }

        } while (temp < 1000);
        var timingInSeconds=temp;
        final String message = "Hola mundo!";
        final String fileNameWithLocation = "src/tema1/ej5/saludos.txt";


        AtomicBoolean running = new AtomicBoolean(true);

        Thread holaMundoThread = new Thread(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameWithLocation, true))) {


                while (running.get() && !Thread.interrupted()) {
                    System.out.println(message); // Writes message and saves it
                    writer.write(message);
                    writer.newLine();
                    writer.flush();


                    Thread.sleep(timingInSeconds);
                }

            } catch (IOException e) {
                System.err.println("Error al escribir el saludo");
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted");
                Thread.currentThread().interrupt();
            }
        }) {
        };

        holaMundoThread.start();
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();

            if ("si".equals(input)) {
                System.out.println("Solicitando detener hilo...");
                running.set(false); // Stops first thread too.
                holaMundoThread.interrupt(); // If sleeps

                try {
                    holaMundoThread.join(2000); // Delay so writer finishes writing.
                    System.out.println("Programa finalizado.");
                } catch (InterruptedException e) {
                    System.err.println("Error esperando por el hilo");
                }
                break;
            } else {
                System.out.println("Escribe 'si' para salir.");
            }
        }

        sc.close();
        holaMundoThread.interrupt();


    }
}
