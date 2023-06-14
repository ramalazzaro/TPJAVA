package PrincipalClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La clase Album representa un álbum que contiene publicaciones y subálbumes.
 * Permite agregar, eliminar y obtener información sobre las publicaciones y subálbumes que se encuentran en ella.
 * Además, realiza un seguimiento de la cantidad de tipos diferentes de publicaciones.
 *
 * La clase también proporciona métodos para obtener información detallada sobre el álbum, incluyendo
 * el nombre, la lista de publicaciones, la lista de subálbumes, la cantidad de cada tipo de publicación
 * y la cantidad total de publicaciones.
 */
public class Album implements Serializable {
    private String nombre;
    private ArrayList<Publicacion> publicaciones;
    private ArrayList<Album> subAlbumes;
    int[] cantTipo = new int[4]; // Índice 0: Imagen, Índice 1: Video, Índice 2: Texto, Índice 3: Audio
    int cantTotal = 0;

    /**
     * Crea un nuevo objeto Album con el nombre especificado.
     *
     * @param nombre El nombre del álbum.
     */
    public Album(String nombre) {
        this.nombre = nombre;
        this.publicaciones = new ArrayList<>();
        this.subAlbumes = new ArrayList<>();
        for (int i = 0; i < cantTipo.length; i++) {
            this.cantTipo[i] = 0;
        }
    }
    /**
     * Incrementa la cantidad de publicaciones del mismo tipo en el álbum.
     *
     * @param publicacion La publicación a agregar. Se obtiene su tipo utilizando el método getClass().
     */
    private void addCantTipo(Publicacion publicacion) {
        switch (publicacion.getClass().getName()) {
            case "Imagen" -> cantTipo[0]++;
            case "Video" -> cantTipo[1]++;
            case "Texto" -> cantTipo[2]++;
            case "Audio" -> cantTipo[3]++;
        }
    }
    /**
     * Decrementa la cantidad de publicaciones del mismo tipo en el álbum.
     *
     * @param publicacion La publicación a remover. Se obtiene su tipo utilizando el método getClass().
     */
    private void removeCantTipo(Publicacion publicacion) {
        switch (publicacion.getClass().getName()) {
            case "Imagen" -> cantTipo[0]--;
            case "Video" -> cantTipo[1]--;
            case "Texto" -> cantTipo[2]--;
            case "Audio" -> cantTipo[3]--;
        }
    }
    /**
     * Agrega una publicación al álbum.
     * Incrementa la cantidad total de publicaciones y la cantidad de publicaciones del mismo tipo.
     *
     * @param publicacion La publicación a agregar.
     */
    public void addPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
        addCantTipo(publicacion);
        cantTotal++;
    }
    /**
     * Elimina una publicación del álbum.
     * Decrementa la cantidad total de publicaciones y la cantidad de publicaciones del mismo tipo.
     *
     * @param publicacion La publicación a eliminar.
     */
    public void removePublicacion(Publicacion publicacion) {
        publicaciones.remove(publicacion);
        removeCantTipo(publicacion);
        cantTotal--;
    }

    /**
     * Agrega un subálbum al álbum.
     *
     * @param subAlbum El subálbum a agregar.
     */
    public void addSubAlbum(Album subAlbum) {
        subAlbumes.add(subAlbum);
    }
    /**
     * Elimina un subálbum del álbum.
     *
     * @param subAlbum El subálbum a eliminar.
     */
    public void removeSubAlbum(Album subAlbum) {
        subAlbumes.remove(subAlbum);
    }
    /**
     * Obtiene el nombre del álbum.
     *
     * @return El nombre del álbum.
     */
    public String getNombre() {
        return this.nombre;
    }
    /**
     * Obtiene la lista de publicaciones del álbum.
     *
     * @return La lista de publicaciones del álbum.
     */
    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }
    /**
     * Obtiene la lista de subálbumes del álbum.
     *
     * @return La lista de subálbumes del álbum.
     */
    public ArrayList<Album> getSubAlbumes() {
        return subAlbumes;
    }
    /**
     * Obtiene la cantidad de un tipo específico de publicación en el álbum.
     *
     * @param i El índice del tipo de publicación. Índice 0: Imagen, Índice 1: Video, Índice 2: Texto, Índice 3: Audio.
     * @return La cantidad de publicaciones del tipo especificado.
     */
    public int getCantTipo(int i) {
        return cantTipo[i];
    }
    /**
     * Obtiene la cantidad total de publicaciones en el álbum.
     *
     * @return La cantidad total de publicaciones en el álbum.
     */
    public int getCantTotal() {
        return cantTotal;
    }
    /**
     * Convierte las publicaciones del álbum a una representación en forma de cadena.
     *
     * @return Una cadena que representa las publicaciones del álbum.
     */
    public String publicacionesToString() {
        StringBuilder temp = new StringBuilder();
        for (Publicacion publicacion : publicaciones) {
            temp.append(publicacion.getNombre()).append("\n");
        }
        return temp.toString();
    }
    /**
     * Convierte los subálbumes del álbum a una representación en forma de cadena.
     *
     * @return Una cadena que representa los subálbumes del álbum.
     */
    public String subAlbumesToString() {
        StringBuilder temp = new StringBuilder();
        for (Album album : subAlbumes) {
            temp.append(album.toString());
            temp.append("\n");
        }
        return temp.toString();
    }
    /**
     * Devuelve una representación en forma de cadena del álbum.
     *
     * @return Una representación en forma de cadena del álbum.
     */
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
                "</html>";
    }
}

