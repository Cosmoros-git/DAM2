package tema1.ej10;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Capturador {
    private String directorio;
    private int numCaptura = 0;

    public Capturador(){
        this.directorio = "./";
    }
    public Capturador(String directorio){
        this.directorio = directorio;
    }

    public synchronized void RealizaCaptura(){
        System.out.println("Captura hecha! Numero de captura: " + numCaptura);
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        try {
            BufferedImage capture = new Robot().createScreenCapture(screenRect);
            ImageIO.write(capture, "bmp", new File(directorio + "/captura"+ numCaptura +".bmp"));
            numCaptura++;
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            System.err.println("Permisos insuficientes para capturar pantalla: " + e.getMessage());
        }
    }



}
