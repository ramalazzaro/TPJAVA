package Interfaces;

/**
 * Una interfaz que define el comportamiento de un objeto filtrable.
 * Un objeto filtrable puede aplicar y quitar filtros, así como consultar el estado actual del filtro.
 */
public interface Filtrable {

    /**
     * Aplica un filtro al objeto filtrable.
     */
    public void poneFiltro();

    /**
     * Quita el filtro aplicado al objeto filtrable.
     */
    public void sacaFiltro();

    /**
     * Consulta el estado actual del filtro aplicado al objeto filtrable.
     *
     * @return el estado actual del filtro como una cadena de texto.
     */
    public String consultaFiltro();
}
