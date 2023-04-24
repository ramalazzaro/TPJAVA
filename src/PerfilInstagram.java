// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class PerfilInstagram {

    public static void main(String[] args) {
        Comentario coment1 = new Comentario("agustin","Primer comentario","2023-04-22");
        Comentario coment2 = new Comentario("fausto","segundo comentario","2023-04-22");
        Comentario coment3 = new Comentario("lucas","tercer comentario","2023-04-22");
        Publicacion publicacion1 = new Publicacion("nueva publicacion 2","hoy",5);

        System.out.println(publicacion1.getNombre());
        publicacion1.setNombre("nombre editado");
        System.out.println(publicacion1.getNombre());
        publicacion1.addComentario(coment1);
        publicacion1.addComentario(coment2);
        publicacion1.addComentario(coment3);

        publicacion1.mostrarComentarios();
    }

}