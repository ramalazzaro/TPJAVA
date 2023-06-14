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
        PerfilInstagram perfilIG = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilIG);
        readXMLFile.parseXML("TPJAVA/datos.xml");

        try {
            FileInputStream fileIn = new FileInputStream("datos.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            PerfilInstagram perfilIGRec = (PerfilInstagram) objectIn.readObject();
            if(!perfilIGRec.getListaAlbumes().isEmpty())
                perfilIG.getListaAlbumes().addAll(perfilIGRec.getListaAlbumes());
            objectIn.close();
            fileIn.close();

            // Realiza las operaciones necesarias con el objeto recuperado
            System.out.println("El objeto se ha recuperado correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        PerfilInstagram perfilInstagram = perfilIG;

        Estadisticas estadisticas = new Estadisticas();
        int vecCantL[]=estadisticas.cantLikesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        for(int i=0;i<4;i++){
            System.out.println(vecCantL[i]+"\n");
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