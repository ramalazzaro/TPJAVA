import java.util.Comparator;
public class MGComparator implements Comparator<Publicacion> {
    @Override
    public int compare(Publicacion o1, Publicacion o2) {
        return Integer.compare(o2.getCantidadMG(), o1.getCantidadMG());
    }
}
