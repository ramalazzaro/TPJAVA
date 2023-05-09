public class Main {
    public static void main(String[] args) {
        PerfilInstagram perfilInstagram = new PerfilInstagram();
        ReadXMLFile readXMLFile = new ReadXMLFile(perfilInstagram);
        readXMLFile.parseXML("./datos.xml");

        // Luego de agregar las publicaciones, puedes llamar a los métodos en la instancia de PerfilInstagram, por ejemplo, para mostrar las publicaciones:
        perfilInstagram.mostrarPublicaciones();
    }
}

