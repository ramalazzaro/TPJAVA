package PrincipalClass;

import java.util.ArrayList;

/**
 * Clase que representa una publicación de texto en un perfil de Instagram.
 */
public class Texto extends Publicacion {
    private String contenido;
    private int caracteres;
    private String fuente;
    private String tamaño;

    /**
     * Constructor de la clase Texto.
     *
     * @param nombre        el nombre de la publicación.
     * @param fechaSubida   la fecha de subida de la publicación.
     * @param cantMG        la cantidad de me gusta de la publicación.
     * @param comentarios   la lista de comentarios de la publicación.
     * @param etiquetas     la lista de etiquetas de la publicación.
     * @param contenido     el contenido del texto.
     * @param caracteres    la cantidad de caracteres del texto.
     * @param fuente        la fuente del texto.
     * @param tamaño        el tamaño del texto.
     */
    public Texto(String nombre, String fechaSubida, int cantMG, ArrayList<Comentario> comentarios,
                 ArrayList<Etiqueta> etiquetas, String contenido, int caracteres, String fuente,
                 String tamaño) {
        super(nombre, fechaSubida, cantMG, comentarios, etiquetas);
        this.contenido = contenido;
        this.caracteres = caracteres;
        this.fuente = fuente;
        this.tamaño = tamaño;
    }
    /**
     * Obtiene el contenido del texto.
     *
     * @return el contenido del texto.
     */
    public String getContenido() {
        return contenido;
    }
    /**
     * Establece el contenido del texto.
     *
     * @param contenido el contenido del texto.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    /**
     * Obtiene la cantidad de caracteres del texto.
     *
     * @return la cantidad de caracteres del texto.
     */
    public int getCaracteres() {
        return caracteres;
    }
    /**
     * Establece la cantidad de caracteres del texto.
     *
     * @param caracteres la cantidad de caracteres del texto.
     */
    public void setCaracteres(int caracteres) {
        this.caracteres = caracteres;
    }
    /**
     * Obtiene la fuente del texto.
     *
     * @return la fuente del texto.
     */
    public String getFuente() {
        return fuente;
    }
    /**
     * Establece la fuente del texto.
     *
     * @param fuente la fuente del texto.
     */
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    /**
     * Obtiene el tamaño del texto.
     *
     * @return el tamaño del texto.
     */
    public String getTamaño() {
        return tamaño;
    }
    /**
     * Establece el tamaño del texto.
     *
     * @param tamaño el tamaño del texto.
     */
    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
    /**
     * Devuelve una representación en forma de cadena de la publicación de texto.
     *
     * @return una cadena que representa la publicación de texto.
     */
    @Override
    public String toString() {
        return "TEXTO\n" + super.toString() + "\n\tContenido: " + contenido + ", Caracteres: " + caracteres +
                ", Fuente: " + fuente + ", Tamaño: " + tamaño + "\n";
    }
}
