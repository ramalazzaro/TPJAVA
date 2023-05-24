import javax.swing.*;
import java.awt.*;

public class InstagramGUI extends JFrame {
    private PerfilInstagram perfilInstagram;
    private JPanel publicacionesPanel; // Nuevo campo para el panel de publicaciones

    public InstagramGUI(PerfilInstagram perfilInstagram) {
        this.perfilInstagram = perfilInstagram;
        createUI();
        addPublicaciones(); // Llamada al nuevo método
    }

    private void createUI() {
        // configuración de la ventana principal
        setTitle("Instagram Profile");
        setSize(800, 600); // tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // para centrar la ventana

        // creamos un panel de desplazamiento en caso de que las publicaciones sean
        // demasiadas
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // creamos un panel para las publicaciones y lo agregamos a la vista del
        // scrollPane
        this.publicacionesPanel = new JPanel();
        this.publicacionesPanel.setLayout(new BoxLayout(this.publicacionesPanel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(this.publicacionesPanel);
    }

    private void addPublicaciones() {
        for (Publicacion publicacion : this.perfilInstagram.getListaPublicaciones()) {
            String publicacionTexto = "<html><b>Nombre:</b> " + publicacion.getNombre() +
                    "<br><b>Fecha:</b> " + publicacion.getFechaSubida() +
                    "<br><b>Me gusta:</b> " + publicacion.getCantidadMG() +
                    "<br>...</html>";
            JLabel publicacionLabel = new JLabel(publicacionTexto);
            this.publicacionesPanel.add(publicacionLabel);
        }
        this.pack();
    }
}
