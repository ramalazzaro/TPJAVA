package PrincipalClass;

import PrincipalClass.Comentario;
import Interfaces.Filtrable;
import Interfaces.Durable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * La clase Video representa una publicación de video en una plataforma.
 * Implementa las interfaces Durable y Filtrable.
 */
public class Video extends Publicacion implements Durable, Filtrable {
    private int duracion;
    private String resolucion;
    private int cantidadCuadros;
    private static boolean finaliza=false;
    private static boolean pausa=false;
    private static boolean filtro=false;

    /**
     * Constructor de la clase Video.
     *
     * @param nombre           El nombre del video.
     * @param fechaSubida      La fecha de subida del video.
     * @param cantidadMG       La cantidad de "Me Gusta" del video.
     * @param comentarios      La lista de comentarios del video.
     * @param etiquetas        La lista de etiquetas del video.
     * @param duracion         La duración del video en segundos.
     * @param resolucion       La resolución del video.
     * @param cantidadCuadros  La cantidad de cuadros del video.
     */
    public Video(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
                 ArrayList<Etiqueta> etiquetas, int duracion, String resolucion,
                 int cantidadCuadros) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
        this.duracion = duracion;
        this.resolucion = resolucion;
    }
    /**
     * Pausa la reproducción del video.
     */
    @Override
    public void pausar(){
        pausa = true;
    }
    /**
     * Reanuda la reproducción del video después de haber sido pausado.
     */
    @Override
    public void reanudar(){
        pausa = false;
    }
    /**
     * Finaliza la reproducción del video.
     */
    @Override
    public void finalizar(){
        finaliza = true;
    }
    /**
     * Devuelve el estado de la variable "pausa".
     *
     * @return true si la pausa está activada, false en caso contrario.
     */
    @Override
    public boolean getPausa() {
        return pausa;
    }

    /**
     * Devuelve el estado de la variable "finaliza".
     *
     * @return true si la finalización está activada, false en caso contrario.
     */
    @Override
    public boolean getFinaliza() {
        return finaliza;
    }
    /**
     * Aplica un filtro al video.
     */
    @Override
    public void poneFiltro() {
        filtro = true;
    }
    /**
     * Quita el filtro aplicado al video.
     */
    @Override
    public void sacaFiltro() {
        filtro = false;
    }
    /**
     * Consulta si se ha aplicado un filtro al video.
     *
     * @return El estado del filtro aplicado al video.
     */
    @Override
    public String consultaFiltro(){
        if(filtro)
            return "Filtro aplicado \n";
        else
            return "Filtro no aplicado \n";
    }

    /**
     * Obtiene la duración del video.
     *
     * @return La duración del video en segundos.
     */
    @Override
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración del video.
     *
     * @param duracion La duración del video en segundos.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene la resolución del video.
     *
     * @return La resolución del video.
     */
    public String getResolucion() {
        return resolucion;
    }
    /**
     * Establece la resolución del video.
     *
     * @param resolucion La resolución del video.
     */
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    /**
     * Obtiene la cantidad de cuadros del video.
     *
     * @return La cantidad de cuadros del video.
     */
    public int getCantidadCuadros() {
        return cantidadCuadros;
    }
    /**
     * Establece la cantidad de cuadros del video.
     *
     * @param cantidadCuadros La cantidad de cuadros del video.
     */
    public void setCantidadCuadros(int cantidadCuadros) {
        this.cantidadCuadros = cantidadCuadros;
    }
    /**
     * Devuelve una representación en cadena del objeto Video.
     *
     * @return Una cadena que representa al objeto Video.
     */
    @Override
    public String toString() {
        return "VIDEO\n" + super.toString() + "\tDuracion: " + duracion + ", Resolucion: " + resolucion + ", Cantidad de Cuadros: " + cantidadCuadros + "\n";
    }
}
