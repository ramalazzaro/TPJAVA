package PrincipalClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Representa un perfil de Instagram que contiene álbumes y publicaciones.
 * Implementa la interfaz Serializable.
 */
public class PerfilInstagram implements Serializable {
    private static final long serialVersionUID = 1L;
    final private SortedSet<Publicacion> listaPublicaciones = new TreeSet<>();
    private ArrayList<Album> listaAlbumes = new ArrayList<>();
    /**
     * Agrega un álbum a la lista de álbumes del perfil.
     *
     * @param album El álbum a agregar.
     */
    public void addAlbum(Album album) {
        listaAlbumes.add(album);
    }
    /**
     * Elimina un álbum de la lista de álbumes del perfil.
     *
     * @param album El álbum a eliminar.
     */
    public void removeAlbum(Album album) {
        listaAlbumes.remove(album);
    }
    /**
     * Agrega una publicación a la lista de publicaciones del perfil.
     *
     * @param publicacion La publicación a agregar.
     */
    public void addPublicacion(Publicacion publicacion) {
        listaPublicaciones.add(publicacion);
    }
    /**
     * Elimina una publicación de la lista de publicaciones del perfil.
     *
     * @param publicacion La publicación a eliminar.
     */
    public void removePublicacion(Publicacion publicacion) {
        listaPublicaciones.remove(publicacion);
    }
    /**
     * Obtiene la lista de álbumes del perfil.
     *
     * @return La lista de álbumes del perfil.
     */
    public ArrayList<Album> getListaAlbumes() {
        return listaAlbumes;
    }
    /**
     * Obtiene la lista de publicaciones del perfil.
     *
     * @return La lista de publicaciones del perfil.
     */
    public SortedSet<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }
    /**
     * Muestra todas las publicaciones del perfil.
     */
    public void mostrarPublicaciones() {
        for (Publicacion publicacion : listaPublicaciones) {
            System.out.println(publicacion.toString());
        }
    }
}
