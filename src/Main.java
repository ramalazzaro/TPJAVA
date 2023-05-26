import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./datos.xml");

        //Luego de agregar las publicaciones, puedes llamar a los métodos en la instancia de PerfilInstagram, por ejemplo, para mostrar las publicaciones:
        perfilInstagram.mostrarPublicaciones();

        Estadisticas estadisticas = new Estadisticas();

        estadisticas.creaListaPorFecha(perfilInstagram);
        //estadisticas.mostrarPublicaciones(perfilInstagram);
        //estadisticas.LikesPorAño(Estadisticas.getListaPublicacionesPorFecha());
        //estadisticas.cantPublicacionesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        //estadisticas.cantLikesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        //estadisticas.publicacionesConMasLikes(perfilInstagram.getListaPublicaciones());
        //System.out.println("Pub mas likes:");
        //estadisticas.mostrarPublicaciones(estadisticas.getLista5PublicacionesMasLikes());


        /*
         * ReportesPublicaciones reportesPublicaciones = new ReportesPublicaciones();
         * reportesPublicaciones.creaListadoPublicaciones(perfilInstagram);
         * reportesPublicaciones.muestraCantPublicacionesAudio();
         * reportesPublicaciones.muestraPromMGAudio();
         * reportesPublicaciones.muestraListaAudio();
         * reportesPublicaciones.muestraCantPublicacionesTexto();
         * reportesPublicaciones.muestraPromMGTexto();
         * reportesPublicaciones.muestraListaTexto();
         * reportesPublicaciones.muestraCantPublicacionesImagen();
         * reportesPublicaciones.muestraPromMGImagen();
         * reportesPublicaciones.muestraListaImagen();
         * reportesPublicaciones.muestraCantPublicacionesVideo();
         * reportesPublicaciones.muestraPromMGVideo();
         * reportesPublicaciones.muestraListaVideo();
         */

        // Luego de generar reportes, se lanza la interfaz gráfica con la instancia de
        // PerfilInstagram
//        SwingUtilities.invokeLater(() -> {
//            InstagramGUI gui = new InstagramGUI(perfilInstagram);
//            gui.setVisible(true);
//        });

        System.out.println("aaaaaaaaaa");
        ReportesPublicaciones reportesPublicaciones = new ReportesPublicaciones();
        reportesPublicaciones.creaReportePublicaciones(perfilInstagram);
        reportesPublicaciones.muestraListaAudio();
        reportesPublicaciones.muestraCantPublicacionesAudio();
        reportesPublicaciones.muestraPromMGAudio();
        System.out.println("aaaaaaaaaa");
        reportesPublicaciones.muestraListaTexto();
        reportesPublicaciones.muestraCantPublicacionesTexto();
        System.out.println("aaaaaaaaaa");
        reportesPublicaciones.muestraListaImagen();
        reportesPublicaciones.muestraCantPublicacionesImagen();

//        ReportesAlbumes reportesAlbumes = new ReportesAlbumes();
//        reportesAlbumes.creaReporteAlbumes(perfilInstagram,estadisticas);
        
    }
}
