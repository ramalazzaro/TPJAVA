/*import java.io.IOException;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./TPJAVA/datos.xml");
        // Luego de agregar las publicaciones, puedes llamar a los métodos en la
        // instancia de PerfilInstagram, por ejemplo, para mostrar las publicaciones:
        // perfilInstagram.showPublicaciones();
        // System.out.println(perfilInstagram.getListaPublicaciones().first().getListaComentarios().toString());

        Album album = new Album("AlbumPrueba");
        album.addPublicacion(perfilInstagram.getListaPublicaciones().first());
        Album album1 = new Album("Subalbum");
        album.addSubAlbum(album1);
        // perfilInstagram.getListaAlbumes().add(album);

        //
        Estadisticas estadisticas = new Estadisticas();
        estadisticas.creaListaPorFecha(perfilInstagram);

        // estadisticas.mostrarPublicaciones(perfilInstagram);
        // estadisticas.LikesPorAño(Estadisticas.getListaPublicacionesPorFecha());
        // estadisticas.cantPublicacionesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        // estadisticas.cantLikesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        // estadisticas.publicacionesConMasLikes(perfilInstagram.getListaPublicaciones());
        // System.out.println("Pub mas likes:");
        // estadisticas.mostrarPublicaciones(estadisticas.getLista5PublicacionesMasLikes());

        // ReportesPublicaciones reportesPublicaciones = new ReportesPublicaciones();

        // reportesPublicaciones.creaReportePublicaciones(perfilInstagram);
        // reportesPublicaciones.reporteEnPantalla();
        // reportesPublicaciones.reporteTXT();

        // ReportesAlbumes reportesAlbumes = new ReportesAlbumes();

        // reportesAlbumes.creaReporteAlbumes(perfilInstagram, estadisticas);

        // Luego de generar reportes, se lanza la interfaz gráfica con la instancia de
        // PerfilInstagram
        // SwingUtilities.invokeLater(() -> {
        // InstagramGUI gui = new InstagramGUI(perfilInstagram);
        // gui.setVisible(true);
        // });

    }
}*/

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./TPJAVA/datos.xml");

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
    }
}
