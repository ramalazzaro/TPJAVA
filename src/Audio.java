import java.util.List;
import java.util.ArrayList;

public class Audio extends Publicacion {
    private int duracion;
    private int velocidad;
    private List<Comentario> comentarios;

    public Audio(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios, ArrayList<Etiqueta> etiquetas, int duracion, int velocidad) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
        this.duracion = duracion;
        this.velocidad = velocidad;
        this.comentarios = new ArrayList<>();
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Audio{");
        sb.append("nombre='").append(getNombre()).append('\'');
        sb.append(", fechaSubida='").append(getFechaSubida()).append('\'');
        sb.append(", cantidadMG=").append(getCantidadMG());
        sb.append(", duracion=").append(duracion);
        sb.append(", velocidad=").append(velocidad);

        if (getComentarios() != null) {
            sb.append(", comentarios=[");
            for (Comentario comentario : getComentarios()) {
                sb.append("\n  ").append(comentario.toString());
            }
            sb.append("\n]");
        }

        sb.append('}');
        return sb.toString();
    }

}
