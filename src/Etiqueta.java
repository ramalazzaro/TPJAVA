//import java.util.Objects;

public class Etiqueta {
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
        return "Etiqueta: " + contenido;
    }

}
