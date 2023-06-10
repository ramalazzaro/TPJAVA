import java.util.SortedSet;
import java.util.TreeSet;

public class ReportesAlbumes {
    private StringBuilder salida = new StringBuilder();

    public void creaReporteAlbumes(PerfilInstagram perfilInstagram, Estadisticas estadisticas) {

        String fecha;
        int cantPub;
        int cantComen = 0;

        String fechaMinima = new String();
        fechaMinima = estadisticas.getListaPublicacionesPorFecha().first().getFechaSubida();
        String fechaMaxima = new String();
        fechaMaxima = estadisticas.getListaPublicacionesPorFecha().last().getFechaSubida();

        // Ingresa fechas a recorrer desde interfaz grafica HAY QUE VALIDARLAS

        for (Album album : perfilInstagram.getListaAlbumes()) {
            cantPub = album.getPublicaciones().size();
            for (Publicacion publicacion : album.getPublicaciones()) {
                String fechaSubida = publicacion.getFechaSubida();
                if (fechaSubida.compareTo(fechaMinima) > 0 && fechaSubida.compareTo(fechaMaxima) < 0) {
                    cantComen += publicacion.getComentarios().size();
                    System.out.println(publicacion.toString());
                }
            }

            // recorrerAlbum(album, fechaMinima, fechaMaxima, cantPub, cantComen);

            salida.append("Album: " + album.getNombre() + "\tCantidad de publicaciones: " + cantPub
                    + "\tCantidad de comentarios totales: " + cantComen + "\n");
            System.out.println(salida);

            for (Album subAlbum : album.getSubAlbumes()) {
                recorrerAlbum(subAlbum, fechaMinima, fechaMaxima, cantPub, cantComen);
            }
        }

    }

    private void recorrerAlbum(Album album, String fechaMinima, String fechaMaxima, int cantPub, int cantComen) {
        for (Publicacion publicacion : album.getPublicaciones()) {
            String fecha = publicacion.getFechaSubida();
            if ((fecha.compareTo(fechaMinima) > 0) && (fecha.compareTo(fechaMaxima) < 0)) {
                cantPub++;
            }
            for (Comentario comentario : publicacion.getComentarios()) {
                cantComen++;
            }
        }

        for (Album subAlbum : album.getSubAlbumes()) {
            recorrerAlbum(subAlbum, fechaMinima, fechaMaxima, cantPub, cantComen);
        }
    }

}
