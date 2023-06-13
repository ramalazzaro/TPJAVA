import javax.swing.*;
import java.awt.*;

public class Main {
    private static Runnable loginAndLaunchInstagram;

    public static void main(String[] args) {

        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("TPJAVA/datos.xml");

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