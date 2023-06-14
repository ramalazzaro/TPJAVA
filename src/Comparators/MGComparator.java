package Comparators;

import PrincipalClass.Publicacion;

import java.util.Comparator;
public class MGComparator implements Comparator<Publicacion> {
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

