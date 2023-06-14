package PrincipalClass;

import GUI.*;
import GUI.LoginGUI;
import Reports.ReportesPublicaciones;

import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    private static Runnable loginAndLaunchInstagram;
    public static void main(String[] args) {
        PerfilInstagram perfilIG = new PerfilInstagram();
        try {
            FileInputStream fileIn = new FileInputStream("datos.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            perfilIG = (PerfilInstagram) objectIn.readObject();
            objectIn.close();
            fileIn.close();

            // Realiza las operaciones necesarias con el objeto recuperado
            System.out.println("El objeto se ha recuperado correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            perfilIG = new PerfilInstagram();
            ReadXMLFile readXMLFile = new ReadXMLFile(perfilIG);
            readXMLFile.parseXML("TPJAVA/datos.xml");
            e.printStackTrace();
        }

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        PerfilInstagram perfilInstagram = perfilIG;

        loginAndLaunchInstagram = () -> {
            LoginGUI login = new LoginGUI();
            login.setListener(() -> {

                perfilInstagram.mostrarPublicaciones();

                ReportesPublicaciones reporte = new ReportesPublicaciones();
                reporte.creaReportePublicaciones(perfilInstagram);

                SwingUtilities.invokeLater(() -> {
                    InstagramGUI gui = new InstagramGUI(perfilInstagram, reporte);
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