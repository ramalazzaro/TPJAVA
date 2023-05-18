import java.util.*;

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
    public SortedSet<Publicacion> getListaPublicaciones(){return listaPublicaciones;}
}
