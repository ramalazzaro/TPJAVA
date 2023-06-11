/*
import javax.swing.*;
import java.awt.*;

public class Main {
    private static Runnable loginAndLaunchInstagram;

    public static void main(String[] args) {

        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("datos.xml");

        ReportesPublicaciones reportesPublicaciones = new ReportesPublicaciones();
        reportesPublicaciones.creaReportePublicaciones(perfilInstagram);
        reportesPublicaciones.reporteTXT();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        loginAndLaunchInstagram = () -> {
            LoginGUI login = new LoginGUI();
            login.setListener(() -> {

                perfilInstagram.mostrarPublicaciones();

                ReportesPublicaciones reporte = new ReportesPublicaciones();
                reporte.creaReportePublicaciones(perfilInstagram);

                SwingUtilities.invokeLater(() -> {
                    InstagramGUI gui = new InstagramGUI(perfilInstagram, reporte);
                    gui.setLogoutListener(e -> {

                        gui.dispose();
                        SwingUtilities.invokeLater(loginAndLaunchInstagram);
                    });
                    gui.setMinimumSize(new Dimension(800, 600));
                    gui.setVisible(true);
                });
                login.dispose();
            });
            login.setVisible(true);
        };

        SwingUtilities.invokeLater(loginAndLaunchInstagram);
    }
}
*/

public class Main {

    public static void main(String[] args){

        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("TPJAVA/datos.xml");

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.creaListaPorFecha(perfilInstagram);


        Album album = new Album("AlbumPrueba");
        album.addPublicacion(perfilInstagram.getListaPublicaciones().first());
        album.addPublicacion(perfilInstagram.getListaPublicaciones().first());
        Album album1 = new Album("Subalbum");
        album1.addPublicacion(perfilInstagram.getListaPublicaciones().last());
        album.addSubAlbum(album1);
        perfilInstagram.getListaAlbumes().add(album);


        ReportesPublicaciones reportesPublicaciones = new ReportesPublicaciones();
        reportesPublicaciones.creaReportePublicaciones(perfilInstagram);
//        reportesPublicaciones.reporteTXT();
//        System.out.println(reportesPublicaciones.getReporte());
/*
        ReportesAlbumes reportesAlbumes = new ReportesAlbumes();
        String fechaMax = estadisticas.getListaPublicacionesPorFecha().first().getFechaSubida();
        String fechaMin = estadisticas.getListaPublicacionesPorFecha().last().getFechaSubida();


        if (reportesAlbumes.validaFechaMaxima(estadisticas, fechaMax) && reportesAlbumes.validaFechaMinima(estadisticas, fechaMin)) {
            reportesAlbumes.creaReporte(perfilInstagram.getListaAlbumes(), fechaMin, fechaMax);
            reportesAlbumes.reportePantalla();
            reportesAlbumes.reporteTXT();
        }
        */

        reportesPublicaciones.listaVideo.first().reproduce();

    }

}