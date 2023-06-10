import java.util.ArrayList;

public class Video extends Publicacion {
    private int duracion;
    private String resolucion;
    private int cantidadCuadros;

    public Video(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
            ArrayList<Etiqueta> etiquetas, int duracion, String resolucion,
            int cantidadCuadros) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
        this.duracion = duracion;
        this.resolucion = resolucion;

    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public int getCantidadCuadros() {
        return cantidadCuadros;
    }

    public void setCantidadCuadros(int cantidadCuadros) {
        this.cantidadCuadros = cantidadCuadros;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nVideo\n")
                .append(super.toString())
                .append(", duracion=").append(duracion)
                .append(", resolucion='").append(resolucion).append('\'')
                .append(", cantidadCuadros=").append(cantidadCuadros);

        builder.append('\n');
        return builder.toString();
    }

}
