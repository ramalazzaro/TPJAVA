package GUI;

import PrincipalClass.PerfilInstagram;
import PrincipalClass.Publicacion;
import PrincipalClass.Album;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndividualAlbumGUI extends JFrame {
    private Album album;
    private PerfilInstagram perfilInstagram;
    private JPanel albumPanel;
    private GridLayout albumPanelLayout;
    private Map<Album, IndividualAlbumGUI> subAlbumWindowsMap;


    public IndividualAlbumGUI(Album album, PerfilInstagram perfilInstagram) {
        albumPanelLayout = new GridLayout(0, 1, 10, 10);
        albumPanel = new JPanel();
        albumPanel.setLayout(albumPanelLayout);
        subAlbumWindowsMap = new HashMap<>();

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

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(albumPanel, BorderLayout.CENTER);

        updateAlbumList(perfilInstagram);
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
            updateAlbumList(perfilInstagram);
        });

        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
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
                updateAlbumList(perfilInstagram);
                dialog.dispose();
            }
        });
        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public void updateAlbumList(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll(); // Limpiamos los álbumes existentes

        // Primero, mostramos las publicaciones
        for (Publicacion publicacion : album.getPublicaciones()) {
            JPanel pubPanel = new JPanel();
            JLabel publicationLabel = new JLabel(publicacion.getClass().getSimpleName()+" "+publicacion.getNombre());

            pubPanel.add(publicationLabel,BorderLayout.WEST);

            JButton elimPubButton = new JButton("Eliminar publicación");
            elimPubButton.addActionListener(e -> {
                album.removePublicacion(publicacion);
                updateAlbumList(perfilInstagram);
            });
            pubPanel.add(elimPubButton, BorderLayout.EAST);

            albumPanel.add(pubPanel,BorderLayout.LINE_START);
        }

        // Después, mostramos los sub-álbumes
        for (Album subAlbum : album.getSubAlbumes()) {
            JPanel subAlbumPanel = new JPanel(new BorderLayout());
            JButton subAlbumButton = new JButton(subAlbum.toString());
            subAlbumButton.setPreferredSize(new Dimension(350, 200));
            //subAlbumButton.addActionListener(e -> new IndividualAlbumGUI(subAlbum, perfilInstagram));
            subAlbumPanel.add(subAlbumButton, BorderLayout.WEST);

            subAlbumButton.addActionListener(e -> {
                IndividualAlbumGUI subAlbumWindow = new IndividualAlbumGUI(subAlbum, perfilInstagram);
                subAlbumWindowsMap.put(subAlbum, subAlbumWindow);
            });

            JButton deleteButton = new JButton("Eliminar Sub-Álbum");
            deleteButton.addActionListener(e -> {
                album.removeSubAlbum(subAlbum);
                closeSubAlbumWindows(subAlbum);
                updateAlbumList(perfilInstagram);
            });
            subAlbumPanel.add(deleteButton, BorderLayout.EAST);

            albumPanel.add(subAlbumPanel,BorderLayout.NORTH);
        }
        albumPanel.revalidate(); // Actualizamos el albumPanel
        albumPanel.repaint(); // Repintamos el panel
    }
    private void closeSubAlbumWindows(Album subAlbum) { // MODIFICATION HERE
        IndividualAlbumGUI window = subAlbumWindowsMap.get(subAlbum);
        if (window != null) {
            for (Album childAlbum : subAlbum.getSubAlbumes()) {
                window.closeSubAlbumWindows(childAlbum);
            }
            window.dispose();
        }
    }
}
