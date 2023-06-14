package Reports;

import PrincipalClass.Album;
import PrincipalClass.Publicacion;
import Statics.Estadisticas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La clase Reports.ReportesAlbumes genera reportes sobre los álbumes de un
 * perfil de Instagram.
 */
public class ReportesAlbumes {
    private StringBuilder reporte = new StringBuilder();

    /**
     * Crea un reporte de los álbumes que cumplen con un rango de fechas.
     *
     * @param listaAlbumes La lista de álbumes a analizar.
     * @param fechaMinima  La fecha mínima del rango.
     * @param fechaMaxima  La fecha máxima del rango.
     */
    public void creaReporte(ArrayList<Album> listaAlbumes, String fechaMinima, String fechaMaxima) {
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
                reporte.append("\nCantidad de publicaciones: " + cantPub + "\tCantidad de comentarios totales: "
                        + cantComen + "\n");
                creaReporte(album.getSubAlbumes(), fechaMinima, fechaMaxima);
            }
        }
    }

    /**
     * Obtiene el reporte generado de los álbumes y sus publicaciones.
     *
     * @return el reporte generado en formato de cadena de caracteres
     */
    public String getReporte() {
        return reporte.toString();
    }

    /**
     * Valida si la fecha mínima es válida en comparación con la fecha de la última
     * publicación.
     *
     * @param estadisticas Las estadísticas del perfil de Instagram.
     * @param fMin         La fecha mínima a validar.
     * @return true si la fecha mínima es válida, false de lo contrario.
     */
    public boolean validaFechaMinima(Estadisticas estadisticas, String fMin) {
        String fechaMinima = new String();
        fechaMinima = estadisticas.getListaPublicacionesPorFecha().last().getFechaSubida();
        return (fMin.compareTo(fechaMinima) >= 0);
    }

    /**
     * Valida si la fecha máxima es válida en comparación con la fecha de la primera
     * publicación.
     *
     * @param estadisticas Las estadísticas del perfil de Instagram.
     * @param fMax         La fecha máxima a validar.
     * @return true si la fecha máxima es válida, false de lo contrario.
     */
    public boolean validaFechaMaxima(Estadisticas estadisticas, String fMax) {
        String fechaMaxima = new String();
        fechaMaxima = estadisticas.getListaPublicacionesPorFecha().first().getFechaSubida();
        return (fMax.compareTo(fechaMaxima) <= 0);
    }

    /**
     * Muestra el reporte en la pantalla.
     */
    public void reportePantalla() {
        System.out.println(getReporte());
    }

    /**
     * Genera un reporte en formato de archivo de texto (TXT).
     */
    public void reporteTXT() {
        try {
            File file = new File("Reporte Albumes.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter fichero = new FileWriter(file);
            fichero.write(getReporte());
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
