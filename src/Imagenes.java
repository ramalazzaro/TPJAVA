public class Imagenes extends Publicacion implements Filtrable{
    private String resolucion;
    private int ancho;
    private int alto;

    public Imagenes(String nombre, String fechaSubida, int cantidadMG, String resolucion, int ancho, int alto) {
        super(nombre, fechaSubida, cantidadMG);
        this.resolucion = resolucion;
        this.ancho = ancho;
        this.alto = alto;
    }
    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}


