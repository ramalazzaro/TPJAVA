import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumGUI extends JFrame {
    private JPanel albumPanel;

    public AlbumGUI(PerfilInstagram perfil) {
        createUI(perfil);
        displayAlbum();

        JList<Album> albumList = new JList<>(perfil.getListaAlbumes().toArray(new Album[0]));
        JScrollPane albumListScrollPane = new JScrollPane(albumList);
        albumPanel.add(albumListScrollPane); // Assuming albumPanel is the correct place for it

        albumList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add the list selection listener to the album list
        albumList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        albumList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Ignore extra events
                Album selectedAlbum = albumList.getSelectedValue();
                if (selectedAlbum != null) {
                    addPublicationsToAlbum(selectedAlbum, perfil); // passing both arguments here
                }
            }
        });
    }

    public void createAlbum(PerfilInstagram perfilInstagram) {

        // Create a dialog to enter the album name and select the publications
        JDialog dialog = new JDialog(this, "Crear Álbum", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        // Create a text field for entering the album name
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
                JOptionPane.showMessageDialog(dialog, "Debe ingresar un nombre para el álbum",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                List<Publicacion> selectedPublications = publicationList.getSelectedValuesList();
                Album album = new Album(albumName);
                for (Publicacion publicacion : selectedPublications) {
                    album.addPublicacion(publicacion);
                }
                updateAlbumList(perfilInstagram);
                perfilInstagram.addAlbum(album);
                updateAlbumList(perfilInstagram); // Update the album list after saving
                dialog.dispose();
            }
        });
        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public void addPublicationsToAlbum(Album album, PerfilInstagram perfilInstagram) {

        JDialog dialog = new JDialog(this, "Agregar Publicaciones", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        List<Publicacion> availablePublications = new ArrayList<>(perfilInstagram.getListaPublicaciones());
        availablePublications.removeAll(album.getPublicaciones());

        JList<Publicacion> publicationList = new JList<>(availablePublications.toArray(new Publicacion[0]));
        publicationList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(publicationList);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            List<Publicacion> selectedPublications = publicationList.getSelectedValuesList();
            for (Publicacion publicacion : selectedPublications) {
                album.addPublicacion(publicacion);
            }
            updateAlbumList(perfilInstagram); // Update the album list after adding publications
            dialog.dispose();
        });
        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void createUI(PerfilInstagram perfilInstagram) {
        setTitle("Álbum");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        albumPanel = new JPanel();
        albumPanel.setLayout(new GridLayout(0, 1, 10, 10));

        updateAlbumList(perfilInstagram);

        for (Publicacion publicacion : perfilInstagram.getListaPublicaciones()) {
            JLabel publicacionLabel = new JLabel(publicacion.toString());
            publicacionLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            albumPanel.add(publicacionLabel);
        }

        JScrollPane scrollPane = new JScrollPane(albumPanel);
        scrollPane.setPreferredSize(new Dimension(780, 560));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton createAlbumButton = new JButton("Crear Álbum");
        createAlbumButton.addActionListener(e -> createAlbum(perfilInstagram));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(createAlbumButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    /*
     * public void updateAlbumList(PerfilInstagram perfilInstagram) {
     * albumPanel.removeAll(); // Clear the existing albums
     * 
     * for (Album album : perfilInstagram.getListaAlbumes()) {
     * JLabel albumLabel = new JLabel(
     * album.toString());
     * albumLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
     * 
     * JButton addPublicationsButton = new JButton("Agregar Publicaciones");
     * addPublicationsButton.addActionListener(e -> addPublicationsToAlbum(album,
     * perfilInstagram));
     * 
     * 
     * 
     * JButton deleteButton = new JButton("Eliminar Álbum");
     * deleteButton.addActionListener(e -> {
     * perfilInstagram.removeAlbum(album);
     * updateAlbumList(perfilInstagram);
     * });
     * 
     * JPanel albumEntryPanel = new JPanel(new BorderLayout());
     * albumEntryPanel.add(albumLabel, BorderLayout.CENTER);
     * albumEntryPanel.add(addPublicationsButton, BorderLayout.WEST);
     * albumEntryPanel.add(deleteButton, BorderLayout.EAST);
     * 
     * albumPanel.add(albumEntryPanel);
     * }
     * 
     * albumPanel.revalidate(); // Update the albumPanel
     * albumPanel.repaint();
     * }
     */

    public void updateAlbumList(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll(); // Clear the existing albums

        for (Album album : perfilInstagram.getListaAlbumes()) {
            JLabel albumLabel = new JLabel(album.toString());
            albumLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JButton addPublicationsButton = new JButton("Agregar Publicaciones");
            addPublicationsButton.addActionListener(e -> addPublicationsToAlbum(album, perfilInstagram));

            JButton createSubAlbumButton = new JButton("Crear Sub-Álbum");
            createSubAlbumButton.addActionListener(e -> createSubAlbum(album, perfilInstagram));

            JButton deleteButton = new JButton("Eliminar Álbum");
            deleteButton.addActionListener(e -> {
                perfilInstagram.removeAlbum(album);
                updateAlbumList(perfilInstagram);
            });

            JPanel albumEntryPanel = new JPanel(new BorderLayout());
            albumEntryPanel.add(albumLabel, BorderLayout.NORTH);
            albumEntryPanel.add(addPublicationsButton, BorderLayout.WEST);
            albumEntryPanel.add(createSubAlbumButton, BorderLayout.CENTER);
            albumEntryPanel.add(deleteButton, BorderLayout.EAST);

            // For each publication in the album, add a delete button
            JPanel publicationsPanel = new JPanel(new GridLayout(0, 1));
            for (Publicacion publicacion : album.getPublicaciones()) {
                JPanel publicationEntryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel publicationLabel = new JLabel(
                        publicacion.getClass().getSimpleName() + "\t" + publicacion.getNombre());
                JButton deletePublicationButton = new JButton("Eliminar");
                deletePublicationButton.addActionListener(e -> {
                    album.deletePublicacion(publicacion);
                    updateAlbumList(perfilInstagram);
                });
                publicationEntryPanel.add(publicationLabel);
                publicationEntryPanel.add(deletePublicationButton);
                publicationsPanel.add(publicationEntryPanel);
            }

            albumEntryPanel.add(publicationsPanel, BorderLayout.SOUTH);
            albumPanel.add(albumEntryPanel);
        }

        albumPanel.revalidate(); // Update the albumPanel
        albumPanel.repaint();
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
                updateAlbumList(perfilInstagram); // Update the album list after saving
                dialog.dispose();
            }
        });
        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public void removeAlbum(Album album, PerfilInstagram perfilInstagram) {
        perfilInstagram.removeAlbum(album);

    }

    private void displayAlbum() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            pack();
        });
    }
}
