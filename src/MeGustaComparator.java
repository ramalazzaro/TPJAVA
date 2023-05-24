import java.util.Comparator;

public class MeGustaComparator implements Comparator<Publicacion> {
    @Override
    public int compare(Publicacion p1, Publicacion p2) {
        return Integer.compare(p2.getCantidadMG(),p1.getCantidadMG());
    }
}
