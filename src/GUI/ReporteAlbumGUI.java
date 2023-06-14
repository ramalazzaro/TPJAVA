package GUI;

import PrincipalClass.*;
import Reports.ReportesAlbumes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReporteAlbumGUI extends JFrame {
        private JTextArea reporteTextArea;
        private JTextField fechaMinimaTextField;
        private JTextField fechaMaximaTextField;
    public void creaReporteAlbumGUI(PerfilInstagram perfilInstagram, JPanel panel) {
            // Configuración de la ventana
            setTitle("Reporte de Álbumes");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setSize(400, 300);
            setLocationRelativeTo(null);

            // Creación de componentes
            reporteTextArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(reporteTextArea);
            fechaMinimaTextField = new JTextField();
            fechaMaximaTextField = new JTextField();
            JButton generarReporteButton = new JButton("Generar Reporte");

            // Diseño de la interfaz
            setLayout(new BorderLayout());

            panel.setLayout(new GridLayout(3, 2));
            panel.add(new JLabel("Fecha Mínima:"));
            panel.add(fechaMinimaTextField);
            panel.add(new JLabel("Fecha Máxima:"));
            panel.add(fechaMaximaTextField);
            panel.add(generarReporteButton);

            add(scrollPane, BorderLayout.CENTER);
            add(panel, BorderLayout.SOUTH);

            // Acción del botón "Generar Reporte"
            generarReporteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fechaMinima = fechaMinimaTextField.getText();
                    String fechaMaxima = fechaMaximaTextField.getText();

                    ReportesAlbumes reportesAlbumes = new ReportesAlbumes();
                    // Llamar al método creaReporte con las fechas ingresadas
                    reportesAlbumes.creaReporte(perfilInstagram.getListaAlbumes(), fechaMinima, fechaMaxima);
                }
            });
    }
}
