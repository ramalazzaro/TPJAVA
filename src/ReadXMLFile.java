import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

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
                Node imagenNode = imagenList.item(i);
                if (imagenNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element imagenElement = (Element) imagenNode;
            
                    String nombre = getElementValue(imagenElement, "nombre");
                    String fechaSubida = getElementValue(imagenElement, "fechaSubida");
                    int cantMG = Integer.parseInt(getElementValue(imagenElement, "cantMG"));
                    int ancho = Integer.parseInt(getElementValue(imagenElement, "Ancho")); // Cambiado aquí
                    int alto = Integer.parseInt(getElementValue(imagenElement, "Alto")); // Cambiado aquí
                    String formato = getElementValue(imagenElement, "formato");
            
                    Imagen imagen = new Imagen(nombre, fechaSubida, cantMG, formato, ancho, alto);
            
                    perfilInstagram.addPublicacion(imagen);
                }
            }
            

            for (int i = 0; i < videoList.getLength(); i++) {
                Node videoNode = videoList.item(i);
                if (videoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element videoElement = (Element) videoNode;
                    String nombre = getElementValue(videoElement, "nombre");
                    String fechaSubida = getElementValue(videoElement, "fechaSubida");
                    int cantMG = Integer.parseInt(getElementValue(videoElement, "cantMG"));
                    int duracion = Integer.parseInt(getElementValue(videoElement, "duracion"));
                    String resolucion = getElementValue(videoElement, "resolucion");
                    int cantidadCuadros = Integer.parseInt(getElementValue(videoElement, "cantidadCuadros"));
                    Video video = new Video(nombre, fechaSubida, cantMG, duracion, resolucion, cantidadCuadros);
                    perfilInstagram.addPublicacion(video);
                }
            }

            for (int i = 0; i < audioList.getLength(); i++) {
                Node audioNode = audioList.item(i);
                if (audioNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element audioElement = (Element) audioNode;
                    String nombre = getElementValue(audioElement, "nombre");
                    String fechaSubida = getElementValue(audioElement, "fechaSubida");
                    int cantMG = Integer.parseInt(getElementValue(audioElement, "cantMG"));
                    int duracion = Integer.parseInt(getElementValue(audioElement, "duracion"));
                    int velocidad = Integer.parseInt(getElementValue(audioElement, "velocidad"));
                    Audio audio = new Audio(nombre, fechaSubida, cantMG, duracion, velocidad);
                    perfilInstagram.addPublicacion(audio);
                }
            }

            for (int i = 0; i < textoList.getLength(); i++) {
                Node textoNode = textoList.item(i);
                if (textoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element textoElement = (Element) textoNode;
                    String nombre = getElementValue(textoElement, "nombre");
                    String fechaSubida = getElementValue(textoElement, "fechaSubida");
                    int cantMG = Integer.parseInt(getElementValue(textoElement, "cantMG"));
                    String contenido = getElementValue(textoElement, "contenido");
                    int caracteres = Integer.parseInt(getElementValue(textoElement, "caracteres"));
                    String fuente = getElementValue(textoElement, "fuente");
                    String tamaño = getElementValue(textoElement, "tamaño");
                    Texto texto = new Texto(nombre, fechaSubida, cantMG, contenido, caracteres, fuente, tamaño);
                    perfilInstagram.addPublicacion(texto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getElementValue(Element element, String tagName) {
        Node firstChild = element.getElementsByTagName(tagName).item(0);
        if (firstChild != null) {
            NodeList nodeList = firstChild.getChildNodes();
            Node node = nodeList.item(0);
            return node.getNodeValue();
        } else {
            return "0";  // Retorna "0" como valor predeterminado si no se encuentra el elemento hijo
        }
    }
    
    
}
