import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./datos.xml");

//<<<<<<< HEAD
        //Luego de agregar las publicaciones, puedes llamar a los métodos en la instancia de PerfilInstagram, por ejemplo, para mostrar las publicaciones:
        //perfilInstagram.mostrarPublicaciones();
        Estadisticas estadisticas = new Estadisticas();
        // Se crea una lista ordenada por fechas solo para calcular estadísticas que lo requeriran (Ej: LikesPorMes)
        estadisticas.creaListaPorFecha(perfilInstagram.getListaPublicaciones());

        estadisticas.mostrarPublicaciones(estadisticas.getListaPublicacionesPorFecha());
        //estadisticas.LikesPorAño(Estadisticas.getListaPublicacionesPorFecha());
        //estadisticas.cantPublicacionesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        //estadisticas.cantLikesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        estadisticas.publicacionesConMasLikes(perfilInstagram.getListaPublicaciones());
        System.out.println("Pub mas likes:");
        estadisticas.mostrarPublicaciones(estadisticas.getLista5PublicacionesMasLikes());
//=======
        // Luego de agregar las publicaciones, puedes llamar a los métodos en la
        // instancia de PerfilInstagram, por ejemplo, para mostrar las publicaciones:
        perfilInstagram.mostrarPublicaciones();

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
        SwingUtilities.invokeLater(() -> {
            InstagramGUI gui = new InstagramGUI(perfilInstagram);
            gui.setVisible(true);
        });
//>>>>>>> 8fb1b35daf893df2b9ae42116d24d5c1d16531fe
    }
}
