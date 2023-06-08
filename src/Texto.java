import java.util.List;
import java.util.ArrayList;

public class Texto extends Publicacion {
    private String contenido;
    private int caracteres;
    private String fuente;
    private String tamaño;
    private List<Comentario> comentarios;

    public Texto(String nombre, String fechaSubida, int cantMG, String contenido, int caracteres, String fuente,
            String tamaño) {
        super(nombre, fechaSubida, cantMG);
        this.contenido = contenido;
        this.caracteres = caracteres;
        this.fuente = fuente;
        this.tamaño = tamaño;
        this.comentarios = new ArrayList<>();
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(int caracteres) {
        this.caracteres = caracteres;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
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
        sb.append("Texto{");
        sb.append("contenido='").append(contenido).append('\'');
        sb.append(", caracteres=").append(caracteres);
        sb.append(", fuente='").append(fuente).append('\'');
        sb.append(", tamaño='").append(tamaño).append('\'');

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
