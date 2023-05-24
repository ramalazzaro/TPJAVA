import java.util.TreeSet;

public class ReportesAlbumes {
    private static Estadisticas estadisticas = new Estadisticas();


    public void creaReporteAlbumes(PerfilInstagram perfilInstagram) {
        String fecha;
        int cantPub;
        int cantComen;

        String fechaMaxima = estadisticas.getListaPublicacionesPorFecha().last().getFechaSubida();
        String fechaMinima = estadisticas.getListaPublicacionesPorFecha().first().getFechaSubida();

        for (Album album : perfilInstagram.getListaAlbumes()) {
            cantPub = 0;
            cantComen = 0;

            recorrerAlbum(album, fechaMinima, fechaMaxima, cantPub, cantComen);

            System.out.println("Album: " + album.getNombre() + "\tCantidad de publicaciones: " + cantPub + " Cantidad de comentarios totales: " + cantComen);
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
