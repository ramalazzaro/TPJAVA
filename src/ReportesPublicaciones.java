import java.util.SortedSet;
import java.util.TreeSet;

public class ReportesPublicaciones {
    private SortedSet<Publicacion> listaAudio = new TreeSet<>(new MGComparator());
    private SortedSet<Publicacion> listaImagen = new TreeSet<>(new MGComparator());
    private SortedSet<Publicacion> listaTexto = new TreeSet<>(new MGComparator());
    private SortedSet<Publicacion> listaVideo = new TreeSet<>(new MGComparator());

    public void creaReportePublicaciones(PerfilInstagram perfilInstagram) {
        for (Publicacion publicacion: perfilInstagram.getListaPublicaciones()) {
            switch (publicacion.getClass().getName()) {
                case "Imagen" -> listaImagen.add(publicacion);
                case "Video" -> listaVideo.add(publicacion);
                case "Texto" -> listaTexto.add(publicacion);
                case "Audio" -> listaAudio.add(publicacion);
            }
        }
    }
    public void muestraListaTexto () {
        for (Publicacion publicacion: listaTexto) {
            System.out.println(publicacion.toString());
        }
    }
    public void muestraListaAudio (){
        for (Publicacion publicacion: listaAudio) {
            System.out.println(publicacion.toString());
        }
    }
    public void muestraListaImagen (){
        for (Publicacion publicacion: listaImagen) {
            System.out.println(publicacion.toString());
        }
    }
    public void muestraListaVideo (){
        for (Publicacion publicacion: listaVideo) {
            System.out.println(publicacion.toString());
        }
    }

    public void muestraCantPublicacionesAudio () {
        System.out.println("CANTIDAD DE AUDIOS: " + listaAudio.size());
    }
    public void muestraCantPublicacionesImagen () {
        System.out.println("CANTIDAD DE IMAGENES: " + listaImagen.size());
    }
    public void muestraCantPublicacionesTexto () {
        System.out.println("CANTIDAD DE TEXTOS: " + listaTexto.size());
    }
    public void muestraCantPublicacionesVideo () {
        System.out.println("CANTIDAD DE VIDEOS: " + listaVideo.size());
    }

    public void muestraPromMGAudio () {
        int i = 0;
        for (Publicacion publicacion: listaAudio) {
            i += publicacion.getCantidadMG();
        }
        System.out.println("PROMEDIO ME GUSTA: " + i/listaAudio.size());
    }
    public void muestraPromMGTexto () {
        int i = 0;
        for (Publicacion publicacion: listaTexto) {
            i += publicacion.getCantidadMG();
        }
        System.out.println("PROMEDIO ME GUSTA: " + i/listaTexto.size());
    }
    public void muestraPromMGImagen () {
        int i = 0;
        for (Publicacion publicacion: listaImagen) {
            i += publicacion.getCantidadMG();
        }
        System.out.println("PROMEDIO ME GUSTA: " + i/listaImagen.size());
    }
    public void muestraPromMGVideo () {
        int i = 0;
        for (Publicacion publicacion: listaVideo) {
            i += publicacion.getCantidadMG();
        }
        System.out.println("PROMEDIO ME GUSTA: " + i/listaVideo.size());
    }


}


