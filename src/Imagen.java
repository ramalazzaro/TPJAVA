import java.util.ArrayList;

public class Imagen extends Publicacion {
    private String resolucion;
    private int ancho;
    private int alto;

    public Imagen(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
            ArrayList<Etiqueta> etiquetas, String resolucion, int ancho, int alto) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nImagen\n")
                .append(super.toString())
                .append("\tResolucion: ").append(resolucion).append('\'')
                .append(", Ancho: ").append(ancho)
                .append(", Alto: ").append(alto);

        builder.append('\n');

        return builder.toString();
    }

}