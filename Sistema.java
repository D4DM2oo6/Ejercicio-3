public class Sistema {
    private final GestorMiembros gestorMiembros;
    private final GestorEntrenadores gestorEntrenadores;
    private final GestorRutinas gestorRutinas;
    private final Reportes reportes;

    public Sistema() {
        this.gestorMiembros = new GestorMiembros();
        this.gestorEntrenadores = new GestorEntrenadores();
        this.gestorRutinas = new GestorRutinas();
        this.reportes = new Reportes();
    }

    // Getters para usar desde Main
    public GestorMiembros getGestorMiembros() { return gestorMiembros; }
    public GestorEntrenadores getGestorEntrenadores() { return gestorEntrenadores; }
    public GestorRutinas getGestorRutinas() { return gestorRutinas; }
    public Reportes getReportes() { return reportes; }
}
