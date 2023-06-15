package GUI;

import Interfaces.Durable;
import PrincipalClass.Publicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase ReproductorGUI representa la interfaz gráfica de un reproductor de publicaciones durables.
 */
public class ReproductorGUI {
    private JFrame reproductorFrame;
    private JLabel segundosLabel, duracionLabel;
    private JButton pausarButton;
    private JButton reanudarButton;
    private JButton finalizarButton;
    private JPanel reproductorPanel;
    private Timer timer;
    private int segundos = 0;

    /**
     * Crea una instancia de ReproductorGUI para una publicación durable.
     *
     * @param publicacion La publicación durable que se reproducirá.
     */
    public ReproductorGUI(Publicacion publicacion) {
        reproductorFrame = new JFrame("Reproductor");
        reproductorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reproductorFrame.setSize(400, 300);

        segundosLabel = new JLabel("0 segundos");
        duracionLabel = new JLabel(String.valueOf(((Durable) publicacion).getDuracion()));
        pausarButton = new JButton("Pausar");
        reanudarButton = new JButton("Reanudar");
        finalizarButton = new JButton("Finalizar");

        pausarButton.addActionListener(e -> ((Durable) publicacion).pausar());
        reanudarButton.addActionListener(e -> ((Durable) publicacion).reanudar());
        finalizarButton.addActionListener(e -> {
            ((Durable) publicacion).finalizar();
            if (timer.isRunning()) {
                timer.stop();
            }
            reproductorFrame.dispose(); // Cierra la ventana del reproductor
        });

        reproductorPanel = new JPanel();
        reproductorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        reproductorPanel.add(segundosLabel);
        reproductorPanel.add(pausarButton);
        reproductorPanel.add(reanudarButton);
        reproductorPanel.add(finalizarButton);
        reproductorPanel.add(duracionLabel);

        reproductorFrame.add(reproductorPanel, BorderLayout.CENTER);
        reproductorFrame.setVisible(true);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!((Durable) publicacion).getPausa() && !((Durable) publicacion).getFinaliza()) {
                    if (segundos <= ((Durable) publicacion).getDuracion()) {
                        segundosLabel.setText(segundos + " segundos");
                        segundos++;
                    } else {
                        ((Timer) e.getSource()).stop();
                        System.out.println("\nFin de la reproducción\n");
                        reproductorFrame.dispose(); // Cierra la ventana cuando termina la reproducción
                    }
                }
            }
        });
        timer.start();
    }
}