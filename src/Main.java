
public class Main {
    public static void main(String[] args) {
        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./datos.xml");
        Estadisticas estadisticas = new Estadisticas();

        ReportesAlbumes reportesAlbumes = new ReportesAlbumes();
        reportesAlbumes.creaReporteAlbumes(perfilInstagram);
    }
}

