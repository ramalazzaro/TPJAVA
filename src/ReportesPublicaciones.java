import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class ReportesPublicaciones {
    private SortedSet<Audio> listaAudio = new TreeSet<>(new MGComparator());
    private SortedSet<Imagen> listaImagen = new TreeSet<>(new MGComparator());
    private SortedSet<Texto> listaTexto = new TreeSet<>(new MGComparator());
    private SortedSet<Video> listaVideo = new TreeSet<>(new MGComparator());

    private StringBuilder salida = new StringBuilder();

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

    public StringBuilder getListaTexto() {
        salida.setLength(0);
        for (Publicacion publicacion : listaTexto) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida;
    }

    public StringBuilder getListaAudio() {
        salida.setLength(0);
        for (Publicacion publicacion : listaAudio) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida;
    }

    public StringBuilder getListaImagen() {
        salida.setLength(0);
        for (Publicacion publicacion : listaImagen) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida;
    }

    public StringBuilder getListaVideo() {
        salida.setLength(0);
        for (Publicacion publicacion : listaVideo) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida;
    }

    public StringBuilder getCantPublicacionesAudio() {
        salida.setLength(0);
        salida.append("CANTIDAD DE AUDIOS: " + listaAudio.size());
        return salida;
    }

    public StringBuilder getCantPublicacionesImagen() {
        salida.setLength(0);
        salida.append("CANTIDAD DE IMAGENES: " + listaImagen.size());
        return salida;
    }

    public StringBuilder getCantPublicacionesTexto() {
        salida.setLength(0);
        salida.append("CANTIDAD DE TEXTOS: " + listaTexto.size());
        return salida;
    }

    public StringBuilder getCantPublicacionesVideo() {
        salida.setLength(0);
        salida.append("CANTIDAD DE VIDEOS: " + listaVideo.size());
        return salida;
    }

    public StringBuilder getPromMGAudio() {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion : listaAudio) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: " + i / listaAudio.size());
        return salida;
    }

    public StringBuilder getPromMGTexto() {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion : listaTexto) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: " + i / listaTexto.size());
        return salida;
    }

    public StringBuilder getPromMGImagen() {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion : listaImagen) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: " + i / listaImagen.size());
        return salida;
    }

    public StringBuilder getPromMGVideo() {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion : listaVideo) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: " + i / listaVideo.size());
        return salida;
    }

    public String getReporte() {
        return "IMAGENES:\n" + getListaImagen() + getCantPublicacionesImagen() + "\n" + getPromMGImagen() + "\n" +
                "\nVIDEOS:\n" + getListaVideo() + getCantPublicacionesVideo() + "\n" + getPromMGVideo() + "\n" +
                "\nAUDIOS:\n" + getListaAudio() + getCantPublicacionesAudio() + "\n" + getPromMGAudio() + "\n" +
                "\nTEXTOS:\n" + getListaTexto() + getCantPublicacionesTexto() + "\n" + getPromMGTexto() + "\n";
    }

    public void reporteTXT() {
        try {
            File file = new File("Reporte publicaciones.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter fichero = new FileWriter(file);
            fichero.write("IMAGENES:\n" + getListaImagen() + getCantPublicacionesImagen() + "\n" + getPromMGImagen() + "\n");
            fichero.write("\nVIDEOS:\n" + getListaVideo() + getCantPublicacionesVideo() + "\n" + getPromMGVideo() + "\n");
            fichero.write("\nAUDIOS:\n" + getListaAudio() + getCantPublicacionesAudio() + "\n" + getPromMGAudio() + "\n");
            fichero.write("\nTEXTOS:\n" + getListaTexto() + getCantPublicacionesTexto() + "\n" + getPromMGTexto() + "\n");
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
