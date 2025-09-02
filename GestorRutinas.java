import java.util.*;

public class GestorRutinas {
    private final List<Rutina> rutinas = new ArrayList<>();
    // Asignación simple: cada miembro tiene a lo sumo una rutina asignada
    private final Map<Miembro, Rutina> asignaciones = new HashMap<>();

    public boolean agregarRutina(Rutina rutina) {
        if (rutina == null) return false;
        return rutinas.add(rutina);
    }

    public boolean eliminarRutina(Rutina rutina) {
        // Al eliminar, también desasignamos a los miembros que la tenían
        boolean removed = rutinas.remove(rutina);
        if (removed) {
            asignaciones.entrySet().removeIf(e -> e.getValue() == rutina);
        }
        return removed;
    }

    public List<Rutina> listarRutinas() { return new ArrayList<>(rutinas); }

    public boolean asignarRutina(Miembro miembro, Rutina rutina) {
        if (miembro == null || rutina == null) return false;
        if (!rutinas.contains(rutina)) return false;
        asignaciones.put(miembro, rutina);
        return true;
    }

    public boolean desasignarRutina(Miembro miembro) {
        return asignaciones.remove(miembro) != null;
    }

    public Rutina obtenerRutinaDe(Miembro miembro) { return asignaciones.get(miembro); }

    public Map<Rutina, Integer> contarPracticantesPorRutina() {
        Map<Rutina, Integer> conteo = new HashMap<>();
        for (Rutina r : asignaciones.values()) {
            conteo.put(r, conteo.getOrDefault(r, 0) + 1);
        }
        return conteo;
    }
}
