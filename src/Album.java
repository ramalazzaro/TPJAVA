import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Album {
    private String nombre;
    private List<Publicacion> publicaciones;
    private List<Album> subAlbumes;
    int[] cantTipo = new int[4]  ; // Índice 0: Imagen, Índice 1: Video, Índice 2: Texto, Índice 3: Audio
    int cantTotal = 0;

    public Album(String nombre) {
        this.nombre = nombre;
        this.publicaciones = new ArrayList<>();
        this.subAlbumes = new ArrayList<>();
        for (int i = 0; i < cantTipo.length; i++) {
            this.cantTipo[i] = 0;
        }
    }
    private void addCantTipo (Publicacion publicacion) {
        switch (publicacion.getClass().getName()) {
            case "Imagen" -> cantTipo[0]++;
            case "Video" -> cantTipo[1]++;
            case "Texto" -> cantTipo[2]++;
            case "Audio" -> cantTipo[3]++;
        }
    }
    public void addPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
        addCantTipo(publicacion);
        cantTotal++;
    }
    public void addSubAlbum(Album subAlbum) {
        subAlbumes.add(subAlbum);
    }
    public void deleteSubAlbum(Album subAlbum) {
        subAlbumes.remove(subAlbum);
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public List<Album> getSubAlbumes() {
        return subAlbumes;
    }

    public int getCantTipo(int i) {
        return cantTipo[i];
    }

    public int getCantTotal() {
        return cantTotal;
    }

    public String publicacionesToString() {
        StringBuilder temp = new StringBuilder();
        for (Publicacion publicacion: publicaciones) {
             temp.append(publicacion.getNombre());
        }
        return temp.toString();
    }

    public String subAlbumesToString () {
        StringBuilder temp = new StringBuilder();
        for (Album album: subAlbumes) {
            temp.append(album.nombre +
                    ", Publicaciones = " + album.publicacionesToString() +
                    ", Imagenes=" + album.getCantTipo(0) +
                    ", Videos=" + album.getCantTipo(1) +
                    ", Textos=" + album.getCantTipo(2) +
                    ", Videos=" + album.getCantTipo(3) +
                    ", Cantidad Total=" + album.cantTotal);
        }
        return temp.toString();
    }

    @Override
    public String toString() {
        return "Album " + nombre +
                ", Publicaciones = " + publicacionesToString() +
                ", Imagenes=" + getCantTipo(0) +
                ", Videos=" + getCantTipo(1) +
                ", Textos=" + getCantTipo(2) +
                ", Videos=" + getCantTipo(3) +
                ", Cantidad Total=" + cantTotal +
                ", SubAlbum = " + subAlbumesToString();
    }

}

