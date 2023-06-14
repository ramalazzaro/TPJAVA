package GUI;

import Interfaces.Durable;
import Interfaces.Filtrable;
import PrincipalClass.*;
import Statics.Estadisticas;
import Reports.ReportesAlbumes;
import Reports.ReportesPublicaciones;
import GUI.AlbumGUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class InstagramGUI extends JFrame {
    private JTextArea reporteTextArea;
    private JTextField fechaMinimaTextField;
    private JTextField fechaMaximaTextField;
    private PerfilInstagram perfilInstagram;
    private ReportesPublicaciones reporte;
    private JButton albumButton;
    private ReportesPublicaciones reportesPublicaciones;
    private JPanel publicacionesPanel,reporteAlbumesPanel;
    private JButton logoutButton, reportPublicacionesButton, reportAlbumesButton, graphsButton, reportPublicacionesTextButton;
    private ActionListener logoutListener;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextArea reportePublicacionesArea;
    private JPanel estadisticasPanel;
    private JButton graphLikesPorAñoButton,graphLikesPorTipoButton,graphCantPubPorTipoButton,graph5PubMasLikesButton;

    public InstagramGUI(PerfilInstagram perfilInstagram) {
        this.perfilInstagram = perfilInstagram;
        createUI();
        addPublicaciones();
        createLogoutButton();
        createAlbumButton();
        showPublicaciones();
    }

    private void createUI() {
        setTitle("Instagram Profile");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Lógica personalizada para cuando se cierra la interfaz
                System.out.println("Interfaz cerrada");
                try {
                    FileOutputStream fileOut = new FileOutputStream("datos.ser");
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(perfilInstagram);
                    objectOut.close();
                    fileOut.close();
                    System.out.println("El objeto se ha serializado correctamente.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                super.windowClosing(e);
            }
        });
        setLocationRelativeTo(null);

        this.mainPanel = new JPanel(new BorderLayout());

        // Create the panels for publicaciones and reporte
        publicacionesPanel = new JPanel();
        publicacionesPanel.setLayout(new GridLayout(0, 1, 10, 10));

        reportePublicacionesArea = new JTextArea();
        reportePublicacionesArea.setEditable(false);
////////////////ver pub panel
        estadisticasPanel = new JPanel();
        publicacionesPanel.setLayout(new GridLayout(0, 1, 10, 10));

        reporteAlbumesPanel = new JPanel();
        publicacionesPanel.setLayout(new GridLayout(0, 1, 10, 10));



        // Create the buttons
        reportPublicacionesButton = new JButton("Ver reporte publicaciones");
        reportPublicacionesButton.addActionListener(e -> toggleView());

        reportAlbumesButton = new JButton("Ver reporte albumes");
        reportAlbumesButton.addActionListener(e -> reporteAlbumesView());

        // Create the album button
        albumButton = new JButton("Ver Álbum");
        albumButton.addActionListener(e -> showAlbumGUI());

        // Create the logout button
        logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(e -> {
            if (logoutListener != null) {
                logoutListener.actionPerformed(e);
            }
            dispose();
        });

        // Create graphs button
        this.graphsButton = new JButton("Ver estadisticas");
        this.graphsButton.addActionListener(e -> estadisticaView());

        // Create the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(reportPublicacionesButton);
        buttonPanel.add(albumButton); // Add the album button
        buttonPanel.add(logoutButton);
        buttonPanel.add(graphsButton);
        buttonPanel.add(reportAlbumesButton);

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

    private void createAlbumButton() {
        albumButton = new JButton("Ver Álbum");
        albumButton.addActionListener(e -> showAlbumGUI());
    }

    public void setLogoutListener(ActionListener listener) {
        this.logoutListener = listener;
    }

    private void addPublicaciones() {
        System.out.println("Número de publicaciones: " + this.perfilInstagram.getListaPublicaciones().size());


        for (Publicacion publicacion : this.perfilInstagram.getListaPublicaciones()) {
            String publicacionTexto = "<html><body style='width: 200px'>" +
                    "<b style='font-size:14px; color:#1b95e0'>Nombre:</b>" + publicacion.getNombre() +
                    "<br><b style='font-size:14px; color:#1b95e0'>Clase:</b> " + publicacion.getClass().getSimpleName()
                    +
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

            publicacionLabel.setLayout(new BorderLayout());
            if (publicacion instanceof Imagen || publicacion instanceof Video) {
                JButton aplicarFiltroButton = new JButton("Aplicar Filtro");
                JButton quitarFiltroButton = new JButton("Quitar Filtro");
                JLabel estadoFiltro = new JLabel(((Filtrable) publicacion).consultaFiltro());
                JPanel buttonsFiltroPanel = new JPanel();

                aplicarFiltroButton.addActionListener(e -> {
                    // Lógica para aplicar el filtro a la imagen
                    ((Filtrable) publicacion).poneFiltro();
                    estadoFiltro.setText(((Filtrable) publicacion).consultaFiltro());
                    buttonsFiltroPanel.revalidate();
                    buttonsFiltroPanel.repaint();
                });

                quitarFiltroButton.addActionListener(e -> {
                    // Lógica para quitar el filtro a la imagen
                    ((Filtrable) publicacion).sacaFiltro();
                    estadoFiltro.setText(((Filtrable) publicacion).consultaFiltro());
                    buttonsFiltroPanel.revalidate();
                    buttonsFiltroPanel.repaint();
                });

                buttonsFiltroPanel.add(aplicarFiltroButton);
                buttonsFiltroPanel.add(quitarFiltroButton);
                buttonsFiltroPanel.add(estadoFiltro);
                publicacionLabel.add(buttonsFiltroPanel, BorderLayout.EAST);
            }


            if(publicacion instanceof Audio || publicacion instanceof Video){
                JButton reproducirButton = new JButton("Reproducir");
                JPanel buttonsReproducePanel = new JPanel();
                buttonsReproducePanel.add(reproducirButton);
                publicacionLabel.add(buttonsReproducePanel, BorderLayout.PAGE_END);

                reproducirButton.addActionListener(e -> {
                    ReproductorGUI reproductor = new ReproductorGUI(publicacion);
                });
            }


            publicacionesPanel.add(publicacionLabel, BorderLayout.CENTER);

            this.publicacionesPanel.add(publicacionLabel);
        }
        this.pack();
    }

    private void toggleView() {
        if (reportPublicacionesButton.getText().equals("Ver reporte publicaciones")) {
            showReportePublicaciones();
        } else {
            showPublicaciones();
        }
    }

    private void estadisticaView() {
        if (graphsButton.getText().equals("Ver estadisticas")) {
            showEstadisticas();
        } else {
            showPublicaciones();
        }
    }

    private void reporteAlbumesView() {
        if (reportAlbumesButton.getText().equals("Ver reporte albumes")) {
            showReporteAlbumes();
        } else {
            showPublicaciones();
        }
    }

    private void showReportePublicaciones() {
        System.out.println("Switching to reporte publicaciones");
        mainPanel.removeAll(); // Remove any previously added components from the mainPanel

        ReportesPublicaciones reporte = new ReportesPublicaciones();
        reporte.creaReportePublicaciones(perfilInstagram);

        reportePublicacionesArea.setText(reporte.getReporte());

        JButton reportPubTextButton = new JButton("Generar archivo de texto");
        reportPubTextButton.addActionListener(e -> reporte.reporteTXT());

        mainPanel.add(reportPubTextButton, BorderLayout.NORTH);

        mainPanel.add(new JScrollPane(reportePublicacionesArea), BorderLayout.CENTER); // Add reporteArea in a JScrollPane to
                                                                          // mainPanel
        reportPublicacionesButton.setText("Volver");
        // Remove the current ActionListener before adding a new one
        for (ActionListener al : reportPublicacionesButton.getActionListeners()) {
            reportPublicacionesButton.removeActionListener(al);
        }
        reportPublicacionesButton.addActionListener(e -> showPublicaciones());

        mainPanel.revalidate(); // Update the panel
        mainPanel.repaint();
    }

    private void showReporteAlbumes() {
        System.out.println("Switching to reporte albumes");
        mainPanel.removeAll(); // Remove any previously added components from the mainPanel

        JPanel reporteAlbumesPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.creaListaPorFecha(perfilInstagram);

        JTextField fechaMinimaTextField = new JTextField();
        JTextField fechaMaximaTextField = new JTextField();
        JButton generarReporteButton = new JButton("Generar Reporte");

        // Diseño de la interfaz

        reporteAlbumesPanel.add(new JLabel("Fecha Mínima:"));
        reporteAlbumesPanel.add(fechaMinimaTextField);
        reporteAlbumesPanel.add(new JLabel("Fecha Máxima:"));
        reporteAlbumesPanel.add(fechaMaximaTextField);
        reporteAlbumesPanel.add(generarReporteButton);
        reporteAlbumesPanel.add(new JLabel("Ingresar fechas entre "+estadisticas.getListaPublicacionesPorFecha().last().getFechaSubida()+" y "+estadisticas.getListaPublicacionesPorFecha().first().getFechaSubida()));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new JScrollPane(reporteAlbumesPanel), BorderLayout.CENTER); // Add reporteAlbumesPanel in a JScrollPane to mainPanel



        reportAlbumesButton.setText("Volver");
        // Remove the current ActionListener before adding a new one
        for (ActionListener al : reportAlbumesButton.getActionListeners()) {
            reportAlbumesButton.removeActionListener(al);
        }
        reportAlbumesButton.addActionListener(e -> showPublicaciones());

        mainPanel.revalidate(); // Update the panel
        mainPanel.repaint();

        generarReporteButton.addActionListener(e -> {
            String fechaMinima = fechaMinimaTextField.getText();
            String fechaMaxima = fechaMaximaTextField.getText();

            ReportesAlbumes reportesAlbumes = new ReportesAlbumes();

            if(reportesAlbumes.validaFechaMinima(estadisticas,fechaMinima)&&reportesAlbumes.validaFechaMaxima(estadisticas,fechaMaxima)) {
                reportesAlbumes.creaReporte(perfilInstagram.getListaAlbumes(), fechaMinima, fechaMaxima);
                String reporte = reportesAlbumes.getReporte();
                reportesAlbumes.reportePantalla();
                // Create a new JFrame to display the report
                JFrame reportFrame = new JFrame("Reporte de Álbumes");
                reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                reportFrame.setSize(400, 300);

                JButton reportAlbTextButton = new JButton("Generar archivo de texto");
                reportAlbTextButton.addActionListener(a -> reportesAlbumes.reporteTXT());

                reportFrame.add(reportAlbTextButton, BorderLayout.NORTH);

                // Create a JTextArea to display the report
                JTextArea reportTextArea = new JTextArea(reporte);
                reportTextArea.setEditable(false);

                // Add the report JTextArea to the JFrame
                reportFrame.add(new JScrollPane(reportTextArea));

                // Set the JFrame visible
                reportFrame.setVisible(true);
            }else{
                JFrame reportFrame = new JFrame("Fecha invalida");
                reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                reportFrame.setSize(400, 300);

                // Create a JTextArea to display the report
                JTextArea reportTextArea = new JTextArea("Se ingresó una fecha erronea");
                reportTextArea.setEditable(false);

                // Add the report JTextArea to the JFrame
                reportFrame.add(new JScrollPane(reportTextArea));

                // Set the JFrame visible
                reportFrame.setVisible(true);
            }
        });
    }



    private void showPublicaciones() {
        System.out.println("Switching to publicaciones");
        mainPanel.removeAll(); // Remove any previously added components from the mainPanel

        mainPanel.add(new JScrollPane(publicacionesPanel), BorderLayout.CENTER); // Add publicacionesPanel to mainPanel

        reportPublicacionesButton.setText("Ver reporte publicaciones");
        // Remove the current ActionListener before adding a new one
        for (ActionListener al : reportPublicacionesButton.getActionListeners()) {
            reportPublicacionesButton.removeActionListener(al);
        }
        reportPublicacionesButton.addActionListener(e -> showReportePublicaciones());

        reportAlbumesButton.setText("Ver reporte albumes");
        for (ActionListener al : reportAlbumesButton.getActionListeners()) {
            reportAlbumesButton.removeActionListener(al);
        }
        reportAlbumesButton.addActionListener(e -> showReporteAlbumes());

        graphsButton.setText("Ver estadisticas");
        // Remove the current ActionListener before adding a new one
        for (ActionListener al : graphsButton.getActionListeners()) {
            graphsButton.removeActionListener(al);
        }
        graphsButton.addActionListener(e -> showEstadisticas());

        mainPanel.revalidate(); // Update the panel
        mainPanel.repaint();
    }

    private void showEstadisticas(){
        System.out.println("Switching to estadisticas");
        mainPanel.removeAll(); // Remove any previously added components from the mainPanel

        mainPanel.add(new JScrollPane(estadisticasPanel), BorderLayout.CENTER); // Add estadisticasPanel to mainPanel

        graphsButton.setText("Volver");
        // Remove the current ActionListener before adding a new one
        for (ActionListener al : graphsButton.getActionListeners()) {
            graphsButton.removeActionListener(al);
        }
        graphsButton.addActionListener(e -> showPublicaciones());

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.creaListaPorFecha(perfilInstagram);
        GraficosGUI graficosGUI = new GraficosGUI();

        graphLikesPorAñoButton = new JButton("Cantidad de Likes por año");
        graphLikesPorAñoButton.addActionListener(e -> graficosGUI.graficoLikesPorAño(estadisticas.LikesPorAño()));
        graphLikesPorTipoButton = new JButton("Cantidad de Likes por tipo de publicacion");
        graphLikesPorTipoButton.addActionListener(e -> graficosGUI.graficoLikesPorTipo(estadisticas.cantLikesDeCadaTipo(perfilInstagram.getListaPublicaciones())));
        graphCantPubPorTipoButton = new JButton("Cantidad de publicaciones por tipo");
        graphCantPubPorTipoButton.addActionListener(e -> graficosGUI.graficoCantPubPorTipo(estadisticas.cantPublicacionesDeCadaTipo(perfilInstagram.getListaPublicaciones())));
        graph5PubMasLikesButton = new JButton("Las 5 publicaciones con más likes");
        graph5PubMasLikesButton.addActionListener(e -> graficosGUI.grafico5PubMasLikes(estadisticas.publicacionesConMasLikes(perfilInstagram.getListaPublicaciones())));

        JPanel buttonPanelEst = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanelEst.add(graphLikesPorAñoButton);
        buttonPanelEst.add(graphLikesPorTipoButton);
        buttonPanelEst.add(graphCantPubPorTipoButton);
        buttonPanelEst.add(graph5PubMasLikesButton);

        estadisticasPanel.add(buttonPanelEst, BorderLayout.CENTER);


        mainPanel.revalidate(); // Update the panel
        mainPanel.repaint();
    }

    private void showAlbumGUI() {
        ArrayList<Publicacion> publicacionesList = new ArrayList<>();
        for (Album album : perfilInstagram.getListaAlbumes()) {
            publicacionesList.addAll(album.getPublicaciones());
        }
        AlbumGUI albumGUI = new AlbumGUI(perfilInstagram);
        albumGUI.updateAlbumList(perfilInstagram);
    }

}