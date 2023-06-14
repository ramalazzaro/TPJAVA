package PrincipalClass;

import Interfaces.Durable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Audio extends Publicacion implements Durable {
    private int duracion;
    private int velocidad;

    public Audio(String nombre, String fechaSubida, int cantidadMG, ArrayList<Comentario> comentarios, ArrayList<Etiqueta> etiquetas, int duracion, int velocidad) {
        super(nombre, fechaSubida, cantidadMG, comentarios, etiquetas);
        this.duracion = duracion;
        this.velocidad = velocidad;
    }

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
        return "AUDIO\n" + super.toString() + "\tDuracion: " + duracion + ", Velocidad: " + velocidad+ "\n";
    }

}
