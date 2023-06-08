import javax.swing.*;
import java.awt.*;

public class InstagramGUI extends JFrame {
    private PerfilInstagram perfilInstagram;
    private JPanel publicacionesPanel;

    public InstagramGUI(PerfilInstagram perfilInstagram) {
        this.perfilInstagram = perfilInstagram;
        createUI();
        addPublicaciones();
    }

    private void createUI() {
        // configuración de la ventana principal
        setTitle("Instagram Profile");
        setSize(800, 600); // tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // creamos un panel de desplazamiento en caso de que las publicaciones sean
        // demasiadas
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        this.publicacionesPanel = new JPanel();
        this.publicacionesPanel.setLayout(new GridLayout(0, 6, 10, 10)); // the last two parameters are hgap (horizontal
                                                                         // gap) and vgap (vertical gap) respectively
        scrollPane.setViewportView(this.publicacionesPanel);
    }

    private void addPublicaciones() {
        System.out.println("Número de publicaciones: " + this.perfilInstagram.getListaPublicaciones().size());
        for (Publicacion publicacion : this.perfilInstagram.getListaPublicaciones()) {
            String publicacionTexto = "<html><body style='width: 200px'>" +
                    "<b style='font-size:14px; color:#1b95e0'>Clase:</b> " + publicacion.getClass().getSimpleName();

            if (publicacion instanceof Texto) {
                Texto texto = (Texto) publicacion;
                publicacionTexto += "<br><b style='font-size:14px; color:#1b95e0'>Contenido:</b> "
                        + texto.getContenido();
            } else if (publicacion instanceof Audio) {
                Audio audio = (Audio) publicacion;

                publicacionTexto += "<br><b style='font-size:14px; color:#1b95e0'>Nombre:</b> "
                        + audio.getNombre() +
                        "<br><b style='font-size:14px; color:#1b95e0'>FechaSubida:</b> " + audio.getFechaSubida() +
                        "<br><b style='font-size:14px; color:#1b95e0'>CantidadMG:</b> " + audio.getCantidadMG();

            } else if (publicacion instanceof Video) {
                Video video = (Video) publicacion;

                publicacionTexto += "<br><b style='font-size:14px; color:#1b95e0'>Contenido:</b> "
                        + video.getNombre() +
                        "<br><b style='font-size:14px; color:#1b95e0'>FechaSubida:</b> " + video.getFechaSubida() +
                        "<br><b style='font-size:14px; color:#1b95e0'>CantidadMG:</b> " + video.getCantidadMG();

            } else if (publicacion instanceof Imagen) {
                Imagen imagen = (Imagen) publicacion;

                publicacionTexto += "<br><b style='font-size:14px; color:#1b95e0'>Contenido:</b> "
                        + imagen.getNombre() +
                        "<br><b style='font-size:14px; color:#1b95e0'>FechaSubida:</b> " + imagen.getFechaSubida() +
                        "<br><b style='font-size:14px; color:#1b95e0'>CantidadMG:</b> " + imagen.getCantidadMG();
            }

            publicacionTexto += "<br>...</body></html>";
            JLabel publicacionLabel = new JLabel(publicacionTexto);
            publicacionLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            this.publicacionesPanel.add(publicacionLabel);
        }
        this.pack();
    }

}