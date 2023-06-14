package PrincipalClass;

import PrincipalClass.Comentario;
import Interfaces.Filtrable;
import java.util.ArrayList;

/**
 * Representa una imagen en una publicación.
 * Implementa la interfaz Filtrable.
 */
public class Imagen extends Publicacion implements Filtrable{
    private String resolucion;
    private int ancho;
    private int alto;
    private static boolean filtro = false;

    /**
     * Constructor de la clase Imagen.
     *
     * @param nombre         El nombre de la imagen.
     * @param fechaSubida    La fecha de subida de la imagen.
     * @param cantidadMG     La cantidad de "Me gusta" de la imagen.
     * @param comentarios    La lista de comentarios de la imagen.
     * @param etiquetas      La lista de etiquetas de la imagen.
     * @param resolucion     La resolución de la imagen.
     * @param ancho          El ancho de la imagen.
     * @param alto           El alto de la imagen.
     */
    public Imagen(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
                  ArrayList<Etiqueta> etiquetas, String resolucion, int ancho, int alto) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
        this.resolucion = resolucion;
        this.ancho = ancho;
        this.alto = alto;
    }
    /**
     * Aplica un filtro a la imagen.
     */
    @Override
    public void poneFiltro() {
        filtro = true;
    }
    /**
     * Quita el filtro aplicado a la imagen.
     */
    @Override
    public void sacaFiltro() {
        filtro = false;
    }
    /**
     * Consulta si la imagen tiene un filtro aplicado.
     *
     * @return Una cadena indicando si hay un filtro aplicado o no.
     */
    @Override
    public String consultaFiltro() {
        if (filtro)
            return "Filtro aplicado\n";
        else
            return "Filtro no aplicado\n";
    }
    /**
     * Obtiene la resolución de la imagen.
     *
     * @return La resolución de la imagen.
     */
    public String getResolucion() {
        return resolucion;
    }
    /**
     * Establece la resolución de la imagen.
     *
     * @param resolucion La resolución de la imagen.
     */
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    /**
     * Obtiene el ancho de la imagen.
     *
     * @return El ancho de la imagen.
     */
    public int getAncho() {
        return ancho;
    }
    /**
     * Establece el ancho de la imagen.
     *
     * @param ancho El ancho de la imagen.
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    /**
     * Obtiene el alto de la imagen.
     *
     * @return El alto de la imagen.
     */
    public int getAlto() {
        return alto;
    }
    /**
     * Establece el alto de la imagen.
     *
     * @param alto El alto de la imagen.
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }
    /**
     * Devuelve una representación en forma de cadena de la imagen.
     *
     * @return Una representación en forma de cadena de la imagen.
     */
    @Override
    public String toString() {
        return "IMAGEN\n" + super.toString() + "\tResolucion: " + resolucion + ", Ancho: " + ancho + ", Alto: " + alto + "\n";
    }
}
