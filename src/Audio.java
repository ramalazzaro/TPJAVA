public class Audio extends Publicacion{
    private int duracion; 
    private int velocidad;
    public Audio(String nombre, String fechaSubida, int cantidadMG, int duracion, int velocidad) {
        super(nombre, fechaSubida, cantidadMG);
        this.duracion = duracion;
        this.velocidad = velocidad;
    }
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}

