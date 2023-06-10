import java.util.ArrayList;

public class Texto extends Publicacion {
    private String contenido;
    private int caracteres;
    private String fuente;
    private String tamaño;

    public Texto(String nombre, String fechaSubida, int cantMG, ArrayList<Comentario> comentarios,
            ArrayList<Etiqueta> etiquetas, String contenido, int caracteres, String fuente,
            String tamaño) {
        super(nombre, fechaSubida, cantMG, comentarios, etiquetas);
        this.contenido = contenido;
        this.caracteres = caracteres;
        this.fuente = fuente;
        this.tamaño = tamaño;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(int caracteres) {
        this.caracteres = caracteres;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nTexto\n")
                .append(super.toString())
                .append("\tContenido: ").append(contenido).append('\'')
                .append(", Caracteres: ").append(caracteres)
                .append(", Fuente: ").append(fuente).append('\'')
                .append(", Tamaño:").append(tamaño).append('\'');

        builder.append('\n');
        return builder.toString();
    }

}
