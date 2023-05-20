
public class Main {
    public static void main(String[] args) {
        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./datos.xml");

        //Luego de agregar las publicaciones, puedes llamar a los métodos en la instancia de PerfilInstagram, por ejemplo, para mostrar las publicaciones:
        //perfilInstagram.mostrarPublicaciones();

        Estadisticas Estadisticas = new Estadisticas();
        // Se crea una lista ordenada por fechas solo para calcular estadísticas que lo requeriran (Ej: LikesPorMes)
        Estadisticas.creaListaPorFecha(perfilInstagram.getListaPublicaciones());

        Estadisticas.mostrarPublicacionesPorFecha();
        Estadisticas.LikesPorAño(Estadisticas.getListaPublicacionesPorFecha());
        //Estadisticas.cantPublicacionesDeCadaTipo(perfilInstagram.getListaPublicaciones());
        //Estadisticas.cantLikesDeCadaTipo(perfilInstagram.getListaPublicaciones());
    }
}

