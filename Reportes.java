import java.util.*;

public class Reportes {

    public Rutina rutinaMasPopular(GestorRutinas gestorRutinas) {
        Map<Rutina, Integer> conteo = gestorRutinas.contarPracticantesPorRutina();
        Rutina maxRutina = null;
        int max = -1;
        for (Map.Entry<Rutina, Integer> e : conteo.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                maxRutina = e.getKey();
            }
        }
        return maxRutina; // puede ser null si nadie tiene rutina
    }

    public int totalRutinasActivas(GestorRutinas gestorRutinas) {
        int c = 0;
        for (Rutina r : gestorRutinas.listarRutinas()) {
            if (r.isActiva()) c++;
        }
        return c;
    }

    public Entrenador entrenadorConMasAlumnos(GestorEntrenadores gestorEntrenadores) {
        Entrenador mejor = null;
        int max = -1;
        for (Entrenador e : gestorEntrenadores.listarEntrenadores()) {
            int n = e.getCantidadAlumnos();
            if (n > max) { max = n; mejor = e; }
        }
        return mejor; // puede ser null si no hay entrenadores
    }
}
