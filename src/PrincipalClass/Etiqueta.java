package PrincipalClass;

import java.io.Serializable;

/**
 * Representa una etiqueta asociada a una publicación.
 * Implementa la interfaz Serializable.
 */
public class Etiqueta implements Serializable {
    private String contenido;
    /**
     * Constructor de la clase Etiqueta.
     *
     * @param contenido El contenido de la etiqueta.
     */
    public Etiqueta(String contenido) {
        this.contenido = contenido;
    }
    /**
     * Obtiene el contenido de la etiqueta.
     *
     * @return El contenido de la etiqueta.
     */
    public String getContenido() {
        return contenido;
    }
    /**
     * Establece el contenido de la etiqueta.
     *
     * @param contenido El contenido de la etiqueta.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    /**
     * Devuelve una representación en forma de cadena de la etiqueta.
     *
     * @return Una representación en forma de cadena de la etiqueta.
     */
    @Override
    public String toString() {
        return "Etiqueta: " + contenido;
    }
}
