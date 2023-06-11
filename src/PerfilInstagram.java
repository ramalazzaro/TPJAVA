import java.io.*;
import java.util.*;
import java.io.Serializable;
public class PerfilInstagram implements Serializable{
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

    public void guardarPerfil(PerfilInstagram PI){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PerfilInstagram.dat"))){
            oos.writeObject(PI);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void recuperarPerfil(PerfilInstagram PI){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PerfilInstagram.dat"))){
            PI = (PerfilInstagram) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}