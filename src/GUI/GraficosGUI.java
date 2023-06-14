package GUI;

import PrincipalClass.Publicacion;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;

public class GraficosGUI extends JFrame {
    public void graficoCantPubPorTipo(HashMap<String,Integer> mapCantTipo){
        // Fuente de Datos
        DefaultPieDataset data = new DefaultPieDataset();

        for(String clave:mapCantTipo.keySet()) data.setValue(clave+" "+mapCantTipo.get(clave),mapCantTipo.get(clave));

        // Creando el Grafico
        JFreeChart grafico = ChartFactory.createPieChart(
                "Cantidad de publicaciones de cada tipo",
                data,
                true,
                true,
                false);

        // Mostrar Grafico
        ChartFrame frame = new ChartFrame("Cantidad publicaciones por tipo", grafico);
        frame.pack();
        frame.setVisible(true);
    }
    public void graficoLikesPorAño(HashMap<Integer,Integer> MapLikesPorAño){
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for(Integer clave:MapLikesPorAño.keySet()){
            datos.setValue(MapLikesPorAño.get(clave),"Likes",clave);
        }
        JFreeChart graficoBarrasLikesPorAño = ChartFactory.createBarChart3D(
                "Likes por año",
                "Año",
                "Likes",
                datos,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("Likes por año", graficoBarrasLikesPorAño);
        frame.pack();
        frame.setVisible(true);
    }
    public void graficoLikesPorTipo(int vecLikesPorTipo[]){
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        datos.setValue(vecLikesPorTipo[0], "Likes", "PrincipalClass.Imagen");
        datos.setValue(vecLikesPorTipo[1], "Likes", "PrincipalClass.Video");
        datos.setValue(vecLikesPorTipo[2], "Likes", "PrincipalClass.Audio");
        datos.setValue(vecLikesPorTipo[3], "Likes", "PrincipalClass.Texto");


        JFreeChart graficoBarrasLikesPorTipo = ChartFactory.createBarChart3D(
                "Cantidad de likes por tipo de publicación",
                "Tipo",
                "Likes",
                datos,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("Likes por tipo de publicacion", graficoBarrasLikesPorTipo);
        frame.pack();
        frame.setVisible(true);
    }
    public void grafico5PubMasLikes(SortedSet<Publicacion> lista5PubMasLikes){
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        for(Publicacion pub:lista5PubMasLikes){
            datos.setValue(pub.getCantidadMG(),"Likes",pub.getNombre()+"\n"+pub.getClass().getSimpleName());
        }
        JFreeChart grafico5PubMasLikes = ChartFactory.createBarChart(
                "Las 5 publicaciones con más likes",
                "Publicaciones",
                "Likes",
                datos,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("Publicaciones com más likes", grafico5PubMasLikes);
        frame.pack();
        frame.setVisible(true);
    }
}
