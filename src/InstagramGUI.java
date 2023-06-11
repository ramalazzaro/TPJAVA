
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InstagramGUI extends JFrame {
    private PerfilInstagram perfilInstagram;
    private ReportesPublicaciones reporte;
    private JPanel publicacionesPanel;
    private JButton logoutButton, reportButton;
    private ActionListener logoutListener;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextArea reporteArea;

    private JPanel graphsPanel;

    public InstagramGUI(PerfilInstagram perfilInstagram, ReportesPublicaciones reporte) {
        this.perfilInstagram = perfilInstagram;
        this.reporte = reporte;
        createUI();
        addPublicaciones();
        createLogoutButton();
        showPublicaciones();
    }

    private void createUI() {
        setTitle("Instagram Profile");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.mainPanel = new JPanel(new BorderLayout());

        // Create the panels for publicaciones and reporte
        publicacionesPanel = new JPanel();
        publicacionesPanel.setLayout(new GridLayout(0, 1, 10, 10));

        reporteArea = new JTextArea();
        reporteArea.setEditable(false);

        // Create the buttons
        this.reportButton = new JButton("Ver reporte");
        this.reportButton.addActionListener(e -> toggleView());

        // Create the logout button
        logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(e -> {
            if (logoutListener != null) {
                logoutListener.actionPerformed(e);
            }
            dispose();
        });

        // Create the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(reportButton);
        buttonPanel.add(logoutButton);

        // Add the button panel to the JFrame
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER); // Add mainPanel to the JFrame

        // Call showPublicaciones() after initializing the necessary components
        showPublicaciones();
    }

    private void createLogoutButton() {
        logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(e -> {
            if (logoutListener != null) {
                logoutListener.actionPerformed(e);
            }
            dispose();
        });
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

    private void toggleView() {
        if (reportButton.getText().equals("Ver reporte")) {
            showReporte();
        } else {
            showPublicaciones();
        }
    }

    private void showReporte() {
        System.out.println("Switching to reporte");
        mainPanel.removeAll(); // Remove any previously added components from the mainPanel

        reporteArea.setText(reporte.getReporte());

        mainPanel.add(new JScrollPane(reporteArea), BorderLayout.CENTER); // Add reporteArea in a JScrollPane to
                                                                          // mainPanel

        reportButton.setText("Ver Publicaciones");
        // Remove the current ActionListener before adding a new one
        for (ActionListener al : reportButton.getActionListeners()) {
            reportButton.removeActionListener(al);
        }
        reportButton.addActionListener(e -> showPublicaciones());

        mainPanel.revalidate(); // Update the panel
        mainPanel.repaint();
    }

    private void showPublicaciones() {
        System.out.println("Switching to publicaciones");
        mainPanel.removeAll(); // Remove any previously added components from the mainPanel

        mainPanel.add(new JScrollPane(publicacionesPanel), BorderLayout.CENTER); // Add publicacionesPanel to mainPanel

        reportButton.setText("Ver Reporte");
        // Remove the current ActionListener before adding a new one
        for (ActionListener al : reportButton.getActionListeners()) {
            reportButton.removeActionListener(al);
        }
        reportButton.addActionListener(e -> showReporte());

        mainPanel.revalidate(); // Update the panel
        mainPanel.repaint();
    }
}
