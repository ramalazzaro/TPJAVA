import javax.swing.*;
import java.awt.*;

public class ReportGUI extends JFrame {

        public ReportGUI(ReportesPublicaciones reporte) {
                setTitle("Reporte");
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setSize(800, 600);
                setLocationRelativeTo(null);

                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);
                getContentPane().add(scrollPane, BorderLayout.CENTER);

                // Add a back button
                JButton btnBack = new JButton("Atrás");
                btnBack.addActionListener(e -> {
                        // Close the report GUI
                        dispose();
                });
                getContentPane().add(btnBack, BorderLayout.SOUTH);

                // Generate the report string
                StringBuilder sb = new StringBuilder();
                sb.append("IMAGENES:\n");
                sb.append(reporte.getListaImagen()).append("\n")
                                .append(reporte.getCantPublicacionesImagen()).append("\n")
                                .append(reporte.getPromMGImagen()).append("\n");
                sb.append("\nVIDEOS:\n");
                sb.append(reporte.getListaVideo()).append("\n")
                                .append(reporte.getCantPublicacionesVideo()).append("\n")
                                .append(reporte.getPromMGVideo()).append("\n");
                sb.append("\nAUDIOS:\n");
                sb.append(reporte.getListaAudio()).append("\n")
                                .append(reporte.getCantPublicacionesAudio()).append("\n")
                                .append(reporte.getPromMGAudio()).append("\n");
                sb.append("\nTEXTOS:\n");
                sb.append(reporte.getListaTexto()).append("\n")
                                .append(reporte.getCantPublicacionesTexto()).append("\n")
                                .append(reporte.getPromMGTexto()).append("\n");

                // Set the report string to the text area
                textArea.setText(sb.toString());
        }
}
