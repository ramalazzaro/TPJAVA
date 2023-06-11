import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * La clase ReportesPublicaciones genera reportes sobre las publicaciones de un perfil de Instagram.
 */

public class ReportesPublicaciones {
    // Variables de instancia para almacenar las listas de publicaciones y el StringBuilder para generar la salida
    private SortedSet<Audio> listaAudio = new TreeSet<>(new MGComparator());
    private SortedSet<Imagen> listaImagen = new TreeSet<>(new MGComparator());
    private SortedSet<Texto> listaTexto = new TreeSet<>(new MGComparator());
    public SortedSet<Video> listaVideo = new TreeSet<>(new MGComparator());

    private StringBuilder salida = new StringBuilder();

    // Métodos privados para obtener las listas y los promedios de Me Gusta para cada tipo de publicación.

    /**
     * Crea un reporte de las publicaciones del perfil de Instagram.
     *
     * @param perfilInstagram El perfil de Instagram del cual generar el reporte.
     */

    public void creaReportePublicaciones(PerfilInstagram perfilInstagram) {
        for (Publicacion publicacion : perfilInstagram.getListaPublicaciones()) {
            if (publicacion instanceof Imagen) {
                listaImagen.add((Imagen) publicacion);
            } else if (publicacion instanceof Video) {
                listaVideo.add((Video) publicacion);
            } else if (publicacion instanceof Texto) {
                listaTexto.add((Texto) publicacion);
            } else if (publicacion instanceof Audio) {
                listaAudio.add((Audio) publicacion);
            }
        }
    }
    /**
     * Obtiene la lista de publicaciones de tipo Audio.
     *
     * @return La lista de publicaciones de tipo Audio.
     */
    private String getListaTexto() {
        salida.setLength(0);
        for (Publicacion publicacion : listaTexto) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida.toString();
    }

    private String getListaAudio() {
        salida.setLength(0);
        for (Publicacion publicacion : listaAudio) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida.toString();
    }
    /**
     * Obtiene la lista de publicaciones de tipo Imagen.
     *
     * @return La lista de publicaciones de tipo Imagen.
     */
    private String getListaImagen() {
        salida.setLength(0);
        for (Publicacion publicacion : listaImagen) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida.toString();
    }
    /**
     * Obtiene la lista de publicaciones de tipo Video.
     *
     * @return La lista de publicaciones de tipo Video.
     */
    private String getListaVideo() {
        salida.setLength(0);
        for (Publicacion publicacion : listaVideo) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida.toString();
    }
    /**
     * Obtiene el promedio de Me Gusta para las publicaciones de tipo Audio.
     *
     * @return El promedio de Me Gusta para las publicaciones de tipo Audio.
     */
    private String  getPromMGAudio() {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion : listaAudio) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: " + i / listaAudio.size());
        return salida.toString();
    }
    /**
     * Obtiene el promedio de Me Gusta para las publicaciones de tipo Texto.
     *
     * @return El promedio de Me Gusta para las publicaciones de tipo Texto.
     */
    private String  getPromMGTexto() {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion : listaTexto) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: " + i / listaTexto.size());
        return salida.toString();
    }
    /**
     * Obtiene el promedio de Me Gusta para las publicaciones de tipo Imagen.
     *
     * @return El promedio de Me Gusta para las publicaciones de tipo Imagen.
     */
    private String getPromMGImagen() {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion : listaImagen) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: " + i / listaImagen.size());
        return salida.toString();
    }
    /**
     * Obtiene el promedio de Me Gusta para las publicaciones de tipo Video.
     *
     * @return El promedio de Me Gusta para las publicaciones de tipo Video.
     */
    private String getPromMGVideo() {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion : listaVideo) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: " + i / listaVideo.size());
        return salida.toString();
    }


    /**
     * Genera un reporte en formato de String.
     * El reporte incluye las listas de publicaciones, la cantidad de publicaciones y el promedio de Me Gusta por tipo de publicación.
     */

    public String getReporte() {
        return "IMAGENES:\n" + getListaImagen() + "CANTIDAD DE IMAGENES: " + listaImagen.size() + "\n" + getPromMGImagen() + "\n" +
                "\nVIDEOS:\n" + getListaVideo() + "CANTIDAD DE VIDEOS: " + listaVideo.size() + "\n" + getPromMGVideo() + "\n" +
                "\nAUDIOS:\n" + getListaAudio() + "CANTIDAD DE AUDIOS: " + listaAudio.size() + "\n" + getPromMGAudio() + "\n" +
                "\nTEXTOS:\n" + getListaTexto() + "CANTIDAD DE TEXTOS: " + listaTexto.size() + "\n" + getPromMGTexto() + "\n";
    }

    /**
     * Genera un reporte en formato de archivo de texto (TXT).
     * El reporte incluye las listas de publicaciones, la cantidad de publicaciones y el promedio de Me Gusta por tipo de publicación.
     */

    public void reporteTXT() {
        try {
            File file = new File("Reporte publicaciones.txt");
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
