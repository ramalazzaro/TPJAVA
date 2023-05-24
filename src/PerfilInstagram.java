import java.util.*;

public class PerfilInstagram {
    private SortedSet<Publicacion> listaPublicaciones = new TreeSet<>();
    private SortedSet<Album> listaAlbumes = new TreeSet<>();
    Iterator<Publicacion> it = listaPublicaciones.iterator();

    public void addPublicacion(Publicacion nuevaPublicacion) {
        listaPublicaciones.add(nuevaPublicacion);
    }

    public void mostrarPublicaciones() {
        for (Publicacion publicacion : listaPublicaciones) {
            System.out.println(publicacion.toString());
        }
    }
    public SortedSet<Publicacion> getListaPublicaciones(){return listaPublicaciones;}

    public SortedSet<Album> getListaAlbumes() {return listaAlbumes;}
}