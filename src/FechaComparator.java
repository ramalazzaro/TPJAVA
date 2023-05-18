import java.util.Comparator;

public class FechaComparator implements Comparator<Publicacion> {
    public int compare(Publicacion p1, Publicacion p2) {
        return p2.getFechaSubida().compareTo(p1.getFechaSubida());
    }
}
