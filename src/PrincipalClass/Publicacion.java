package PrincipalClass;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Clase abstracta que representa una publicación en una red social.
 */
public abstract class Publicacion implements Comparable<Publicacion>, Serializable {

    private String nombre;
    private String fechaSubida;
    private int cantidadMG;
    private ArrayList<Etiqueta> listaEtiquetas = new ArrayList<>();
    private ArrayList<Comentario> listaComentarios = new ArrayList<>();

    /**
     * Constructor de la clase Publicacion.
     *
     * @param nombre         El nombre de la publicación.
     * @param fechaSubida    La fecha de subida de la publicación.
     * @param cantidadMG     La cantidad de "me gusta" de la publicación.
     * @param comentarios    La lista de comentarios de la publicación.
     * @param etiquetas      La lista de etiquetas de la publicación.
     */
    public Publicacion(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
                       ArrayList<Etiqueta> etiquetas) {
        this.nombre = nombre;
        this.fechaSubida = fechaSubida;
        this.cantidadMG = cantidadMG;
        this.listaEtiquetas = etiquetas;
        this.listaComentarios = comentarios;
    }
    /**
     * Obtiene el nombre de la publicación.
     *
     * @return El nombre de la publicación.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre de la publicación.
     *
     * @param nombre El nombre de la publicación.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene la fecha de subida de la publicación.
     *
     * @return La fecha de subida de la publicación.
     */
    public String getFechaSubida() {
        return fechaSubida;
    }
    /**
     * Establece la fecha de subida de la publicación.
     *
     * @param fechaSubida La fecha de subida de la publicación.
     */
    public void setFechaSubida(String fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
    /**
     * Obtiene la cantidad de "me gusta" de la publicación.
     *
     * @return La cantidad de "me gusta" de la publicación.
     */
    public int getCantidadMG() {
        return cantidadMG;}

    /**
     * Establece la cantidad de "me gusta" de la publicación.
     *
     * @param cantidadMG La cantidad de "me gusta" de la publicación.
     */
    public void setCantidadMG(int cantidadMG) {
        this.cantidadMG = cantidadMG;
    }
    /**
     * Agrega una etiqueta a la publicación.
     *
     * @param nuevaEtiqueta La etiqueta a agregar.
     */
    public void addEtiqueta(Etiqueta nuevaEtiqueta) {
        listaEtiquetas.add(nuevaEtiqueta);
    }
    /**
     * Agrega un comentario a la publicación.
     *
     * @param nuevoComentario El comentario a agregar.
     */
    public void addComentario(Comentario nuevoComentario) {
        listaComentarios.add(nuevoComentario);
    }
    /**
     * Muestra las etiquetas de la publicación.
     */
    public void mostrarEtiquetas() {
        for (Etiqueta etiqueta : listaEtiquetas) {
            System.out.println(etiqueta.toString());
        }
    }
    /**
     * Muestra los comentarios de la publicación.
     */
    public void mostrarComentarios() {
        for (Comentario comentario : listaComentarios) {
            System.out.println(comentario.toString());
        }
    }
    /**
     * Obtiene la lista de comentarios de la publicación.
     *
     * @return La lista de comentarios de la publicación.
     */
    public ArrayList<Comentario> getComentarios() {
        return this.listaComentarios;
    }
    /**
     * Obtiene la lista de etiquetas de la publicación.
     *
     * @return La lista de etiquetas de la publicación.
     */
    public ArrayList<Etiqueta> getEtiquetas() {
        return this.listaEtiquetas;
    }
    /**
     * Devuelve una representación en forma de cadena de la publicación.
     *
     * @return La representación en forma de cadena de la publicación.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\tNombre: " + getNombre() + ", Fecha de Subida: " + getFechaSubida() + ", Cantidad de me gusta: " + getCantidadMG());

        if (getComentarios() != null && !getComentarios().isEmpty()) {
            builder.append("\n\tComentarios:\n");
            for (Comentario comentario : getComentarios()) {
                builder.append("\t\t" + comentario.toString() + '\n');
            }
        }

        if (getEtiquetas() != null && !getEtiquetas().isEmpty()) {
            builder.append("\tEtiquetas:\n");
            for (Etiqueta etiqueta : getEtiquetas()) {
                builder.append("\t\t" + etiqueta + '\n');
            }
        }

        return builder.toString();
    }
    /**
     * Compara esta publicación con otra publicación dada en función del nombre.
     *
     * @param o La publicación a comparar.
     * @return Un valor negativo si esta publicación es menor, cero si es igual o un valor positivo si es mayor.
     */
    @Override
    public int compareTo(Publicacion o) {
        return this.nombre.compareTo(o.nombre);
    }
}