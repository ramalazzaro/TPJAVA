package PrincipalClass;

import java.io.*;
import java.util.*;

public class PerfilInstagram implements Serializable{
    private static final long serialVersionUID = 1L;
    final private SortedSet<Publicacion> listaPublicaciones = new TreeSet<>();
    private ArrayList<Album> listaAlbumes = new ArrayList<>();

    public void addAlbum(Album album) {
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

    public void removeAlbum(Album album) {
        listaAlbumes.remove(album);
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