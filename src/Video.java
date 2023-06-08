import java.util.List;
import java.util.ArrayList;

public class Video extends Publicacion {
    private int duracion;
    private String resolucion;
    private int cantidadCuadros;
    private List<Comentario> comentarios;

    public Video(String nombre, String fechaSubida, int cantidadMG, int duracion, String resolucion,
            int cantidadCuadros) {
        super(nombre, fechaSubida, cantidadMG);
        this.duracion = duracion;
        this.resolucion = resolucion;
        this.cantidadCuadros = cantidadCuadros;
        this.comentarios = new ArrayList<>();
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

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Video{");
        sb.append("nombre='").append(getNombre()).append('\'');
        sb.append(", fechaSubida='").append(getFechaSubida()).append('\'');
        sb.append(", cantidadMG=").append(getCantidadMG());
        sb.append(", duracion=").append(duracion);
        sb.append(", resolucion='").append(resolucion).append('\'');
        sb.append(", cantidadCuadros=").append(cantidadCuadros);

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
