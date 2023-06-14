package PrincipalClass;

import Interfaces.Durable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/**
 * Representa una publicación de tipo audio.
 * Extiende la clase Publicacion e implementa la interfaz Durable.
 */
public class Audio extends Publicacion implements Durable {
    private int duracion;
    private int velocidad;
    private static boolean finaliza = false;
    private static boolean pausa = false;
    /**
     * Constructor de la clase Audio.
     *
     * @param nombre       El nombre del audio.
     * @param fechaSubida  La fecha de subida del audio.
     * @param cantidadMG   La cantidad de megabytes del audio.
     * @param comentarios  La lista de comentarios del audio.
     * @param etiquetas    La lista de etiquetas del audio.
     * @param duracion     La duración del audio en segundos.
     * @param velocidad    La velocidad del audio.
     */
    public Audio(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios, ArrayList<Etiqueta> etiquetas, int duracion, int velocidad) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
        this.duracion = duracion;
        this.velocidad = velocidad;
    }
    /**
     * Pausa la reproducción del audio.
     */
    @Override
    public void pausar() {
        pausa = true;
    }
    /**
     * Reanuda la reproducción del audio.
     */
    @Override
    public void reanudar() {
        pausa = false;
    }
    /**
     * Finaliza la reproducción del audio.
     */
    @Override
    public void finalizar() {
        finaliza = true;
    }/**
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
     * Obtiene la duración del audio.
     *
     * @return La duración del audio en segundos.
     */
    public int getDuracion() {
        return duracion;
    }
    /**
     * Establece la duración del audio.
     *
     * @param duracion La duración del audio en segundos.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    /**
     * Obtiene la velocidad del audio.
     *
     * @return La velocidad del audio.
     */
    public int getVelocidad() {
        return velocidad;
    }
    /**
     * Establece la velocidad del audio.
     *
     * @param velocidad La velocidad del audio.
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    /**
     * Devuelve una representación en forma de cadena del audio.
     *
     * @return Una representación en forma de cadena del audio.
     */
    @Override
    public String toString() {
        return "AUDIO\n" + super.toString() + "\tDuracion: " + duracion + ", Velocidad: " + velocidad + "\n";
    }
}

