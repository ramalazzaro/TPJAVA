import java.util.SortedSet;
import java.util.TreeSet;

public class ReportesPublicaciones {

    private SortedSet<Publicacion> listaAudio = new TreeSet<>(new MGComparator());
    private SortedSet<Publicacion> listaImagen = new TreeSet<>(new MGComparator());
    private SortedSet<Publicacion> listaTexto = new TreeSet<>(new MGComparator());
    private SortedSet<Publicacion> listaVideo = new TreeSet<>(new MGComparator());

    private StringBuilder salida = new StringBuilder();

    public void creaReportePublicaciones(PerfilInstagram perfilInstagram) { // no guarda todas
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
            salida.setLength(0);
            salida.append(publicacion.toString());
            System.out.println(salida.toString());
            //System.out.println(publicacion.toString());
        }
    }
    public void muestraListaAudio (){
        for (Publicacion publicacion: listaAudio) {
            salida.setLength(0);
            salida.append(publicacion.toString());
            System.out.println(salida.toString());
            //System.out.println(publicacion.toString());
        }
    }
    public void muestraListaImagen (){
        for (Publicacion publicacion: listaImagen) {
            salida.setLength(0);
            salida.append(publicacion.toString());
            System.out.println(salida.toString());
            //System.out.println(publicacion.toString());
        }
    }
    public void muestraListaVideo (){
        for (Publicacion publicacion: listaVideo) {
            salida.setLength(0);
            salida.append(publicacion.toString());
            System.out.println(salida.toString());
            //System.out.println(publicacion.toString());
        }
    }

    public void muestraCantPublicacionesAudio () {
        salida.setLength(0);
        salida.append("CANTIDAD DE AUDIOS: ").append(listaAudio.size());
        System.out.println(salida.toString());
        //System.out.println("CANTIDAD DE AUDIOS: " + listaAudio.size());
    }
    public void muestraCantPublicacionesImagen () {
        salida.setLength(0);
        salida.append("CANTIDAD DE IMAGENES: ").append(listaImagen.size());
        System.out.println(salida.toString());
        //System.out.println("CANTIDAD DE iMAGENES: " + listaImagen.size());
    }
    public void muestraCantPublicacionesTexto () {
        salida.setLength(0);
        salida.append("CANTIDAD DE TEXTOS: ").append(listaTexto.size());
        System.out.println(salida.toString());
        //System.out.println("CANTIDAD DE TEXTOS: " + listaTexto.size());
    }
    public void muestraCantPublicacionesVideo () {
        salida.setLength(0);
        salida.append("CANTIDAD DE VIDEOS: ").append(listaVideo.size());
        System.out.println(salida.toString());
        //System.out.println("CANTIDAD DE VIDEOS: " + listaVideo.size());
    }

    public void muestraPromMGAudio () {
        int i = 0;
        for (Publicacion publicacion: listaAudio) {
            i += publicacion.getCantidadMG();
        }
        salida.setLength(0);
        salida.append("PROMEDIO ME GUSTA: ").append(i/listaAudio.size());
        System.out.println(salida.toString());
        //System.out.println("PROMEDIO ME GUSTA: " + i/listaAudio.size());
    }
    public void muestraPromMGTexto () {
        int i = 0;
        for (Publicacion publicacion: listaTexto) {
            i += publicacion.getCantidadMG();
        }
        salida.setLength(0);
        salida.append("PROMEDIO ME GUSTA: ").append(i/listaTexto.size());
        System.out.println(salida.toString());
        //System.out.println("PROMEDIO ME GUSTA: " + i/listaTexto.size());
    }
    public void muestraPromMGImagen () {
        int i = 0;
        for (Publicacion publicacion: listaImagen) {
            i += publicacion.getCantidadMG();
        }
        salida.setLength(0);
        salida.append("PROMEDIO ME GUSTA: ").append(i/listaImagen.size());
        System.out.println(salida.toString());
        //System.out.println("PROMEDIO ME GUSTA: " + i/listaImagen.size());
    }
    public void muestraPromMGVideo () {
        int i = 0;
        for (Publicacion publicacion: listaVideo) {
            i += publicacion.getCantidadMG();
        }
        salida.setLength(0);
        salida.append("PROMEDIO ME GUSTA: ").append(i/listaVideo.size());
        System.out.println(salida.toString());
        //System.out.println("PROMEDIO ME GUSTA: " + i/listaVideo.size());
    }


}


