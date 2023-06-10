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

                // Set the report to the text area
                textArea.setText(reporte.getReporte());
        }
}
