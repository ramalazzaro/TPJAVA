package Statics;

import Comparators.FechaComparator;
import Comparators.MGComparator;
import PrincipalClass.PerfilInstagram;
import PrincipalClass.Publicacion;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
/**
 * Clase que contiene métodos para calcular estadísticas de un perfil de Instagram.
 */
public class Estadisticas {
    private final SortedSet<Publicacion> listaPublicacionesPorFecha = new TreeSet<>(new FechaComparator());

    /**
     * Crea una lista de publicaciones ordenadas por fecha a partir de un perfil de Instagram.
     * @param perfilInstagram El perfil de Instagram del cual obtener las publicaciones.
     */
    public void creaListaPorFecha(PerfilInstagram perfilInstagram){
        listaPublicacionesPorFecha.addAll(perfilInstagram.getListaPublicaciones());
    }
    /**
     * Obtiene la lista de publicaciones ordenadas por fecha.
     * @return La lista de publicaciones ordenadas por fecha.
     */
    public SortedSet<Publicacion> getListaPublicacionesPorFecha(){return listaPublicacionesPorFecha;}

    /**
     * Calcula la cantidad de likes por año de las publicaciones.
     * @param listaPublicacionesPorFecha La lista de publicaciones ordenadas por fecha.
     * @return Un mapa con la cantidad de likes por año.
     */
    public HashMap<Integer,Integer> LikesPorAño()
    {
        HashMap<Integer,Integer> MapCantLikesAño = new HashMap<Integer, Integer>();
        int CantLikesAño=0;
        int año=0;
        Iterator<Publicacion> publicacionIterator = listaPublicacionesPorFecha.iterator();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate Date;
        Publicacion publicacion = publicacionIterator.next();
        while(publicacionIterator.hasNext()) {
            CantLikesAño=0;
            Date = LocalDate.parse(publicacion.getFechaSubida(),formatter);
            año = Date.getYear();
            while(publicacionIterator.hasNext() && año == Date.getYear()){
                CantLikesAño += publicacion.getCantidadMG();
                publicacion = publicacionIterator.next();
                Date = LocalDate.parse(publicacion.getFechaSubida(),formatter);
            }
            MapCantLikesAño.put(año,CantLikesAño);
        }
        MapCantLikesAño.put(año,CantLikesAño + publicacion.getCantidadMG());

        return MapCantLikesAño;
    }

    /**
     * Calcula la cantidad de publicaciones por tipo.
     * @param listaPublicaciones La lista de publicaciones.
     * @return Un mapa con la cantidad de publicaciones por tipo de publicación.
     */
    public HashMap<String,Integer> cantPublicacionesDeCadaTipo(SortedSet<Publicacion> listaPublicaciones)
    {
        HashMap<String,Integer> MapCant = new HashMap<String, Integer>();
        MapCant.put("PrincipalClass.Imagen",0);MapCant.put("PrincipalClass.Video",0);MapCant.put("PrincipalClass.Audio",0);MapCant.put("PrincipalClass.Texto",0);

        for(Publicacion publicacion : listaPublicaciones) {
            switch (publicacion.getClass().getName()) {
                case "PrincipalClass.Imagen" -> MapCant.put("PrincipalClass.Imagen", MapCant.get("PrincipalClass.Imagen") + 1);
                case "PrincipalClass.Video" -> MapCant.put("PrincipalClass.Video", MapCant.get("PrincipalClass.Video") + 1);
                case "PrincipalClass.Audio" -> MapCant.put("PrincipalClass.Audio", MapCant.get("PrincipalClass.Audio") + 1);
                case "PrincipalClass.Texto" -> MapCant.put("PrincipalClass.Texto", MapCant.get("PrincipalClass.Texto") + 1);
            }
        }
        return MapCant;
    }

    /**
     * Calcula la cantidad de likes por cada tipo de publicación.
     * @param listaPublicaciones La lista de publicaciones.
     * @return Un vector con la cantidad de likes por cada tipo de publicación(pos:0 = PrincipalClass.Imagen - pos:1 = PrincipalClass.Video - pos:2 = PrincipalClass.Audio - pos:3 = PrincipalClass.Texto).
     */
    public int[] cantLikesDeCadaTipo(SortedSet<Publicacion> listaPublicaciones)
    {
        System.out.println("Cant Likes por cada tipo: ");
        int vecCantL[] = {0,0,0,0};
        for(Publicacion publicacion : listaPublicaciones) {
            switch (publicacion.getClass().getName()) {
                case "PrincipalClass.Imagen" -> vecCantL[0] += publicacion.getCantidadMG();
                case "PrincipalClass.Video" -> vecCantL[1] += publicacion.getCantidadMG();
                case "PrincipalClass.Audio" -> vecCantL[2] += publicacion.getCantidadMG();
                case "PrincipalClass.Texto" -> vecCantL[3] += publicacion.getCantidadMG();
            }
        }
        return vecCantL;
    }

    /**
     * Obtiene las publicaciones con más likes.
     * @param listaPublicaciones La lista de publicaciones.
     * @return Una lista con las 5 publicaciones con más likes.
     */
    public SortedSet<Publicacion> publicacionesConMasLikes(SortedSet<Publicacion> listaPublicaciones)
    {
        //int likesMax = 0;
        SortedSet<Publicacion> lista5PublicacionesMasLikes = new TreeSet<>(new MGComparator());
        Iterator<Publicacion> publicacionIterator = listaPublicaciones.iterator();
        int i = 0;
        int cantPublicacionesConMasLikes=5;
        while (publicacionIterator.hasNext() && i < cantPublicacionesConMasLikes) {
            lista5PublicacionesMasLikes.add(publicacionIterator.next());
            i++;
        }
        //Iterator<PrincipalClass.Publicacion> publicacionLikesIterator = lista5PublicacionesMasLikes.iterator();
        Publicacion publicacion;
        while (publicacionIterator.hasNext()) {
            publicacion = publicacionIterator.next();
            if (publicacion.getCantidadMG() > lista5PublicacionesMasLikes.last().getCantidadMG()) {
                lista5PublicacionesMasLikes.add(publicacion);
                lista5PublicacionesMasLikes.remove(lista5PublicacionesMasLikes.last());
            }
        }
        return lista5PublicacionesMasLikes;
    }
}