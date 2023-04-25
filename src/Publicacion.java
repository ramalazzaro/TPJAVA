import java.util.ArrayList;

public class Publicacion implements Comparable<Publicacion> {

    private String nombre;
    private String fechaSubida;
    private int cantidadMG;
    private ArrayList<Etiqueta> listaEtiquetas = new ArrayList<>();
    private ArrayList<Comentario> listaComentarios = new ArrayList<>();
    public Publicacion(String nombre,String fechaSubida,int cantidadMG){
        this.nombre = nombre;
        this.fechaSubida = fechaSubida;
        this.cantidadMG = cantidadMG;
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
    public void addComentario(Comentario nuevoComentario){
        listaComentarios.add(nuevoComentario);
    }

    public void mostrarEtiquetas(){
        for(Etiqueta etiqueta: listaEtiquetas) {
            System.out.println(etiqueta.toString());
        }
    }
    public void mostrarComentarios(){
        for (Comentario comentario: listaComentarios) {
            System.out.println(comentario.toString());
        }
    }

    @Override
    public int compareTo(Publicacion o) {
        return this.nombre.compareTo(o.nombre);
    }
}
