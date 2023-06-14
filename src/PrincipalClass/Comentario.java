package PrincipalClass;

import java.io.Serializable;

/**
 * Representa un comentario en una publicación.
 * Implementa la interfaz Serializable.
 */
public class Comentario implements Serializable {
    private String usuario;
    private String comentario;
    private String fecha;
    /**
     * Constructor de la clase Comentario.
     *
     * @param usuario    El usuario que realiza el comentario.
     * @param comentario El contenido del comentario.
     * @param fecha      La fecha en la que se realizó el comentario.
     */
    public Comentario(String usuario, String comentario, String fecha) {
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }
    /**
     * Obtiene el usuario que realizó el comentario.
     *
     * @return El usuario que realizó el comentario.
     */
    public String getUsuario() {
        return usuario;
    }
    /**
     * Establece el usuario que realizó el comentario.
     *
     * @param usuario El usuario que realizó el comentario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /**
     * Obtiene el contenido del comentario.
     *
     * @return El contenido del comentario.
     */
    public String getComentario() {
        return comentario;
    }
    /**
     * Establece el contenido del comentario.
     *
     * @param comentario El contenido del comentario.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    /**
     * Obtiene la fecha en la que se realizó el comentario.
     *
     * @return La fecha en la que se realizó el comentario.
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * Establece la fecha en la que se realizó el comentario.
     *
     * @param fecha La fecha en la que se realizó el comentario.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * Devuelve una representación en forma de cadena del comentario.
     *
     * @return Una representación en forma de cadena del comentario.
     */
    @Override
    public String toString() {
        return fecha + " " + usuario + " comentó: " + comentario;
    }
}
