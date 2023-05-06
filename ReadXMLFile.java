import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {

    public static void main(String argv[]) {
        try {
            File fXmlFile = new File("datos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList imagenList = doc.getElementsByTagName("Imagen");
            NodeList videoList = doc.getElementsByTagName("Video");
            NodeList audioList = doc.getElementsByTagName("Audio");

            System.out.println("RESUMEN DE IMÁGENES");
            System.out.println("----------------------------");
            for (int i = 0; i < imagenList.getLength(); i++) {
                Node imagenNode = imagenList.item(i);
                if (imagenNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element imagenElement = (Element) imagenNode;
                    System.out.println("Nombre: " + getElementValue(imagenElement, "nombre"));
                    System.out.println("Fecha de Subida: " + getElementValue(imagenElement, "fechaSubida"));
                    System.out.println("Cantidad de Me Gusta: " + getElementValue(imagenElement, "cantMG"));

                    NodeList etiquetasList = imagenElement.getElementsByTagName("etiqueta");
                    System.out.println("Etiquetas:");
                    for (int j = 0; j < etiquetasList.getLength(); j++) {
                        Node etiquetaNode = etiquetasList.item(j);
                        if (etiquetaNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element etiquetaElement = (Element) etiquetaNode;
                            System.out.println("- " + etiquetaElement.getTextContent());
                        }
                    }

                    NodeList comentarioList = imagenElement.getElementsByTagName("cometario");
                    System.out.println("Comentarios:");
                    for (int j = 0; j < comentarioList.getLength(); j++) {
                        Node comentarioNode = comentarioList.item(j);
                        if (comentarioNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element comentarioElement = (Element) comentarioNode;
                            System.out.println("- Usuario: " + getElementValue(comentarioElement, "usuario"));
                            System.out.println("  Contenido: " + getElementValue(comentarioElement, "contenido"));
                            System.out.println("  Fecha: " + getElementValue(comentarioElement, "fecha"));
                        }
                    }

                    System.out.println("Resolución: " + getElementValue(imagenElement, "resolucion"));
                    System.out.println("Ancho: " + getElementValue(imagenElement, "Ancho"));
                    System.out.println("Alto: " + getElementValue(imagenElement, "Alto"));

                    System.out.println("----------------------------");
                }
            }

            System.out.println("RESUMEN DE VIDEOS");
            System.out.println("----------------------------");
            for (int i = 0; i < videoList.getLength(); i++) {
                Node videoNode = videoList.item(i);
                if (videoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element videoElement = (Element) videoNode;
                    System.out.println("Nombre: " + getElementValue(videoElement, "nombre"));
                    System.out.println("Fecha de Subida: " + getElementValue(videoElement, "fechaSubida"));
                    System.out.println("Cantidad de Me Gusta: " + getElementValue(videoElement, "cantMG"));
                    System.out.println("Duración: " + getElementValue(videoElement, "duracion"));
                    System.out.println("Resolución: " + getElementValue(videoElement, "resolucion"));
                    System.out.println("Formato: " + getElementValue(videoElement, "formato"));
                    System.out.println("Tamaño: " + getElementValue(videoElement, "tamanio"));

                    System.out.println("----------------------------");
                }
            }

            System.out.println("RESUMEN DE AUDIOS");
            System.out.println("----------------------------");
            for (int i = 0; i < audioList.getLength(); i++) {
                Node audioNode = audioList.item(i);
                if (audioNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element audioElement = (Element) audioNode;
                    System.out.println("Nombre: " + getElementValue(audioElement, "nombre"));
                    System.out.println("Fecha de Subida: " + getElementValue(audioElement, "fechaSubida"));
                    System.out.println("Cantidad de Me Gusta: " + getElementValue(audioElement, "cantMG"));

                    NodeList etiquetasList = audioElement.getElementsByTagName("etiqueta");
                    System.out.println("Etiquetas:");
                    for (int j = 0; j < etiquetasList.getLength(); j++) {
                        Node etiquetaNode = etiquetasList.item(j);
                        if (etiquetaNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element etiquetaElement = (Element) etiquetaNode;
                            System.out.println("- " + etiquetaElement.getTextContent());
                        }
                    }

                    NodeList comentarioList = audioElement.getElementsByTagName("cometario");
                    System.out.println("Comentarios:");
                    for (int j = 0; j < comentarioList.getLength(); j++) {
                        Node comentarioNode = comentarioList.item(j);
                        if (comentarioNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element comentarioElement = (Element) comentarioNode;
                            System.out.println("- Usuario: " + getElementValue(comentarioElement, "usuario"));
                            System.out.println("  Contenido: " + getElementValue(comentarioElement, "contenido"));
                            System.out.println("  Fecha: " + getElementValue(comentarioElement, "fecha"));
                        }
                    }

                    System.out.println("Duración: " + getElementValue(audioElement, "duracion"));
                    System.out.println("Formato: " + getElementValue(audioElement, "formato"));
                    System.out.println("Tamaño: " + getElementValue(audioElement, "tamanio"));

                    System.out.println("----------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return "";
    }
}
