import java.util.SortedSet;
import java.util.TreeSet;


public class ReportesAlbumes {
    private StringBuilder salida = new StringBuilder();

    public void creaReporteAlbumes(PerfilInstagram perfilInstagram,Estadisticas estadisticas) { //Se reutiliza la lista de publicaciones por fecha de la clase Estadisticas para poder validar las fechas ingresadas por el usuario
        String fecha;
        int cantPub;
        int cantComen;

        String fechaMinima = estadisticas.getListaPublicacionesPorFecha().first().getFechaSubida();
        String fechaMaxima = estadisticas.getListaPublicacionesPorFecha().last().getFechaSubida();

        for (Album album : perfilInstagram.getListaAlbumes()) {
            cantPub = 0;
            cantComen = 0;

            recorrerAlbum(album, fechaMinima, fechaMaxima, cantPub, cantComen);

            salida.append("Album: ").append(album.getNombre()).append("\tCantidad de publicaciones: ").append(cantPub).append("\tCantidad de comentarios totales: ").append(cantComen).append("\n");
            //System.out.println("Album: " + album.getNombre() + "\tCantidad de publicaciones: " + cantPub + " Cantidad de comentarios totales: " + cantComen);
            System.out.println(salida.toString());
        }
    }

    public void recorrerAlbum(Album album, String fechaMinima, String fechaMaxima, int cantPub, int cantComen) {
        for (Publicacion publicacion : album.getPublicaciones()) {
            String fecha = publicacion.getFechaSubida();
            if ((fecha.compareTo(fechaMinima) > 0) && (fecha.compareTo(fechaMaxima) < 0)) {
                cantPub++;
            }
            for (Comentario comentario : publicacion.getListaComentarios()) {
                cantComen++;
            }
        }

        for (Album subAlbum : album.getSubAlbumes()) {
            recorrerAlbum(subAlbum, fechaMinima, fechaMaxima, cantPub, cantComen);
        }
    }

}
