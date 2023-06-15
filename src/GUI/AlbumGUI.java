package GUI;

import PrincipalClass.Album;
import PrincipalClass.PerfilInstagram;
import PrincipalClass.Publicacion;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la interfaz de usuario para mostrar y gestionar álbumes de un perfil de Instagram.
 */
public class AlbumGUI extends JFrame {
    private JPanel albumPanel;
    private GridLayout albumPanelLayout;

    /**
     * Constructor de la clase AlbumGUI.
     *
     * @param perfil el perfil de Instagram para el cual se mostrarán los álbumes.
     */
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

        albumPanelLayout = new GridLayout(0, 1, 10, 10);
        albumPanel = new JPanel();
        albumPanel.setLayout(albumPanelLayout);

        JScrollPane scrollPane = new JScrollPane(albumPanel);
        scrollPane.setPreferredSize(new Dimension(780, 560));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton createAlbumButton = new JButton("Crear Álbum");
        createAlbumButton.addActionListener(e -> createAlbum(perfilInstagram));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(createAlbumButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Actualiza la lista de álbumes en la interfaz de usuario.
     *
     * @param perfilInstagram el perfil de Instagram del cual se obtendrán los álbumes.
     */
    public void updateAlbumList(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll(); // Limpiar los álbumes existentes

        int numRows = perfilInstagram.getListaAlbumes().size();
        albumPanel.setLayout(new GridLayout(numRows, 1, 10, 10));

        for (Album album : perfilInstagram.getListaAlbumes()) {
            JPanel albumEntryPanel = new JPanel();
            albumEntryPanel.setLayout(new BoxLayout(albumEntryPanel, BoxLayout.Y_AXIS));

            JButton albumButton = new JButton(album.toString());
            albumButton.addActionListener(e -> new IndividualAlbumGUI(album, perfilInstagram));

            JButton deleteAlbumButton = new JButton("Eliminar Álbum");
            deleteAlbumButton.addActionListener(e -> {
                perfilInstagram.removeAlbum(album);
                updateAlbumList(perfilInstagram);
            });

            albumEntryPanel.add(albumButton);
            albumEntryPanel.add(deleteAlbumButton);

            // Crear el panel de publicaciones
            JPanel publicationsPanel = new JPanel();
            publicationsPanel.setLayout(new BoxLayout(publicationsPanel, BoxLayout.Y_AXIS));

            // Agregar los botones de publicación
            for (Publicacion publicacion : album.getPublicaciones()) {
                JPanel publicationEntryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel publicationLabel = new JLabel(
                        publicacion.getClass().getSimpleName() + "\t" + publicacion.getNombre());
                JButton deletePublicationButton = new JButton("-");
                deletePublicationButton.setPreferredSize(new Dimension(20, 20));
                deletePublicationButton.addActionListener(e -> {
                    album.removePublicacion(publicacion);
                    updateAlbumList(perfilInstagram);
                });
                publicationEntryPanel.add(publicationLabel);
                publicationEntryPanel.add(deletePublicationButton);
                publicationsPanel.add(publicationEntryPanel);
            }

            albumEntryPanel.add(publicationsPanel);

            // Agregar los componentes al panel de álbumes
            albumPanel.add(albumEntryPanel);
        }

        albumPanel.revalidate(); // Actualizar albumPanel
        albumPanel.repaint();
    }

    /**
     * Crea un nuevo álbum para el perfil de Instagram.
     *
     * @param perfilInstagram el perfil de Instagram al que se agregará el álbum.
     */
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
                // Dado que se ha creado un nuevo álbum, se debe actualizar la interfaz de usuario del álbum aquí.
                dialog.dispose();
                refreshGUI(perfilInstagram);
            }
        });
        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * Actualiza la interfaz de usuario del álbum.
     *
     * @param perfilInstagram el perfil de Instagram del cual se obtendrán los álbumes.
     */
    public void refreshGUI(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll(); // Limpiar los álbumes existentes

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

        albumPanel.revalidate(); // Actualizar albumPanel
        albumPanel.repaint();
    }

    private void displayAlbum() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            pack();
        });
    }
}