package Interfaces;

public interface Durable {
    public void pausar();
    public void reanudar();
    public void finalizar();
    public boolean getPausa();
    public boolean getFinaliza();
    public int getDuracion();
}
