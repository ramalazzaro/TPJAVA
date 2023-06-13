import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;

public class Album implements Serializable {
    private String nombre;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Album> subAlbumes;
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

    private void addCantTipo(Publicacion publicacion) {
        switch (publicacion.getClass().getName()) {
            case "Imagen" -> cantTipo[0]++;
            case "Video" -> cantTipo[1]++;
            case "Texto" -> cantTipo[2]++;
            case "Audio" -> cantTipo[3]++;
        }
    }

    private void removeCantTipo(Publicacion publicacion) {
        switch (publicacion.getClass().getName()) {
            case "Imagen" -> cantTipo[0]--;
            case "Video" -> cantTipo[1]--;
            case "Texto" -> cantTipo[2]--;
            case "Audio" -> cantTipo[3]--;
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

    public void deletePublicacion(Publicacion publicacion) {
        publicaciones.remove(publicacion);
        removeCantTipo(publicacion);
        cantTotal--;
    }

    public void deleteSubAlbum(Album subAlbum) {
        subAlbumes.remove(subAlbum);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void removeSubAlbum(Album subAlbum) {
        subAlbumes.remove(subAlbum);
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public ArrayList<Album> getSubAlbumes() {
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
        for (Publicacion publicacion : publicaciones) {
            temp.append(publicacion.getNombre() + "\n");
        }
        return temp.toString();
    }

    /*
     * public String subAlbumesToString() {
     * StringBuilder temp = new StringBuilder();
     * for (Album album : subAlbumes) {
     * 
     * temp.append(album.nombre +
     * "\nPublicaciones: \n" + publicacionesToString() +
     * "Imagenes = " + getCantTipo(0) +
     * ", Videos = " + getCantTipo(1) +
     * ", Textos = " + getCantTipo(2) +
     * ", Audio = " + getCantTipo(3) +
     * ", Cantidad Total = " + cantTotal +
     * ", Cantidad Total=" + album.cantTotal);
     * 
     * temp.append("<html>" +
     * "<b>Album:</b> " + album.getNombre() + "<br>" +
     * "<b>Publicaciones:</b> <br>" + album.publicacionesToString().replaceAll("\n",
     * "<br>") +
     * "<b>Imagenes:</b> " + album.getCantTipo(0) + "<br>" +
     * "<b>Videos:</b> " + album.getCantTipo(1) + "<br>" +
     * "<b>Textos:</b> " + album.getCantTipo(2) + "<br>" +
     * "<b>Audio:</b> " + album.getCantTipo(3) + "<br>" +
     * "<b>Cantidad Total:</b> " + album.cantTotal + "<br>" +
     * "</html>");
     * }
     * return temp.toString();
     * }
     * 
     * @Override
     * public String toString() {
     * return "<html>" +
     * "<b>Album:</b> " + nombre + "<br>" +
     * "<b>Publicaciones:</b> <br>" + publicacionesToString().replaceAll("\n",
     * "<br>") +
     * "<b>Imagenes:</b> " + getCantTipo(0) + "<br>" +
     * "<b>Videos:</b> " + getCantTipo(1) + "<br>" +
     * "<b>Textos:</b> " + getCantTipo(2) + "<br>" +
     * "<b>Audio:</b> " + getCantTipo(3) + "<br>" +
     * "<b>Cantidad Total:</b> " + cantTotal + "<br>" +
     * "<b>SubAlbum:</b> " + subAlbumesToString().replaceAll("\n", "<br>") +
     * "</html>";
     * }
     */

    public String subAlbumesToString() {
        StringBuilder temp = new StringBuilder();
        for (Album album : subAlbumes) {
            temp.append(album.toString());
            temp.append("\n"); // Add a line break between sub-albums
        }
        return temp.toString();
    }

    @Override
    public String toString() {
        return "<html>" +
                "<b>Album:</b> " + nombre + "<br>" +
                "<b>Publicaciones:</b> <br>" + publicacionesToString().replaceAll("\n", "<br>") +
                "<b>Imagenes:</b> " + getCantTipo(0) + "<br>" +
                "<b>Videos:</b> " + getCantTipo(1) + "<br>" +
                "<b>Textos:</b> " + getCantTipo(2) + "<br>" +
                "<b>Audio:</b> " + getCantTipo(3) + "<br>" +
                "<b>Cantidad Total:</b> " + cantTotal + "<br>" +
                // "<b>Sub-Álbumes:</b> <br>" + subAlbumesToString().replaceAll("\n", "<br>") +
                "</html>";
    }

}
