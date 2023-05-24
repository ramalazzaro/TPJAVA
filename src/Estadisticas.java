import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Estadisticas {
    private static SortedSet<Publicacion> listaPublicacionesPorFecha = new TreeSet<>(new FechaComparator());
    private SortedSet<Publicacion> lista5PublicacionesMasLikes = new TreeSet<>(new MeGustaComparator());

    public  void creaListaPorFecha(PerfilInstagram perfilInstagram){
        listaPublicacionesPorFecha.addAll(perfilInstagram.getListaPublicaciones());
    }
    public void mostrarPublicaciones(SortedSet<Publicacion> lista) {
        for (Publicacion publicacion : lista) {
            System.out.println(publicacion.toString());
        }
    }
    public SortedSet<Publicacion> getListaPublicacionesPorFecha(){return listaPublicacionesPorFecha;}
    public SortedSet<Publicacion> getLista5PublicacionesMasLikes(){return lista5PublicacionesMasLikes;}
    public void LikesPorAño(SortedSet<Publicacion> listaPublicaciones)
    {
        HashMap<Integer,Integer> MapCantLikesAño = new HashMap<Integer, Integer>();
        int CantLikesAño=0;
        int año=0;
        Iterator<Publicacion> publicacionIterator = listaPublicaciones.iterator();
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
        for(Integer clave:MapCantLikesAño.keySet()){System.out.println(clave+" "+MapCantLikesAño.get(clave));}
    }
    public void cantPublicacionesDeCadaTipo(SortedSet<Publicacion> listaPublicaciones)
    {
        System.out.println("Cant de publicaciones por tipo: ");
        HashMap<String,Integer> MapCant = new HashMap<String, Integer>();
        MapCant.put("Imagen",0);MapCant.put("Video",0);MapCant.put("Audio",0);MapCant.put("Texto",0);

        for(Publicacion publicacion : listaPublicaciones) {
            if("Imagen".equals(publicacion.getClass().getName()))
                MapCant.put("Imagen", MapCant.get("Imagen")+1);
            else
                if("Video".equals(publicacion.getClass().getName()))
                    MapCant.put("Video", MapCant.get("Video")+1);
                else
                    if("Audio".equals(publicacion.getClass().getName()))
                        MapCant.put("Audio", MapCant.get("Audio")+1);
                    else
                        MapCant.put("Texto", MapCant.get("Texto")+1);
        }
        for(String clave:MapCant.keySet()){System.out.println(clave+" "+MapCant.get(clave));}
    }
    public void cantLikesDeCadaTipo(SortedSet<Publicacion> listaPublicaciones)
    {
        System.out.println("Cant Likes por cada tipo: ");
        int vecCantL[] = {0,0,0,0};
        for(Publicacion publicacion : listaPublicaciones) {
            if("Imagen".equals(publicacion.getClass().getName()))
                vecCantL[0]+= publicacion.getCantidadMG();
            else
                if("Video".equals(publicacion.getClass().getName()))
                    vecCantL[1]+=publicacion.getCantidadMG();
                else
                    if("Audio".equals(publicacion.getClass().getName()))
                        vecCantL[2]+=publicacion.getCantidadMG();
                    else
                        vecCantL[3]+=publicacion.getCantidadMG();
        }
        System.out.println("Imagen: "+vecCantL[0]);
        System.out.println("Video: "+vecCantL[1]);
        System.out.println("Audio: "+vecCantL[2]);
        System.out.println("Texto: "+vecCantL[3]);
    }
    public void publicacionesConMasLikes(SortedSet<Publicacion> listaPublicaciones)
    {
        int likesMax = 0;
        Iterator<Publicacion> publicacionIterator = listaPublicaciones.iterator();
        int i = 0;
        int cantPublicacionesConMasLikes=5;
        while (publicacionIterator.hasNext() && i < cantPublicacionesConMasLikes) {
            lista5PublicacionesMasLikes.add(publicacionIterator.next());
            i++;
        }
        Iterator<Publicacion> publicacionLikesIterator = lista5PublicacionesMasLikes.iterator();
        Publicacion publicacion;
        while (publicacionIterator.hasNext()) {
            publicacion = publicacionIterator.next();
            if (publicacion.getCantidadMG() > lista5PublicacionesMasLikes.last().getCantidadMG()) {
                lista5PublicacionesMasLikes.add(publicacion);
                lista5PublicacionesMasLikes.remove(lista5PublicacionesMasLikes.last());
            }
        }
    }
}