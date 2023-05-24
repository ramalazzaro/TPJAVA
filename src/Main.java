public class Main {
    public static void main(String[] args) {
        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./datos.xml");

        // Luego de agregar las publicaciones, puedes llamar a los métodos en la instancia de PerfilInstagram, por ejemplo, para mostrar las publicaciones:
        //perfilInstagram.mostrarPublicaciones();

        ListadoPublicaciones listadoPublicaciones = new ListadoPublicaciones();
        listadoPublicaciones.creaListadoPublicaciones(perfilInstagram);
        listadoPublicaciones.muestraCantPublicacionesAudio();
        listadoPublicaciones.muestraPromMGAudio();
        listadoPublicaciones.muestraListaAudio();
        listadoPublicaciones.muestraCantPublicacionesTexto();
        listadoPublicaciones.muestraPromMGTexto();
        listadoPublicaciones.muestraListaTexto();
        listadoPublicaciones.muestraCantPublicacionesImagen();
        listadoPublicaciones.muestraPromMGImagen();
        listadoPublicaciones.muestraListaImagen();
        listadoPublicaciones.muestraCantPublicacionesVideo();
        listadoPublicaciones.muestraPromMGVideo();
        listadoPublicaciones.muestraListaVideo();
    }
}

