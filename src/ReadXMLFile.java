import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class ReadXMLFile {
    private PerfilInstagram perfilInstagram;

    public ReadXMLFile(PerfilInstagram perfilInstagram) {
        this.perfilInstagram = perfilInstagram;
    }

    public void parseXML(String xmlPath) {
        try {
            File fXmlFile = new File(xmlPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList imagenList = doc.getElementsByTagName("Imagen");
            NodeList videoList = doc.getElementsByTagName("Video");
            NodeList audioList = doc.getElementsByTagName("Audio");
            NodeList textoList = doc.getElementsByTagName("Texto");

            for (int i = 0; i < imagenList.getLength(); i++) {
                parseImagenNode(imagenList.item(i));
            }

            for (int i = 0; i < videoList.getLength(); i++) {
                parseVideoNode(videoList.item(i));
            }

            for (int i = 0; i < audioList.getLength(); i++) {
                parseAudioNode(audioList.item(i));
            }

            for (int i = 0; i < textoList.getLength(); i++) {
                parseTextoNode(textoList.item(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseImagenNode(Node imagenNode) {
        if (imagenNode.getNodeType() == Node.ELEMENT_NODE) {
            Element imagenElement = (Element) imagenNode;

            String nombre = getElementValue(imagenElement, "nombre");
            String fechaSubida = getElementValue(imagenElement, "fechaSubida");
            int cantMG = parseInt(getElementValue(imagenElement, "cantMG"), 0);
            int ancho = parseInt(getElementValue(imagenElement, "Ancho"), 0);
            int alto = parseInt(getElementValue(imagenElement, "Alto"), 0);
            String formato = getElementValue(imagenElement, "formato");

            Imagen imagen = new Imagen(nombre, fechaSubida, cantMG, formato, ancho, alto);

            NodeList commentNodes = imagenElement.getElementsByTagName("comentario");
            List<Comentario> comentarios = new ArrayList<>();
            for (int j = 0; j < commentNodes.getLength(); j++) {
                Node commentNode = commentNodes.item(j);
                if (commentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element commentElement = (Element) commentNode;
                    String usuario = getElementValue(commentElement, "usuario");
                    String contenido = getElementValue(commentElement, "contenido");
                    String fecha = getElementValue(commentElement, "fecha");
                    Comentario comentario = new Comentario(usuario, contenido, fecha);
                    comentarios.add(comentario);
                }
            }
            imagen.setComentarios(comentarios);

            perfilInstagram.addPublicacion(imagen);
        }
    }

    private void parseVideoNode(Node videoNode) {
        if (videoNode.getNodeType() == Node.ELEMENT_NODE) {
            Element videoElement = (Element) videoNode;

            String nombre = getElementValue(videoElement, "nombre");
            String fechaSubida = getElementValue(videoElement, "fechaSubida");
            int cantMG = parseInt(getElementValue(videoElement, "cantMG"), 0);
            int duracion = parseInt(getElementValue(videoElement, "duracion"), 0);
            String resolucion = getElementValue(videoElement, "resolucion");
            int cantidadCuadros = parseInt(getElementValue(videoElement, "cantidadCuadros"), 0);

            Video video = new Video(nombre, fechaSubida, cantMG, duracion, resolucion, cantidadCuadros);

            perfilInstagram.addPublicacion(video);
        }
    }

    private void parseAudioNode(Node audioNode) {
        if (audioNode.getNodeType() == Node.ELEMENT_NODE) {
            Element audioElement = (Element) audioNode;

            String nombre = getElementValue(audioElement, "nombre");
            String fechaSubida = getElementValue(audioElement, "fechaSubida");
            int cantMG = parseInt(getElementValue(audioElement, "cantMG"), 0);
            int duracion = parseInt(getElementValue(audioElement, "duracion"), 0);
            int velocidad = parseInt(getElementValue(audioElement, "velocidad"), 0);

            Audio audio = new Audio(nombre, fechaSubida, cantMG, duracion, velocidad);

            perfilInstagram.addPublicacion(audio);
        }
    }

    private void parseTextoNode(Node textoNode) {
        if (textoNode.getNodeType() == Node.ELEMENT_NODE) {
            Element textoElement = (Element) textoNode;

            String contenido = getElementValue(textoElement, "contenido");
            int caracteres = parseInt(getElementValue(textoElement, "caracteres"), 0);
            String fuente = getElementValue(textoElement, "fuente");
            String tamaño = getElementValue(textoElement, "tamaño");

            Texto texto = new Texto(contenido, caracteres, fuente, tamaño);

            perfilInstagram.addPublicacion(texto);
        }
    }

    private String getElementValue(Element element, String tagName) {
        return getElementValue(element, tagName, null);
    }

    private String getElementValue(Element element, String tagName, String defaultValue) {
        Node firstChild = element.getElementsByTagName(tagName).item(0);
        if (firstChild != null) {
            NodeList nodeList = firstChild.getChildNodes();
            Node node = nodeList.item(0);
            if (node != null) {
                return node.getNodeValue();
            }
        }
        return defaultValue;
    }

    private int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
