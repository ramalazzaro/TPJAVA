import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Video extends Publicacion implements Durable,Filtrable{
    private int duracion;
    private String resolucion;
    private int cantidadCuadros;

    public Video(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios,
            ArrayList<Etiqueta> etiquetas, int duracion, String resolucion,
            int cantidadCuadros) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
        this.duracion = duracion;
        this.resolucion = resolucion;

    }

    //De la interfaz Durable;
    private static boolean pausaActivada=false;
    private static boolean finaliza=false;
    private static boolean pausa=false;
    @Override
    public void reproduce(){
        int segundos = 0;
        try {
            while(segundos<=duracion&&!finaliza){
                if(!pausaActivada) {
                    System.out.println("Segundos transcurridos: " + segundos);
                    TimeUnit.SECONDS.sleep(1);
                    segundos++;
                }else
                    TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nFin de la reproduccion de l video \n");
    }
    @Override
    public void pausar(){
        pausa=true;
    }
    @Override
    public void reanudar(){
        pausa = false;
    }
    @Override
    public void finalizar(){
        finaliza = true;
    }

    //De la interfaz Filtrable
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
        return "VIDEO\n" + super.toString() + "\tDuracion: " + duracion + ", Resolucion: " + resolucion + ", Cantidad de Cuadros: " + cantidadCuadros + "\n";
    }
}
