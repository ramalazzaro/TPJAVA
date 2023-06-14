package Comparators;

import PrincipalClass.Publicacion;
import java.util.Comparator;

/**
 * Comparador para ordenar publicaciones por fecha de subida en orden descendente.
 */
public class FechaComparator implements Comparator<Publicacion> {

    /**
     * Compara dos publicaciones por su fecha de subida.
     *
     * @param p1 la primera publicación a comparar.
     * @param p2 la segunda publicación a comparar.
     * @return un valor negativo si p1 es anterior a p2, cero si son iguales, o un valor positivo si p1 es posterior a p2.
     */
    @Override
    public int compare(Publicacion p1, Publicacion p2) {
        return p2.getFechaSubida().compareTo(p1.getFechaSubida());
    }
}
