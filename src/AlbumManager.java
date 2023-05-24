public class AlbumManager{ //Encargada de gestionar los albumes
    private List<Album>albums;

}

public AlbumManager() {
    this.albums = new ArrayList<>();
}

public void crearAlbumNuevo(String nombre) {
    Album album = new Album(nombre);  //Crea album utilizando el constructor de la clase Album
    albums.add(album); //se agrega el álbum recién creado a la lista de álbumes gestionada
}

public void eliminarAlbum(String nombre) {
    Album albumAEliminar = null;
    for (Album album : albums) {
        if (album.getNombre().equals(nombre)) {
            albumAEliminar = album;
            break;
        }
    }
    if (albumAEliminar != null) {
        albums.remove(albumAEliminar);
    }
}

