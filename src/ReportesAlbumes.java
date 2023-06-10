import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class ReportesAlbumes {
    private StringBuilder reporte = new StringBuilder();

    public void creaReporte (ArrayList<Album> listaAlbumes, String fechaMinima, String fechaMaxima) {
        if (!listaAlbumes.isEmpty()) {
            int cantPub = 0;
            int cantComen = 0;

            for (Album album : listaAlbumes) {
                cantPub = album.getPublicaciones().size();
                reporte.append("\nAlbum: " + album.getNombre() + "\n\n");
                for (Publicacion publicacion : album.getPublicaciones()) {
                    String fechaSubida = publicacion.getFechaSubida();
                    if (fechaSubida.compareTo(fechaMinima) >= 0 && fechaSubida.compareTo(fechaMaxima) <= 0) {
                        cantComen += publicacion.getComentarios().size();
                        reporte.append(publicacion.toString());
                    }
                }
                reporte.append("\nCantidad de publicaciones: " + cantPub + "\tCantidad de comentarios totales: " + cantComen + "\n");
                creaReporte(album.getSubAlbumes(), fechaMinima, fechaMaxima);
            }
        }
    }

    public boolean validaFechaMinima(Estadisticas estadisticas, String fMin) {
        String fechaMinima = new String();
        fechaMinima = estadisticas.getListaPublicacionesPorFecha().last().getFechaSubida();
        return (fMin.compareTo(fechaMinima)>=0);

    }
    public boolean validaFechaMaxima(Estadisticas estadisticas, String fMax) {
        String fechaMaxima = new String();
        fechaMaxima = estadisticas.getListaPublicacionesPorFecha().first().getFechaSubida();
        return (fMax.compareTo(fechaMaxima)<=0);
    }

    public void reportePantalla () {
        System.out.println(reporte.toString());
    }

    public void reporteTXT() {
        try {
            File file = new File("Reporte Albumes.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter fichero = new FileWriter(file);
            fichero.write(reporte.toString());
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
