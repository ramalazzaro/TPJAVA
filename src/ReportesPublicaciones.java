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

    public void creaReportePublicaciones(PerfilInstagram perfilInstagram) { // no guarda todas
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

    private StringBuilder getListaTexto () {
        salida.setLength(0);
        for (Publicacion publicacion: listaTexto) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida;
    }
    private StringBuilder getListaAudio (){
        salida.setLength(0);
        for (Publicacion publicacion: listaAudio) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida;
    }
    private StringBuilder getListaImagen (){
        salida.setLength(0);
        for (Publicacion publicacion: listaImagen) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida;
    }
    private StringBuilder getListaVideo (){
        salida.setLength(0);
        for (Publicacion publicacion: listaVideo) {
            salida.append("\t" + publicacion.toString() + "\n");
        }
        return salida;
    }

    private StringBuilder getCantPublicacionesAudio () {
        salida.setLength(0);
        salida.append("CANTIDAD DE AUDIOS: ").append(listaAudio.size());
        return salida;
    }
    private StringBuilder getCantPublicacionesImagen () {
        salida.setLength(0);
        salida.append("CANTIDAD DE IMAGENES: ").append(listaImagen.size());
        return salida;
    }
    private StringBuilder getCantPublicacionesTexto () {
        salida.setLength(0);
        salida.append("CANTIDAD DE TEXTOS: ").append(listaTexto.size());
        return salida;
    }
    private StringBuilder getCantPublicacionesVideo () {
        salida.setLength(0);
        salida.append("CANTIDAD DE VIDEOS: ").append(listaVideo.size());
        return salida;
    }

    private StringBuilder getPromMGAudio () {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion: listaAudio) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: ").append(i/listaVideo.size());
        return salida;
    }
    private StringBuilder getPromMGTexto () {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion: listaTexto) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: ").append(i/listaVideo.size());
        return salida;
    }
    private StringBuilder getPromMGImagen () {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion: listaImagen) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: ").append(i/listaVideo.size());
        return salida;
    }
    private StringBuilder getPromMGVideo () {
        salida.setLength(0);
        int i = 0;
        for (Publicacion publicacion: listaVideo) {
            i += publicacion.getCantidadMG();
        }
        salida.append("PROMEDIO ME GUSTA: ").append(i/listaVideo.size());
        return salida;
    }

    public void reporteEnPantalla () {
        System.out.println("\nIMAGENES:\n");
        System.out.println(getListaImagen() + "\n" + getCantPublicacionesImagen() + "\n" + getPromMGImagen());
        System.out.println("\nVIDEOS:\n");
        System.out.println(getListaVideo() + "\n" + getCantPublicacionesVideo()+ "\n" + getPromMGVideo());
        System.out.println("\nAUDIOS:\n");
        System.out.println(getListaAudio() + "\n" + getCantPublicacionesAudio()+ "\n" + getPromMGAudio());
        System.out.println("\nTEXTOS:\n");
        System.out.println(getListaTexto() + "\n" + getCantPublicacionesTexto()+ "\n" + getPromMGTexto());
    }

    public void reporteTXT() {
        try (FileWriter fichero = new FileWriter("Reporte publicaciones.txt")) {
            fichero.write("IMAGENES:\n");
            fichero.write(getListaImagen() + "\n" + getCantPublicacionesImagen() + "\n" + getPromMGImagen() + "\n");
            fichero.write("\nVIDEOS:\n");
            fichero.write(getListaVideo() + "\n" + getCantPublicacionesVideo() + "\n" + getPromMGVideo() + "\n");
            fichero.write("\nAUDIOS:\n");
            fichero.write(getListaAudio() + "\n" + getCantPublicacionesAudio() + "\n" + getPromMGAudio() + "\n");
            fichero.write("\nTEXTOS:\n");
            fichero.write(getListaTexto() + "\n" + getCantPublicacionesTexto() + "\n" + getPromMGTexto() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


