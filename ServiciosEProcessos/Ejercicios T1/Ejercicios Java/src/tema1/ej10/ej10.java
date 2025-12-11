package tema1.ej10;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ej10 {

    private final String DEBUG_LOCATION = "src/tema1/ej10/capturas";

    public void Start() {
        var scanner = new Scanner(System.in);

        var location = validateLocation(scanner);
        System.out.println("Directorio seleccionado: " + location);

        var timing = validateInt(scanner, "Frecuencia de capturas de pantalla");
        System.out.println("Frecuencia: " + timing + " segundos");

        Capturador capturador = new Capturador(location);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(capturador::RealizaCaptura, 0, timing, TimeUnit.SECONDS);
        System.out.println("Capturas iniciadas!");

    }


    public String validateLocation(Scanner scanner) {
        Path folder;
        while (true) {
            System.out.println("Ingrese el nombre del carpeta");
            var rawLoc = scanner.nextLine().trim();

            if (rawLoc.isEmpty()) {
                System.out.printf("El nombre de carpeta no puede ser vacio\nUsando default location %s\n", DEBUG_LOCATION);
                return DEBUG_LOCATION;
            }

            try {
                folder = Paths.get(rawLoc);
            } catch (InvalidPathException e) {
                System.out.println("El nombre de carpeta no es valido");
                continue;
            }
            if (!Files.exists(folder)) {
                System.out.println("El nombre de carpeta no existe");
                continue;
            }
            if (!Files.isWritable(folder)) {
                System.out.println("Carpeta es Read-Only");
                continue;
            }
            return folder.toString();
        }
    }

    public int validateInt(Scanner scanner, String message) {

        while (true) {
            try {
                System.out.println(message);
                var in = scanner.nextInt();
                if (in <= 0) {
                    System.out.println("Numero no puede ser mayor o igual a 0");
                }
                return in;
            } catch (InputMismatchException e) {
                System.out.println("Input invalido. Prueba de nuevo");
            }
        }
    }
}
