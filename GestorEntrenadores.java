import java.util.ArrayList;
import java.util.List;

public class GestorEntrenadores {
    private final List<Entrenador> entrenadores = new ArrayList<>();

    public boolean agregarEntrenador(Entrenador entrenador) {
        if (entrenador == null) return false;
        return entrenadores.add(entrenador);
    }

    public boolean eliminarEntrenador(Entrenador entrenador) {
        return entrenadores.remove(entrenador);
    }

    public Entrenador buscarEntrenadorPorNombre(String nombre) {
        for (Entrenador e : entrenadores) {
            if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        }
        return null;
    }

    public List<Entrenador> listarEntrenadores() {
        return new ArrayList<>(entrenadores);
    }

    // Asignaciones básicas (sin prints, para usar desde Main)
    public boolean asignarEntrenadorAMiembro(Entrenador entrenador, Miembro miembro) {
        if (entrenador == null || miembro == null) return false;
        boolean ok = entrenador.agregarAlumno(miembro);
        if (ok) miembro.asignarEntrenador(entrenador);
        return ok;
    }

    public boolean desasignarEntrenadorDeMiembro(Entrenador entrenador, Miembro miembro) {
        if (entrenador == null || miembro == null) return false;
        boolean ok = entrenador.eliminarAlumno(miembro);
        if (ok && miembro.getEntrenadorAsignado() == entrenador) {
            miembro.asignarEntrenador(null);
        }
        return ok;
    }
}
