import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PerfilInstagram {
    private SortedSet<Publicacion> listaPublicaciones = new TreeSet<>();

    public void addPublicacion(Publicacion nuevaPublicacion) {
        listaPublicaciones.add(nuevaPublicacion);
    }

    public void mostrarPublicaciones() {
        for (Publicacion publicacion : listaPublicaciones) {
            System.out.println(publicacion.toString());
        }
    }
}
