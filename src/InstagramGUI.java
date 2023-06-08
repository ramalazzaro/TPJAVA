import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InstagramGUI extends JFrame {
    private PerfilInstagram perfilInstagram;
    private JPanel publicacionesPanel;
    private JButton logoutButton; // Añade esto en la lista de componentes de la clase
    private ActionListener logoutListener; // Añade esto para gestionar el evento de cierre de sesión

    public InstagramGUI(PerfilInstagram perfilInstagram) {
        this.perfilInstagram = perfilInstagram;
        createUI();
        addPublicaciones();
        createLogoutButton();
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
        this.publicacionesPanel.setLayout(new GridLayout(0, 6, 10, 10));
        scrollPane.setViewportView(this.publicacionesPanel);
    }

    private void createLogoutButton() {
        logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(e -> {
            // Esto se ejecutará cuando se haga clic en el botón de cierre de sesión
            if (logoutListener != null) {
                logoutListener.actionPerformed(e);
            }
            dispose(); // Cierra la ventana actual
        });

        // Layout para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alinea el botón a la derecha
        buttonPanel.add(logoutButton);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
    }

    public void setLogoutListener(ActionListener listener) {
        this.logoutListener = listener;
    }

    private void addPublicaciones() {
        System.out.println("Número de publicaciones: " + this.perfilInstagram.getListaPublicaciones().size());
        for (Publicacion publicacion : this.perfilInstagram.getListaPublicaciones()) {
            String publicacionTexto = "<html><body style='width: 200px'>" +
                    "<b style='font-size:14px; color:#1b95e0'>Clase:</b> " + publicacion.getClass().getSimpleName() +
                    "<br><b style='font-size:14px; color:#1b95e0'>Fecha Subida:</b> " + publicacion.getFechaSubida() +
                    "<br><b style='font-size:14px; color:#1b95e0'>Cantidad MG:</b> " + publicacion.getCantidadMG();

            if (publicacion instanceof Texto) {
                Texto texto = (Texto) publicacion;
                publicacionTexto += "<br><b style='font-size:14px; color:#1b95e0'>Contenido:</b> "
                        + texto.getContenido();
            }

            publicacionTexto += "<br>...</body></html>";
            JLabel publicacionLabel = new JLabel(publicacionTexto);
            publicacionLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            this.publicacionesPanel.add(publicacionLabel);
        }
        this.pack();
    }

}