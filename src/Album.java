import java.util.ArrayList;
import java.util.List;

public class Album {
    private String nombre;
    private List<Publicacion> publicaciones;
    private List<Album> subAlbumes;
    int[] cantTipo = new int[4]; // Índice 0: Imagen, Índice 1: Video, Índice 2: Texto, Índice 3: Audio
    int cantTotal = 0;


    public Album(String nombre) {
        this.nombre = nombre;
        this.publicaciones = new ArrayList<>();
        this.subAlbumes = new ArrayList<>();
        for (int i = 0; i < cantTipo.length; i++) {
            this.cantTipo[i] = 0;
        }
    }
    public void addCantTipo (Publicacion publicacion) {
        switch (publicacion.getClass().getName()) {
            case "Imagen" -> cantTipo[0]++;
            case "Video" -> cantTipo[1]++;
            case "Texto" -> cantTipo[2]++;
            case "Audio" -> cantTipo[3]++;
        }
    }
    public void agregarPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
        addCantTipo(publicacion);
        cantTotal++;
    }
    public void agregarSubAlbum(Album subAlbum) {
        subAlbumes.add(subAlbum);
    }
    public void eliminarSubAlbum(Album subAlbum) {
        subAlbumes.remove(subAlbum);
    }

}

