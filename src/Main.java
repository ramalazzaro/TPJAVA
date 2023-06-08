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
    private static Runnable loginAndLaunchInstagram;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a Runnable that encapsulates the code for logging in and launching the
        // Instagram GUI
        loginAndLaunchInstagram = () -> {
            LoginGUI login = new LoginGUI();
            login.setListener(() -> {
                // After successful login, load the profile data and launch the Instagram GUI
                PerfilInstagram perfilInstagram = new PerfilInstagram();
                ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
                readXMLFile.parseXML("./TPJAVA/datos.xml");

                perfilInstagram.mostrarPublicaciones();

                SwingUtilities.invokeLater(() -> {
                    InstagramGUI gui = new InstagramGUI(perfilInstagram);
                    gui.setLogoutListener(e -> {
                        // When logging out, dispose the current Instagram GUI and start a new login
                        gui.dispose();
                        SwingUtilities.invokeLater(loginAndLaunchInstagram);
                    });
                    gui.setVisible(true);
                });
                login.dispose(); // dispose the login window after logging in
            });
            login.setVisible(true);
        };

        // Initially start with the login GUI
        SwingUtilities.invokeLater(loginAndLaunchInstagram);
    }
}
