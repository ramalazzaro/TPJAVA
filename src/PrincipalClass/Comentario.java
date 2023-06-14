package PrincipalClass;

public class Comentario {
    private String usuario;
    private String comentario;
    private String fecha;

    public Comentario(String usuario, String comentario, String fecha) {
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return fecha + " " + usuario + " comento: " + comentario;
    }
}
