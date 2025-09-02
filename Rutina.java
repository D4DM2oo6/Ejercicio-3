import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private String nombre;
    private List<String> ejercicios;   // sencillo: solo nombres de ejercicios
    private String objetivo;           // orientación: pérdida de peso, músculo, condición
    private boolean activa;            // para el reporte de "rutinas activas"

    public Rutina(String nombre, String objetivo, boolean activa) {
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.activa = activa;
        this.ejercicios = new ArrayList<>();
    }

    // Gestión de ejercicios
    public boolean agregarEjercicio(String ejercicio) {
        if (ejercicio == null || ejercicio.isEmpty()) return false;
        return ejercicios.add(ejercicio);
    }

    public boolean eliminarEjercicio(String ejercicio) {
        return ejercicios.remove(ejercicio);
    }

    // Getters/Setters
    public String getNombre() { return nombre; }
    public String getObjetivo() { return objetivo; }
    public List<String> getEjercicios() { return new ArrayList<>(ejercicios); }
    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
