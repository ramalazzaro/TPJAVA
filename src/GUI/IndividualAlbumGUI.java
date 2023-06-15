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

/**
 * Clase que representa la interfaz gráfica para visualizar y manipular un álbum individual en Instagram.
 */
public class IndividualAlbumGUI extends JFrame {
    private Album album;
    private PerfilInstagram perfilInstagram;
    private JPanel albumPanel;
    private GridLayout albumPanelLayout;
    private Map<Album, IndividualAlbumGUI> subAlbumWindowsMap;


    /**
     * Crea una nueva instancia de IndividualAlbumGUI.
     *
     * @param album           el álbum individual que se mostrará en la interfaz.
     * @param perfilInstagram el perfil de Instagram al que pertenece el álbum.
     */
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

    /**
     * Abre una ventana de diálogo para agregar publicaciones al álbum.
     *
     * @param album           el álbum al que se agregarán las publicaciones.
     * @param perfilInstagram el perfil de Instagram que contiene las publicaciones disponibles.
     */
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
            dialog.dispose();
            updateAlbumList(perfilInstagram);
        });

        dialog.add(saveButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * Crea un sub-álbum dentro del álbum actual.
     *
     * @param parentAlbum     el álbum padre en el que se creará el sub-álbum.
     * @param perfilInstagram el perfil de Instagram al que pertenece el álbum.
     */
    public void createSubAlbum(Album parentAlbum, PerfilInstagram perfilInstagram) {
        JDialog dialog = new JDialog(this, "Crear Sub-Álbum", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new BorderLayout());

        JTextField albumNameField = new JTextField();
        dialog.add(albumNameField, BorderLayout.NORTH);

        JList<Publicacion> publicationList = new JList<>(
                perfilInstagram.getListaPublicaciones().toArray(new Publicacion[0]));
        publicationList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(publicationList);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            String albumName = albumNameField.getText().trim();
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

    /**
     * Actualiza la lista de álbumes y publicaciones en la interfaz gráfica.
     *
     * @param perfilInstagram el perfil de Instagram al que pertenece el álbum.
     */
    public void updateAlbumList(PerfilInstagram perfilInstagram) {
        albumPanel.removeAll();

        for (Publicacion publicacion : album.getPublicaciones()) {
            JPanel pubPanel = new JPanel();
            JLabel publicationLabel = new JLabel(publicacion.getClass().getSimpleName() + " " + publicacion.getNombre());

            pubPanel.add(publicationLabel, BorderLayout.WEST);

            JButton elimPubButton = new JButton("Eliminar publicación");
            elimPubButton.addActionListener(e -> {
                album.removePublicacion(publicacion);
                updateAlbumList(perfilInstagram);
            });
            pubPanel.add(elimPubButton, BorderLayout.EAST);

            albumPanel.add(pubPanel, BorderLayout.LINE_START);
        }

        for (Album subAlbum : album.getSubAlbumes()) {
            JPanel subAlbumPanel = new JPanel(new BorderLayout());
            JButton subAlbumButton = new JButton(subAlbum.toString());
            subAlbumButton.setPreferredSize(new Dimension(350, 200));
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

            albumPanel.add(subAlbumPanel, BorderLayout.NORTH);
        }
        albumPanel.revalidate();
        albumPanel.repaint();
    }

    private void closeSubAlbumWindows(Album subAlbum) {
        IndividualAlbumGUI window = subAlbumWindowsMap.get(subAlbum);
        if (window != null) {
            for (Album childAlbum : subAlbum.getSubAlbumes()) {
                window.closeSubAlbumWindows(childAlbum);
            }
            window.dispose();
        }
    }
}