import java.util.List;

public class Album {
    private List<Album> listaAlbumSub;
    private List<Publicacion> listaAlbumPublicaciones;
    private List<Integer> listaCantTipo;

    //FALTA: anade listaPub y listaSub (si no hay elementos, crearla y agregarla)
    //al anadir publicacion agregar automaticamente a listaCantTipo

    public int getCantidadAcumuladaMG() {
        int x = new int;
        for(Publicacion publicacion: listaAlbumPublicaciones){
            x += publicacion.getCantidadMG();
        }
        return x;
    }

    public int[] getCantPublicaciones() {
        return new int[]{listaAlbumPublicaciones.size()};
    }

}
