import java.time.LocalDate;

public class Miembro {
    private String nombre;
    private int tiempo;                       // meses inscrito
    private String calidadMembresia;          // básico | intermedio | avanzado (o similar)
    private Entrenador entrenadorAsignado;    // puede ser null
    private LocalDate fechaFinMembresia;

    public Miembro(String nombre, int tiempo, String calidadMembresia, LocalDate fechaFinMembresia) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.calidadMembresia = calidadMembresia;
        this.fechaFinMembresia = fechaFinMembresia;
    }

    // Acciones básicas
    public void actualizarFechaFinMembresia(LocalDate nuevaFecha) { this.fechaFinMembresia = nuevaFecha; }
    public void asignarEntrenador(Entrenador entrenador) { this.entrenadorAsignado = entrenador; }
    public void cambiarCalidadMembresia(String nuevaCalidad) { this.calidadMembresia = nuevaCalidad; }

    // Getters
    public String getNombre() { return nombre; }
    public int getTiempo() { return tiempo; }
    public String getCalidadMembresia() { return calidadMembresia; }
    public Entrenador getEntrenadorAsignado() { return entrenadorAsignado; }
    public LocalDate getFechaFinMembresia() { return fechaFinMembresia; }

    // Setters opcionales
    public void setTiempo(int tiempo) { this.tiempo = tiempo; }
}
