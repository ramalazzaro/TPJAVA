package GUI;

import PrincipalClass.Publicacion;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.HashMap;
import java.util.SortedSet;

/**
 * Clase que representa la interfaz de usuario para generar gráficos relacionados con las publicaciones de Instagram.
 */
public class GraficosGUI extends JFrame {
    /**
     * Genera un gráfico de torta que muestra la cantidad de publicaciones de cada tipo.
     *
     * @param mapCantTipo un mapa que contiene la cantidad de publicaciones por tipo.
     */
    public void graficoCantPubPorTipo(HashMap<String, Integer> mapCantTipo) {
        // Fuente de Datos
        DefaultPieDataset data = new DefaultPieDataset();

        for (String clave : mapCantTipo.keySet())
            data.setValue(clave + " " + mapCantTipo.get(clave), mapCantTipo.get(clave));

        // Creando el Gráfico
        JFreeChart grafico = ChartFactory.createPieChart(
                "Cantidad de publicaciones de cada tipo",
                data,
                true,
                true,
                false);

        // Mostrar el Gráfico
        ChartFrame frame = new ChartFrame("Cantidad publicaciones por tipo",
                grafico);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Genera un gráfico de barras que muestra la cantidad de likes por año.
     *
     * @param MapLikesPorAño un mapa que contiene la cantidad de likes por año.
     */
    public void graficoLikesPorAño(HashMap<Integer, Integer> MapLikesPorAño) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for (Integer clave : MapLikesPorAño.keySet()) {
            datos.setValue(MapLikesPorAño.get(clave), "Likes", clave);
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

    /**
     * Genera un gráfico de barras que muestra la cantidad de likes por tipo de publicación.
     *
     * @param vecLikesPorTipo un vector que contiene la cantidad de likes por tipo de publicación.
     */
    public void graficoLikesPorTipo(int vecLikesPorTipo[]) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        datos.setValue(vecLikesPorTipo[0], "Likes", "Imagen");
        datos.setValue(vecLikesPorTipo[1], "Likes", "Video");
        datos.setValue(vecLikesPorTipo[2], "Likes", "Audio");
        datos.setValue(vecLikesPorTipo[3], "Likes", "Texto");


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
        ChartFrame frame = new ChartFrame("Likes por tipo de publicacion",
                graficoBarrasLikesPorTipo);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Genera un gráfico de barras que muestra las 5 publicaciones con más likes.
     *
     * @param lista5PubMasLikes un conjunto ordenado que contiene las 5 publicaciones con más likes.
     */
    public void grafico5PubMasLikes(SortedSet<Publicacion> lista5PubMasLikes) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        for (Publicacion pub : lista5PubMasLikes) {
            datos.setValue(pub.getCantidadMG(), "Likes", pub.getNombre() + "\n" + pub.getClass().getSimpleName());
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
        ChartFrame frame = new ChartFrame("Publicaciones com más likes",
                grafico5PubMasLikes);
        frame.pack();
        frame.setVisible(true);
    }
}