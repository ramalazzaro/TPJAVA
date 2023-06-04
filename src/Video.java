public class Video extends Publicacion{
    private int duracion;
    private String resolucion;
    private int cantidadCuadros;
    

    public Video(String nombre, String fechaSubida, int cantidadMG, int duracion,String resolucion,int cantidadCuadros) {
        super(nombre, fechaSubida, cantidadMG);
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

    @Override
    public String toString() {
        return "Video{" +
                "nombre='" + getNombre() + '\'' +
                ", fechaSubida='" + getFechaSubida() + '\'' +
                ", cantidadMG=" + getCantidadMG() +
                ", duracion=" + duracion +
                ", resolucion='" + resolucion + '\'' +
                ", cantidadCuadros=" + cantidadCuadros +
                '}';
    }
}
