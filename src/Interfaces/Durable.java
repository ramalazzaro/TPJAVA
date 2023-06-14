package Interfaces;

/**
 * Una interfaz que define el comportamiento de un objeto durable.
 * Un objeto durable puede ser pausado, reanudado, finalizado y proporciona información sobre su estado y duración.
 */
public interface Durable {

    /**
     * Pausa el objeto durable.
     */
    public void pausar();
    /**
     * Reanuda el objeto durable después de haber sido pausado.
     */
    public void reanudar();
    /**
     * Finaliza el objeto durable.
     */
    public void finalizar();
    /**
     * Obtiene el estado de pausa del objeto durable.
     *
     * @return true si el objeto está pausado, false en caso contrario.
     */
    public boolean getPausa();
    /**
     * Obtiene el estado de finalización del objeto durable.
     *
     * @return true si el objeto está finalizado, false en caso contrario.
     */
    public boolean getFinaliza();
    /**
     * Obtiene la duración del objeto durable.
     *
     * @return la duración del objeto durable.
     */
    public int getDuracion();
}
