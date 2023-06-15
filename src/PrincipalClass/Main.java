package PrincipalClass;

import GUI.*;
import GUI.LoginGUI;
import Reports.ReportesPublicaciones;
import Statics.Estadisticas;

import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    private static Runnable loginAndLaunchInstagram;

    public static void main(String[] args) {
        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("TPJAVA/datos.xml");

        try {
            FileInputStream fileIn = new FileInputStream("datos.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            PerfilInstagram perfilIGRec = (PerfilInstagram) objectIn.readObject();
            if (!perfilIGRec.getListaAlbumes().isEmpty()){
                perfilInstagram.getListaAlbumes().addAll(perfilIGRec.getListaAlbumes());
                System.out.println("Se recupero los albumes");
            }else
                System.out.println("No se recuperó ningun album");
            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo de datos: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        loginAndLaunchInstagram = () -> {
            LoginGUI login = new LoginGUI();
            login.setListener(() -> {

                SwingUtilities.invokeLater(() -> {
                    InstagramGUI gui = new InstagramGUI(perfilInstagram);
                    gui.setLogoutListener(e -> {

                        gui.dispose();
                        SwingUtilities.invokeLater(loginAndLaunchInstagram);
                    });
                    gui.setMinimumSize(new Dimension(800, 600));
                    gui.setVisible(true);
                });
                login.dispose();
            });
            login.setVisible(true);
        };
        SwingUtilities.invokeLater(loginAndLaunchInstagram);
    }
}