import java.util.*;

public class PerfilInstagram {
    private SortedSet<Publicacion> listaPublicaciones = new TreeSet<>();
    private ArrayList<Album> listaAlbumes = new ArrayList<>();

    public void addAlbum(String nombre) {
        Album album = new Album(nombre); // Crea album utilizando el constructor de la clase Album
        listaAlbumes.add(album); // se agrega el álbum recién creado a la lista de álbumes gestionada
    }

    public ArrayList<Album> getListaAlbumes() {
        return listaAlbumes;
    }

    public void deleteAlbum(String nombre) {
        Album albumAEliminar = null;
        for (Album album : listaAlbumes) {
            if (album.getNombre().equals(nombre)) {
                albumAEliminar = album;
                break;
            }
        }
        if (albumAEliminar != null) {
            listaAlbumes.remove(albumAEliminar);
        }
    }

    public void addPublicacion(Publicacion nuevaPublicacion) {
        listaPublicaciones.add(nuevaPublicacion);
    }

    public void mostrarPublicaciones() {
        for (Publicacion publicacion : listaPublicaciones) {
            System.out.println(publicacion.toString());
        }
    }

    public SortedSet<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

}