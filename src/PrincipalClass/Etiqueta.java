package PrincipalClass;//import java.util.Objects;
import java.io.Serializable;
public class Etiqueta implements Serializable {
    private String contenido;

    public Etiqueta(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "PrincipalClass.Etiqueta: " + contenido;
    }

}
