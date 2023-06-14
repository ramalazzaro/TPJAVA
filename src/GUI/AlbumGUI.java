package GUI;

import PrincipalClass.Album;
import PrincipalClass.PerfilInstagram;
import PrincipalClass.Publicacion;

import javax.swing.*;
import java.awt.*;

public class AlbumGUI extends JFrame {
    private JPanel albumPanel;

    public AlbumGUI(PerfilInstagram perfil) {
        createUI(perfil);
        displayAlbum();
        updateAlbumList(perfil);
    }

    private void createUI(PerfilInstagram perfilInstagram) {
        setTitle("Lista de Álbumes");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        albumPanel = new JPanel();
        albumPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JScrollPane scrollPane = new JScrollPane(albumPanel);
        scrollPane.setPreferredSize(new Dimension(780, 560));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton createAlbumButton = new JButton("Crear Álbum");
        createAlbumButton.addActionListener(e -> createAlbum(perfilInstagram));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(createAlbumButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    }

    public void updateAlbumList(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll(); // Clear the existing albums

        int numRows = perfilInstagram.getListaAlbumes().size();
        albumPanel.setLayout(new GridLayout(numRows, 2, 10, 10));

        for (Album album : perfilInstagram.getListaAlbumes()) {
            JButton albumButton = new JButton(album.toString());
            albumButton.addActionListener(e -> new IndividualAlbumGUI(album, perfilInstagram));

            // Create the album button and delete button
            JButton deleteAlbumButton = new JButton("Eliminar Álbum");
            deleteAlbumButton.addActionListener(e -> {
                perfilInstagram.removeAlbum(album);
                updateAlbumList(perfilInstagram);
            });

            // Create the publications panel
            JPanel publicationsPanel = new JPanel();
            publicationsPanel.setLayout(new GridLayout(album.getPublicaciones().size(), 1, 10, 10));

            // Add the publication buttons
            for (Publicacion publicacion : album.getPublicaciones()) {
                JPanel publicationEntryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel publicationLabel = new JLabel(
                        publicacion.getClass().getSimpleName() + "\t" + publicacion.getNombre());
                JButton deletePublicationButton = new JButton("-");
                deletePublicationButton.setPreferredSize(new Dimension(20, 20));
                deletePublicationButton.addActionListener(e -> {
                    album.deletePublicacion(publicacion);
                    updateAlbumList(perfilInstagram);
                });
                publicationEntryPanel.add(publicationLabel);
                publicationEntryPanel.add(deletePublicationButton);
                publicationsPanel.add(publicationEntryPanel);
            }

            // Add the components to the album panel
            albumPanel.add(albumButton);
            albumPanel.add(deleteAlbumButton);
            albumPanel.add(publicationsPanel);
        }

        albumPanel.revalidate(); // Update the albumPanel
        albumPanel.repaint();
    }

    public void createAlbum(PerfilInstagram perfilInstagram) {
        JDialog dialog = new JDialog(this, "Crear Álbum", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        JTextField albumNameField = new JTextField();
        dialog.add(albumNameField, BorderLayout.NORTH);

        JList publicationList = new JList(perfilInstagram.getListaPublicaciones().toArray(new Publicacion[0]));
        publicationList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(publicationList);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            String albumName = albumNameField.getText().trim();
            if (albumName.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Debe ingresar un nombre para el álbum",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Album album = new Album(albumName);
                Object[] selectedObjects = publicationList.getSelectedValues();
                for (Object object : selectedObjects) {
                    if (object instanceof Publicacion) {
                        album.addPublicacion((Publicacion) object);
                    }
                }
                perfilInstagram.addAlbum(album);
                // Since you have created a new album, you should refresh your album's UI here.
                dialog.dispose();
                refreshGUI(perfilInstagram);
            }
        });
        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public void refreshGUI(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll(); // Clear the existing albums

        for (Album album : perfilInstagram.getListaAlbumes()) {
            JButton albumButton = new JButton(album.toString());
            albumButton.addActionListener(e -> new IndividualAlbumGUI(album, perfilInstagram));

            JButton deleteButton = new JButton("Eliminar Álbum");
            deleteButton.addActionListener(e -> {
                perfilInstagram.removeAlbum(album);
                refreshGUI(perfilInstagram);
            });

            JPanel albumEntryPanel = new JPanel(new FlowLayout());
            albumEntryPanel.add(albumButton);
            albumEntryPanel.add(deleteButton);
            albumPanel.add(albumEntryPanel);
        }

        albumPanel.revalidate(); // Update the albumPanel
        albumPanel.repaint();
    }

    private void displayAlbum() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            pack();
        });
    }
}
