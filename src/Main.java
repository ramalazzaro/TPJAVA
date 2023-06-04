import java.io.IOException;
import java.util.ArrayList;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) throws IOException{
        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./datos.xml");
        //Luego de agregar las publicaciones, puedes llamar a los métodos en la instancia de PerfilInstagram, por ejemplo, para mostrar las publicaciones:
        //perfilInstagram.mostrarPublicaciones();

        perfilInstagram.addAlbum("Prueba");
        perfilInstagram.addAlbum("PRUEBAMAYUS");

        for (Album album: perfilInstagram.getListaAlbumes()) {
            album.agregarPublicacion(perfilInstagram.getListaPublicaciones().first());
            Album album1 = new Album("Subalbum");
            album1.agregarPublicacion(perfilInstagram.getListaPublicaciones().last());
            album.agregarSubAlbum(album1);
            System.out.println(album.toString());
        }

//
//        Estadisticas estadisticas = new Estadisticas();
//
//        estadisticas.creaListaPorFecha(perfilInstagram);

        //estadisticas.mostrarPublicaciones(perfilInstagram);
        //estadisticas.LikesPorAño(Estadisticas.getListaPublicacionesPorFecha());
        //estadisticas.cantPublicacionesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        //estadisticas.cantLikesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        //estadisticas.publicacionesConMasLikes(perfilInstagram.getListaPublicaciones());
        //System.out.println("Pub mas likes:");
        //estadisticas.mostrarPublicaciones(estadisticas.getLista5PublicacionesMasLikes());


//        ReportesPublicaciones reportesPublicaciones = new ReportesPublicaciones();

//        reportesPublicaciones.creaReportePublicaciones(perfilInstagram);
//        reportesPublicaciones.reporteEnPantalla();
//        reportesPublicaciones.reporteTXT();

        // Luego de generar reportes, se lanza la interfaz gráfica con la instancia de
        // PerfilInstagram
//        SwingUtilities.invokeLater(() -> {
//            InstagramGUI gui = new InstagramGUI(perfilInstagram);
//            gui.setVisible(true);
//        });

    }
}
