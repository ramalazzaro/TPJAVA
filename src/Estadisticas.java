import java.sql.SQLOutput;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Estadisticas{
    private SortedSet<Publicacion> listaPublicacionesPorFecha = new TreeSet<>(new FechaComparator());
    public void creaListaPorFecha(SortedSet<Publicacion> listaPub){listaPublicacionesPorFecha.addAll(listaPub);}
    public void mostrarPublicacionesPorFecha() {
        for (Publicacion publicacion : listaPublicacionesPorFecha) {
            System.out.println(publicacion.toString());
        }
    }
    public SortedSet<Publicacion> getListaPublicacionesPorFecha(){return listaPublicacionesPorFecha;}
    public void LikesPorAño(SortedSet<Publicacion> listaPublicaciones)
    {
        HashMap<String,Integer> MapCantLikesAño = new HashMap<String, Integer>();
        int CantLikesAño;
        int año;
        Iterator<Publicacion> publicacionIterator = listaPublicaciones.iterator();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate Date;
        Publicacion publicacion = publicacionIterator.next();
        while(publicacionIterator.hasNext()) {
            CantLikesAño=0;
            CantLikesAño += publicacion.getCantidadMG();
            Date = LocalDate.parse(publicacion.getFechaSubida(),formatter);
            año = Date.getYear();
            publicacion = publicacionIterator.next();
            while(publicacionIterator.hasNext() && año == Date.getYear()){
                publicacion = publicacionIterator.next();
                CantLikesAño += publicacion.getCantidadMG();
                Date = LocalDate.parse(publicacion.getFechaSubida(),formatter);
            }
            System.out.println("Cantidad de MeGustas en el año "+ año +": "+ CantLikesAño)
        }
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
}