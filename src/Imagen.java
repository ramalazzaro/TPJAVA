import java.util.List;
import java.util.ArrayList;

public class Imagen extends Publicacion {
    private String resolucion;
    private int ancho;
    private int alto;
    private List<Comentario> comentarios;

    public Imagen(String nombre, String fechaSubida, int cantidadMG, String resolucion, int ancho, int alto) {
        super(nombre, fechaSubida, cantidadMG);
        this.resolucion = resolucion;
        this.ancho = ancho;
        this.alto = alto;
        this.comentarios = new ArrayList<>();
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

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Imagen{")
                .append("nombre='").append(getNombre()).append('\'')
                .append(", fechaSubida='").append(getFechaSubida()).append('\'')
                .append(", cantidadMG=").append(getCantidadMG())
                .append(", resolucion='").append(resolucion).append('\'')
                .append(", ancho=").append(ancho)
                .append(", alto=").append(alto);

        if (comentarios != null && !comentarios.isEmpty()) {
            builder.append(", comentarios:\n");
            for (Comentario comentario : comentarios) {
                builder.append(comentario.toString()).append('\n');
            }
        }
        builder.append('}');

        return builder.toString();
    }

}