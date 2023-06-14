package GUI;

import PrincipalClass.PerfilInstagram;
import PrincipalClass.Publicacion;
import PrincipalClass.Album;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IndividualAlbumGUI extends JFrame {
    private Album album;
    private PerfilInstagram perfilInstagram;
    private JPanel albumPanel;

    public IndividualAlbumGUI(Album album, PerfilInstagram perfilInstagram) {
        albumPanel = new JPanel();
        this.album = album;
        this.perfilInstagram = perfilInstagram;

        setTitle(album.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addPublicationButton = new JButton("Agregar Publicaciones");
        addPublicationButton.addActionListener(e -> addPublicationsToAlbum(album, perfilInstagram));

        JButton createSubAlbumButton = new JButton("Crear Sub-Álbum");
        createSubAlbumButton.addActionListener(e -> createSubAlbum(album, perfilInstagram));

        JPanel panel = new JPanel();
        panel.add(addPublicationButton);
        panel.add(createSubAlbumButton);

        getContentPane().add(panel);

        setVisible(true);
    }

    public void addPublicationsToAlbum(Album album, PerfilInstagram perfilInstagram) {
        JDialog dialog = new JDialog(this, "Agregar Publicaciones", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        List<Publicacion> availablePublications = new ArrayList<>(perfilInstagram.getListaPublicaciones());
        availablePublications.removeAll(album.getPublicaciones());

        JList publicationList = new JList(availablePublications.toArray(new Publicacion[0]));
        publicationList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(publicationList);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            Object[] selectedObjects = publicationList.getSelectedValues();
            for (Object object : selectedObjects) {
                if (object instanceof Publicacion) {
                    album.addPublicacion((Publicacion) object);
                }
            }
            // Since you have added publications to album, you should refresh your album's
            // UI here.
            dialog.dispose();
            refreshGUI(perfilInstagram);
        });
        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public void removePublicacion(Publicacion publicacion) {
        album.deletePublicacion(publicacion);
    }

    public void refreshGUI(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll(); // Remove old components
        // Rebuild the albumPanel
        for (Publicacion publicacion : album.getPublicaciones()) {
            JLabel publicationLabel = new JLabel(publicacion.toString());
            publicationLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JButton deleteButton = new JButton("Eliminar PrincipalClass.Publicacion");
            deleteButton.addActionListener(e -> {
                album.deletePublicacion(publicacion);
                refreshGUI(perfilInstagram);
            });

            JPanel publicationEntryPanel = new JPanel(new BorderLayout());
            publicationEntryPanel.add(publicationLabel, BorderLayout.CENTER);
            publicationEntryPanel.add(deleteButton, BorderLayout.EAST);

            albumPanel.add(publicationEntryPanel);
        }

        this.getContentPane().revalidate(); // Inform that contents have changed
        this.getContentPane().repaint(); // Redraw contents
    }

    public void createSubAlbum(Album parentAlbum, PerfilInstagram perfilInstagram) {
        // Create a dialog to enter the sub-album name and select the publications
        JDialog dialog = new JDialog(this, "Crear Sub-Álbum", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        // Create a text field for entering the sub-album name
        JTextField albumNameField = new JTextField();
        dialog.add(albumNameField, BorderLayout.NORTH);

        // Create a list to display the available publications
        JList<Publicacion> publicationList = new JList<>(
                perfilInstagram.getListaPublicaciones().toArray(new Publicacion[0]));
        publicationList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(publicationList);
        scrollPane.setPreferredSize(new Dimension(350, 200)); // Set a preferred size for the scroll pane
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Create a button to save the selected publications and close the dialog
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            String albumName = albumNameField.getText().trim(); // Trim leading and trailing spaces
            if (albumName.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Debe ingresar un nombre para el sub-álbum",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                List<Publicacion> selectedPublications = publicationList.getSelectedValuesList();
                Album album = new Album(albumName);
                for (Publicacion publicacion : selectedPublications) {
                    album.addPublicacion(publicacion);
                }
                parentAlbum.addSubAlbum(album);
                refreshGUI(perfilInstagram);
                dialog.dispose();
            }
        });
        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public void updateAlbumList(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll(); // Clear the existing albums

        for (Album album : perfilInstagram.getListaAlbumes()) {
            JLabel albumLabel = new JLabel(
                    album.toString());
            albumLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JButton addPublicationsButton = new JButton("Agregar Publicaciones");
            addPublicationsButton.addActionListener(e -> addPublicationsToAlbum(album,
                    perfilInstagram));

            JButton deleteButton = new JButton("Eliminar Álbum");
            deleteButton.addActionListener(e -> {
                perfilInstagram.removeAlbum(album);
                updateAlbumList(perfilInstagram);
            });

            JPanel albumEntryPanel = new JPanel(new BorderLayout());
            albumEntryPanel.add(albumLabel, BorderLayout.CENTER);
            albumEntryPanel.add(addPublicationsButton, BorderLayout.WEST);
            albumEntryPanel.add(deleteButton, BorderLayout.EAST);

            albumPanel.add(albumEntryPanel);
        }

        albumPanel.revalidate(); // Update the albumPanel
        albumPanel.repaint();
    }

    public void displayAlbum() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            pack();
        });
    }
}
