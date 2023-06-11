import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;
public abstract class Publicacion implements Comparable<Publicacion>, Serializable {

    private String nombre;
    private String fechaSubida;
    private int cantidadMG;
    private ArrayList<Etiqueta> listaEtiquetas = new ArrayList<>();
    private ArrayList<Comentario> listaComentarios = new ArrayList<>();

    public Publicacion(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
            ArrayList<Etiqueta> etiquetas) {
        this.nombre = nombre;
        this.fechaSubida = fechaSubida;
        this.cantidadMG = cantidadMG;
        this.listaEtiquetas = etiquetas;
        this.listaComentarios = comentarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(String fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public int getCantidadMG() {
        return cantidadMG;
    }

    public void setCantidadMG(int cantidadMG) {
        this.cantidadMG = cantidadMG;
    }

    public void addEtiqueta(Etiqueta nuevaEtiqueta) {
        listaEtiquetas.add(nuevaEtiqueta);
    }

    public void addComentario(Comentario nuevoComentario) {
        listaComentarios.add(nuevoComentario);
    }

    public void mostrarEtiquetas() {
        for (Etiqueta etiqueta : listaEtiquetas) {
            System.out.println(etiqueta.toString());
        }
    }

    public void mostrarComentarios() {
        for (Comentario comentario : listaComentarios) {
            System.out.println(comentario.toString());
        }
    }

    public ArrayList<Comentario> getComentarios() {
        return this.listaComentarios;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return this.listaEtiquetas;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\tNombre: " + getNombre() + ", Fecha de Subida: " + getFechaSubida() + ", Cantidad de me gusta: " + getCantidadMG());

        if (getComentarios() != null && !getComentarios().isEmpty()) {
            builder.append("\n\tComentarios:\n");
            for (Comentario comentario : getComentarios()) {
                builder.append("\t\t" + comentario.toString() + '\n');
            }
        }

        if (getEtiquetas() != null && !getEtiquetas().isEmpty()) {
            builder.append("\tEtiquetas:\n");
            for (Etiqueta etiqueta : getEtiquetas()) {
                builder.append("\t\t" + etiqueta + '\n');
            }
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Publicacion o) {
        return this.nombre.compareTo(o.nombre);
    }
}