import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PerfilInstagram {
    //ver como hacer esto de forma TREESET, (ejemplo concesionaria en teoria)
    private List<Publicacion> listaPublicaciones = new TreeSet<>();
    public void addPublicacion(Publicacion nuevaPublicacion) {
        listaPublicaciones.add(nuevaPublicacion);
    }
}