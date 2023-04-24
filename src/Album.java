public class Album {
    private int cantidadAcumuladaMG;
    private int cantPublicacionesTotales;
    private int[] cantPublicacionesTipo = new int[3];

    public Album(int cantidadAcumuladaMG, int cantPublicacionesTotales) {
        this.cantidadAcumuladaMG = cantidadAcumuladaMG;
        this.cantPublicacionesTotales = cantPublicacionesTotales;
    }
    public int getCantidadAcumuladaMG() {
        return cantidadAcumuladaMG;
    }
    public void setCantidadAcumuladaMG(int cantidadAcumuladaMG) {
        this.cantidadAcumuladaMG = cantidadAcumuladaMG;
    }
    public int getCantPublicacionesTotales() {
        return cantPublicacionesTotales;
    }
    public void setCantPublicacionesTotales(int cantPublicacionesTotales) {
        this.cantPublicacionesTotales = cantPublicacionesTotales;
    }
    public int[] getCantPublicacionesTipo() {
        return cantPublicacionesTipo;
    }
    public void setCantPublicacionesTipo(int[] cantPublicacionesTipo) {
        this.cantPublicacionesTipo = cantPublicacionesTipo;
    }
}
