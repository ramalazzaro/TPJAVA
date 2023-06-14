package PrincipalClass;

import PrincipalClass.Comentario;
import Interfaces.Filtrable;
import java.util.ArrayList;

public class Imagen extends Publicacion implements Filtrable {
    private String resolucion;
    private int ancho;
    private int alto;

    public Imagen(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
                  ArrayList<Etiqueta> etiquetas, String resolucion, int ancho, int alto) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
        this.resolucion = resolucion;
        this.ancho = ancho;
        this.alto = alto;
    }

    //De la interfaz Interfaces.Filtrable
    private static boolean filtro=false;
    @Override
    public void poneFiltro() {
        filtro = true;
    }
    @Override
    public void sacaFiltro() {
        filtro = false;
    }
    @Override
    public String cansultaFiltro(){
        if(filtro)
            return "Filtro aplicado \n";
        else
            return "Filtro no aplicado \n";
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    @Override
    public String toString() {
        return "IMAGEN\n" + super.toString() + "\tResolucion: " + resolucion + ", Ancho: " + ancho + ", Alto: " + alto + "\n";
    }

}