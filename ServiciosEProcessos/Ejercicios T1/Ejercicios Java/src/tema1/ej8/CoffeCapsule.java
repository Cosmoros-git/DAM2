package tema1.ej8;

public class CoffeCapsule {

    public String nombreVariedad;
    public int intensidadCafe;

    public CoffeCapsule(String nombreVariedad, int intensidadCafe) {
        this.nombreVariedad = nombreVariedad;
        this.intensidadCafe = intensidadCafe;
    }

    @Override
    public String toString() {
        return "Capsula "+nombreVariedad+" con intensidad: "+intensidadCafe;
    }
}
