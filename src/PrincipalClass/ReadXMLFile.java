package PrincipalClass;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import PrincipalClass.Audio;
import PrincipalClass.Comentario;
import PrincipalClass.PerfilInstagram;
import PrincipalClass.Texto;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase que lee y analiza un archivo XML y crea publicaciones en un perfil de Instagram.
 */
public class ReadXMLFile {
    private PerfilInstagram perfilInstagram;

    /**
     * Constructor de la clase ReadXMLFile.
     *
     * @param perfilInstagram el perfil de Instagram al que se agregarán las publicaciones.
     */
    public ReadXMLFile(PerfilInstagram perfilInstagram) {
        this.perfilInstagram = perfilInstagram;
    }
    /**
     * Analiza un archivo XML y crea las publicaciones correspondientes en el perfil de Instagram.
     *
     * @param xmlPath la ruta del archivo XML a analizar.
     */
    public void parseXML(String xmlPath) {
        try {
            File fXmlFile = new File(xmlPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            // Obtener nodos de imagen, video, audio y texto del archivo XML
            NodeList imagenList = doc.getElementsByTagName("Imagen");
            NodeList videoList = doc.getElementsByTagName("Video");
            NodeList audioList = doc.getElementsByTagName("Audio");
            NodeList textoList = doc.getElementsByTagName("Texto");
            // Procesar cada nodo de imagen
            for (int i = 0; i < imagenList.getLength(); i++) {
                parseImagenNode(imagenList.item(i));
            }
            // Procesar cada nodo de video
            for (int i = 0; i < videoList.getLength(); i++) {
                parseVideoNode(videoList.item(i));
            }
            // Procesar cada nodo de audio
            for (int i = 0; i < audioList.getLength(); i++) {
                parseAudioNode(audioList.item(i));
            }
            // Procesar cada nodo de texto
            for (int i = 0; i < textoList.getLength(); i++) {
                parseTextoNode(textoList.item(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Analiza un nodo de imagen y crea una publicación de imagen en el perfil de Instagram.
     *
     * @param imagenNode el nodo de imagen a analizar.
     */
    private void parseImagenNode(Node imagenNode) {
        if (imagenNode.getNodeType() == Node.ELEMENT_NODE) {
            Element imagenElement = (Element) imagenNode;

            String nombre = getElementValue(imagenElement, "nombre");
            String fechaSubida = getElementValue(imagenElement, "fechaSubida");
            int cantMG = parseInt(getElementValue(imagenElement, "cantMG"), 0);
            String resolucion = getElementValue(imagenElement, "resolucion");
            int ancho = parseInt(getElementValue(imagenElement, "Ancho"), 0);
            int alto = parseInt(getElementValue(imagenElement, "Alto"), 0);

            NodeList commentNodes = imagenElement.getElementsByTagName("comentario");
            ArrayList<Comentario> comentarios = new ArrayList<>();

            NodeList etiquetaNodes = imagenElement.getElementsByTagName("etiqueta");
            ArrayList<Etiqueta> etiquetas = new ArrayList<>();

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

            for (int j = 0; j < etiquetaNodes.getLength(); j++) {
                Node etiquetaNode = etiquetaNodes.item(j);
                if (etiquetaNode.getNodeType() == Node.ELEMENT_NODE) {
                    String contenido = etiquetaNode.getTextContent();
                    Etiqueta etiqueta = new Etiqueta(contenido);
                    etiquetas.add(etiqueta);
                }
            }

            Imagen imagen = new Imagen(nombre, fechaSubida, cantMG, comentarios, etiquetas, resolucion, ancho, alto);
            perfilInstagram.addPublicacion(imagen);
        }
    }
    /**
     * Analiza un nodo de video y crea una publicación de video en el perfil de Instagram.
     *
     * @param videoNode el nodo de video a analizar.
     */
    private void parseVideoNode(Node videoNode) {
        if (videoNode.getNodeType() == Node.ELEMENT_NODE) {
            Element videoElement = (Element) videoNode;

            String nombre = getElementValue(videoElement, "nombre");
            String fechaSubida = getElementValue(videoElement, "fechaSubida");
            int cantMG = parseInt(getElementValue(videoElement, "cantMG"), 0);
            int duracion = parseInt(getElementValue(videoElement, "duracion"), 0);
            String resolucion = getElementValue(videoElement, "resolucion");
            int cantidadCuadros = parseInt(getElementValue(videoElement, "cantidadCuadros"), 0);

            NodeList commentNodes = videoElement.getElementsByTagName("comentario");
            ArrayList<Comentario> comentarios = new ArrayList<>();

            NodeList etiquetaNodes = videoElement.getElementsByTagName("etiqueta");
            ArrayList<Etiqueta> etiquetas = new ArrayList<>();

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

            for (int j = 0; j < etiquetaNodes.getLength(); j++) {
                Node etiquetaNode = etiquetaNodes.item(j);
                if (etiquetaNode.getNodeType() == Node.ELEMENT_NODE) {
                    String contenido = etiquetaNode.getTextContent();
                    Etiqueta etiqueta = new Etiqueta(contenido);
                    etiquetas.add(etiqueta);
                }
            }

            Video video = new Video(nombre, fechaSubida, cantMG, comentarios, etiquetas, duracion, resolucion,
                    cantidadCuadros);

            perfilInstagram.addPublicacion(video);
        }
    }
    /**
     * Analiza un nodo de audio y crea una publicación de audio en el perfil de Instagram.
     *
     * @param audioNode el nodo de audio a analizar.
     */
    private void parseAudioNode(Node audioNode) {
        if (audioNode.getNodeType() == Node.ELEMENT_NODE) {
            Element audioElement = (Element) audioNode;

            String nombre = getElementValue(audioElement, "nombre");
            String fechaSubida = getElementValue(audioElement, "fechaSubida");
            int cantMG = parseInt(getElementValue(audioElement, "cantMG"), 0);
            int duracion = parseInt(getElementValue(audioElement, "duracion"), 0);
            int velocidad = parseInt(getElementValue(audioElement, "velocidad"), 0);

            NodeList commentNodes = audioElement.getElementsByTagName("comentario");
            ArrayList<Comentario> comentarios = new ArrayList<>();

            NodeList etiquetaNodes = audioElement.getElementsByTagName("etiqueta");
            ArrayList<Etiqueta> etiquetas = new ArrayList<>();

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

            for (int j = 0; j < etiquetaNodes.getLength(); j++) {
                Node etiquetaNode = etiquetaNodes.item(j);
                if (etiquetaNode.getNodeType() == Node.ELEMENT_NODE) {
                    String contenido = etiquetaNode.getTextContent();
                    Etiqueta etiqueta = new Etiqueta(contenido);
                    etiquetas.add(etiqueta);
                }
            }

            Audio audio = new Audio(nombre, fechaSubida, cantMG, comentarios, etiquetas, duracion, velocidad);

            perfilInstagram.addPublicacion(audio);
        }
    }
    /**
     * Analiza un nodo de texto y crea una publicación de texto en el perfil de Instagram.
     *
     * @param textoNode el nodo de texto a analizar.
     */
    private void parseTextoNode(Node textoNode) {
        if (textoNode.getNodeType() == Node.ELEMENT_NODE) {
            Element textoElement = (Element) textoNode;

            String nombre = getElementValue(textoElement, "nombre");
            String fechaSubida = getElementValue(textoElement, "fechaSubida");
            int cantMG = parseInt(getElementValue(textoElement, "cantMG"), 0);
            String contenido = getElementValue(textoElement, "contenido");
            int caracteres = parseInt(getElementValue(textoElement, "caracteres"), 0);
            String fuente = getElementValue(textoElement, "fuente");
            String tamaño = getElementValue(textoElement, "tamaño");

            NodeList commentNodes = textoElement.getElementsByTagName("comentario");
            ArrayList<Comentario> comentarios = new ArrayList<>();

            NodeList etiquetaNodes = textoElement.getElementsByTagName("etiqueta");
            ArrayList<Etiqueta> etiquetas = new ArrayList<>();

            for (int j = 0; j < commentNodes.getLength(); j++) {
                Node commentNode = commentNodes.item(j);
                if (commentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element commentElement = (Element) commentNode;
                    String usuario = getElementValue(commentElement, "usuario");
                    String contenidoComentario = getElementValue(commentElement, "contenido");
                    String fecha = getElementValue(commentElement, "fecha");
                    Comentario comentario = new Comentario(usuario, contenidoComentario, fecha);
                    comentarios.add(comentario);
                }
            }

            for (int j = 0; j < etiquetaNodes.getLength(); j++) {
                Node etiquetaNode = etiquetaNodes.item(j);
                if (etiquetaNode.getNodeType() == Node.ELEMENT_NODE) {
                    String contenidoEtiqueta = etiquetaNode.getTextContent();
                    Etiqueta etiqueta = new Etiqueta(contenidoEtiqueta);
                    etiquetas.add(etiqueta);
                }
            }

            Texto texto = new Texto(nombre, fechaSubida, cantMG, comentarios, etiquetas, contenido, caracteres, fuente,
                    tamaño);
            texto.setNombre(nombre);
            texto.setFechaSubida(fechaSubida);
            texto.setCantidadMG(cantMG);

            perfilInstagram.addPublicacion(texto);
        }
    }
    /**
     * Obtiene el valor de un elemento específico dentro de un elemento dado.
     *
     * @param element el elemento padre.
     * @param tagName el nombre del elemento hijo.
     * @return el valor del elemento.
     */
    private String getElementValue(Element element, String tagName) {
        try {
            NodeList nodes = element.getElementsByTagName(tagName).item(0).getChildNodes();
            Node node = nodes.item(0);
            return node.getNodeValue();
        }catch (Exception e){
            return "null";
        }
    }
    /**
     * Convierte una cadena en un entero y maneja una posible excepción.
     *
     * @param str          la cadena a convertir en entero.
     * @param defaultValue el valor predeterminado a devolver si ocurre una excepción.
     * @return el entero convertido o el valor predeterminado si ocurre una excepción.
     */
    private int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}