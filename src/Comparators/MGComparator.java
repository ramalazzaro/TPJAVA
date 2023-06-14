package Comparators;

import PrincipalClass.Publicacion;

import java.util.Comparator;

/**
 * Comparador para ordenar publicaciones por cantidad de Me Gusta (MG) en orden descendente.
 * En caso de que dos publicaciones tengan la misma cantidad de MG, se ordenan alfabéticamente por nombre.
 */
public class MGComparator implements Comparator<Publicacion> {

    /**
     * Compara dos publicaciones por su cantidad de Me Gusta (MG).
     *
     * @param o1 la primera publicación a comparar.
     * @param o2 la segunda publicación a comparar.
     * @return un valor negativo si o1 tiene menos MG que o2, cero si tienen la misma cantidad de MG,
     *         o un valor positivo si o1 tiene más MG que o2. Si tienen la misma cantidad de MG,
     *         se compara alfabéticamente por nombre.
     */
    @Override
    public int compare(Publicacion o1, Publicacion o2) {
        int compareMG = Integer.compare(o2.getCantidadMG(), o1.getCantidadMG());

        if (compareMG != 0) {
            return compareMG;
        } else {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    }
}
