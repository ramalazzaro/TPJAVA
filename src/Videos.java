import javax.print.DocFlavor.STRING;

public class Video extends Publicacion implements Filtrable,Durable {
    private int duracion; //en segundos
    private String resolucion; 
    private int cantidadCuadros;
    public Videos(String nombre, String fechaSubida, int cantidadMG,int duracion,String resolucion,int cantidadCuadros) {
        super(nombre,fechaSubida,cantidadMG);
        this.duracion = duracion;
        this.resolucion = resolucion;
        this.cantidadCuadros = cantidadCuadros;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public int getCantidadCuadros() {
        return cantidadCuadros;
    }

    public void setCantidadCuadros(int cantidadCuadros) {
        this.cantidadCuadros = cantidadCuadros;
    }
}
