import java.util.ArrayList;

public class Audio extends Publicacion {
    private int duracion;
    private int velocidad;

    public Audio(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
            ArrayList<Etiqueta> etiquetas, int duracion, int velocidad) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\nAudio\n")
                .append(super.toString())
                .append(", duracion=").append(duracion)
                .append(", velocidad=").append(velocidad);

        builder.append('\n');
        return builder.toString();
    }

}
