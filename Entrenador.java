import java.util.ArrayList;
import java.util.List;

public class Entrenador {
    private String nombre;
    private String tipoRutina;                // p.ej. pérdida de peso, fuerza, etc.
    private List<Miembro> alumnos;

    public Entrenador(String nombre, String tipoRutina) {
        this.nombre = nombre;
        this.tipoRutina = tipoRutina;
        this.alumnos = new ArrayList<>();
    }

    // Gestión de alumnos
    public boolean agregarAlumno(Miembro miembro) {
        if (miembro == null || alumnos.contains(miembro)) return false;
        alumnos.add(miembro);
        return true;
    }

    public boolean eliminarAlumno(Miembro miembro) {
        return alumnos.remove(miembro);
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getTipoRutina() { return tipoRutina; }
    public List<Miembro> getAlumnos() { return new ArrayList<>(alumnos); }
    public int getCantidadAlumnos() { return alumnos.size(); }
}
