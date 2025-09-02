import java.util.*;
import java.time.LocalDate;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        boolean seguir = true;

        while (seguir) {
            System.out.println("\n=== Sistema de Administración de Gimnasio ===");
            System.out.println("1) Agregar miembro");
            System.out.println("2) Eliminar miembro");
            System.out.println("3) Agregar entrenador");
            System.out.println("4) Eliminar entrenador");
            System.out.println("5) Agregar rutina");
            System.out.println("6) Eliminar rutina");
            System.out.println("7) Asignar entrenador a miembro");
            System.out.println("8) Desasignar entrenador de miembro");
            System.out.println("9) Asignar rutina a miembro");
            System.out.println("10) Desasignar rutina de miembro");
            System.out.println("11) Listar miembros");
            System.out.println("12) Listar entrenadores");
            System.out.println("13) Listar rutinas");
            System.out.println("14) Reportes");
            System.out.println("15) Salir");
            System.out.print("Opción: ");

            int op = leerEntero();
            switch (op) {
                case 1 -> agregarMiembro(sistema);
                case 2 -> eliminarMiembro(sistema);
                case 3 -> agregarEntrenador(sistema);
                case 4 -> eliminarEntrenador(sistema);
                case 5 -> agregarRutina(sistema);
                case 6 -> eliminarRutina(sistema);
                case 7 -> asignarEntrenadorAMiembro(sistema);
                case 8 -> desasignarEntrenadorDeMiembro(sistema);
                case 9 -> asignarRutinaAMiembro(sistema);
                case 10 -> desasignarRutinaDeMiembro(sistema);
                case 11 -> listarMiembros(sistema);
                case 12 -> listarEntrenadores(sistema);
                case 13 -> listarRutinas(sistema);
                case 14 -> menuReportes(sistema);
                case 15 -> {
                    System.out.println("¡Hasta luego!");
                    seguir = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    // =================== Helpers de lectura ===================

    private static int leerEntero() {
        while (true) {
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                return n;
            } catch (Exception e) {
                System.out.print("Número inválido. Intenta de nuevo: ");
            }
        }
    }

    private static boolean leerSiNo(String prompt) {
        System.out.print(prompt + " (s/n): ");
        String r = sc.nextLine().trim().toLowerCase();
        return r.equals("s") || r.equals("si") || r.equals("sí");
    }

    private static LocalDate leerFechaISO(String prompt) {
        // Formato esperado: YYYY-MM-DD
        while (true) {
            System.out.print(prompt + " (YYYY-MM-DD): ");
            String txt = sc.nextLine().trim();
            try {
                return LocalDate.parse(txt);
            } catch (Exception e) {
                System.out.println("Fecha inválida. Ejemplo válido: 2025-09-01");
            }
        }
    }

    // =================== Miembros ===================

    private static void agregarMiembro(Sistema sistema) {
        System.out.print("Nombre del miembro: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Meses inscrito (tiempo): ");
        int tiempo = leerEntero();

        System.out.print("Calidad de membresía (básico/intermedio/avanzado): ");
        String calidad = sc.nextLine().trim();

        LocalDate fin = leerFechaISO("Fecha de fin de membresía");

        Miembro m = new Miembro(nombre, tiempo, calidad, fin);
        boolean ok = sistema.getGestorMiembros().agregarMiembro(m);
        System.out.println(ok ? "Miembro agregado." : "No se pudo agregar.");
    }

    private static void eliminarMiembro(Sistema sistema) {
        listarMiembros(sistema);
        System.out.print("Nombre exacto del miembro a eliminar: ");
        String nombre = sc.nextLine().trim();
        Miembro m = sistema.getGestorMiembros().buscarMiembroPorNombre(nombre);
        if (m == null) {
            System.out.println("No existe ese miembro.");
            return;
        }
        boolean ok = sistema.getGestorMiembros().eliminarMiembro(m);
        System.out.println(ok ? "Miembro eliminado." : "No se pudo eliminar.");
    }

    private static void listarMiembros(Sistema sistema) {
        List<Miembro> ms = sistema.getGestorMiembros().listarMiembros();
        if (ms.isEmpty()) {
            System.out.println("(Sin miembros)");
        } else {
            System.out.println("— Miembros —");
            for (int i = 0; i < ms.size(); i++) {
                Miembro m = ms.get(i);
                String ent = (m.getEntrenadorAsignado() == null) ? "Sin entrenador" : m.getEntrenadorAsignado().getNombre();
                System.out.println((i + 1) + ". " + m.getNombre()
                        + " | Membresía: " + m.getCalidadMembresia()
                        + " | Fin: " + m.getFechaFinMembresia()
                        + " | Entrenador: " + ent);
            }
        }
    }

    // =================== Entrenadores ===================

    private static void agregarEntrenador(Sistema sistema) {
        System.out.print("Nombre del entrenador: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Tipo de rutina que imparte (pérdida de peso/fuerza/etc.): ");
        String tipo = sc.nextLine().trim();

        Entrenador e = new Entrenador(nombre, tipo);
        boolean ok = sistema.getGestorEntrenadores().agregarEntrenador(e);
        System.out.println(ok ? "Entrenador agregado." : "No se pudo agregar.");
    }

    private static void eliminarEntrenador(Sistema sistema) {
        listarEntrenadores(sistema);
        System.out.print("Nombre exacto del entrenador a eliminar: ");
        String nombre = sc.nextLine().trim();
        Entrenador e = sistema.getGestorEntrenadores().buscarEntrenadorPorNombre(nombre);
        if (e == null) {
            System.out.println("No existe ese entrenador.");
            return;
        }
        boolean ok = sistema.getGestorEntrenadores().eliminarEntrenador(e);
        System.out.println(ok ? "Entrenador eliminado." : "No se pudo eliminar.");
    }

    private static void listarEntrenadores(Sistema sistema) {
        List<Entrenador> es = sistema.getGestorEntrenadores().listarEntrenadores();
        if (es.isEmpty()) {
            System.out.println("(Sin entrenadores)");
        } else {
            System.out.println("— Entrenadores —");
            for (int i = 0; i < es.size(); i++) {
                Entrenador e = es.get(i);
                System.out.println((i + 1) + ". " + e.getNombre()
                        + " | Tipo: " + e.getTipoRutina()
                        + " | Alumnos: " + e.getCantidadAlumnos());
            }
        }
    }

    private static void asignarEntrenadorAMiembro(Sistema sistema) {
        listarMiembros(sistema);
        System.out.print("Nombre del miembro: ");
        String nMiembro = sc.nextLine().trim();
        Miembro m = sistema.getGestorMiembros().buscarMiembroPorNombre(nMiembro);
        if (m == null) {
            System.out.println("Miembro no encontrado.");
            return;
        }

        listarEntrenadores(sistema);
        System.out.print("Nombre del entrenador: ");
        String nEntr = sc.nextLine().trim();
        Entrenador e = sistema.getGestorEntrenadores().buscarEntrenadorPorNombre(nEntr);
        if (e == null) {
            System.out.println("Entrenador no encontrado.");
            return;
        }

        boolean ok = sistema.getGestorEntrenadores().asignarEntrenadorAMiembro(e, m);
        System.out.println(ok ? "Entrenador asignado." : "No se pudo asignar.");
    }

    private static void desasignarEntrenadorDeMiembro(Sistema sistema) {
        listarMiembros(sistema);
        System.out.print("Nombre del miembro: ");
        String nMiembro = sc.nextLine().trim();
        Miembro m = sistema.getGestorMiembros().buscarMiembroPorNombre(nMiembro);
        if (m == null) {
            System.out.println("Miembro no encontrado.");
            return;
        }
        Entrenador e = m.getEntrenadorAsignado();
        if (e == null) {
            System.out.println("Este miembro no tiene entrenador asignado.");
            return;
        }
        boolean ok = sistema.getGestorEntrenadores().desasignarEntrenadorDeMiembro(e, m);
        System.out.println(ok ? "Entrenador desasignado." : "No se pudo desasignar.");
    }

    // =================== Rutinas ===================

    private static void agregarRutina(Sistema sistema) {
        System.out.print("Nombre de la rutina: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Objetivo (pérdida de peso/músculo/condición): ");
        String objetivo = sc.nextLine().trim();

        boolean activa = leerSiNo("¿La rutina está activa?");
        Rutina r = new Rutina(nombre, objetivo, activa);

        // Cargar ejercicios (opcional)
        boolean agregarMas = leerSiNo("¿Desea agregar ejercicios ahora?");
        while (agregarMas) {
            System.out.print("Nombre del ejercicio: ");
            String ej = sc.nextLine().trim();
            if (ej.isEmpty()) {
                System.out.println("Ejercicio vacío, no agregado.");
            } else {
                r.agregarEjercicio(ej);
                System.out.println("Ejercicio agregado.");
            }
            agregarMas = leerSiNo("¿Agregar otro ejercicio?");
        }

        boolean ok = sistema.getGestorRutinas().agregarRutina(r);
        System.out.println(ok ? "Rutina agregada." : "No se pudo agregar.");
    }

    private static void eliminarRutina(Sistema sistema) {
        listarRutinas(sistema);
        System.out.print("Nombre exacto de la rutina a eliminar: ");
        String nombre = sc.nextLine().trim();

        // buscar por nombre simple
        Rutina objetivo = null;
        for (Rutina r : sistema.getGestorRutinas().listarRutinas()) {
            if (r.getNombre().equalsIgnoreCase(nombre)) {
                objetivo = r;
                break;
            }
        }
        if (objetivo == null) {
            System.out.println("Rutina no encontrada.");
            return;
        }
        boolean ok = sistema.getGestorRutinas().eliminarRutina(objetivo);
        System.out.println(ok ? "Rutina eliminada." : "No se pudo eliminar.");
    }

    private static void listarRutinas(Sistema sistema) {
        List<Rutina> rs = sistema.getGestorRutinas().listarRutinas();
        if (rs.isEmpty()) {
            System.out.println("(Sin rutinas)");
        } else {
            System.out.println("— Rutinas —");
            for (int i = 0; i < rs.size(); i++) {
                Rutina r = rs.get(i);
                System.out.println((i + 1) + ". " + r.getNombre()
                        + " | Objetivo: " + r.getObjetivo()
                        + " | Activa: " + (r.isActiva() ? "Sí" : "No")
                        + " | Ejercicios: " + r.getEjercicios().size());
            }
        }
    }

    private static void asignarRutinaAMiembro(Sistema sistema) {
        listarMiembros(sistema);
        System.out.print("Nombre del miembro: ");
        String nMiembro = sc.nextLine().trim();
        Miembro m = sistema.getGestorMiembros().buscarMiembroPorNombre(nMiembro);
        if (m == null) {
            System.out.println("Miembro no encontrado.");
            return;
        }

        listarRutinas(sistema);
        System.out.print("Nombre de la rutina: ");
        String nRutina = sc.nextLine().trim();

        Rutina r = null;
        for (Rutina x : sistema.getGestorRutinas().listarRutinas()) {
            if (x.getNombre().equalsIgnoreCase(nRutina)) { r = x; break; }
        }
        if (r == null) {
            System.out.println("Rutina no encontrada.");
            return;
        }

        boolean ok = sistema.getGestorRutinas().asignarRutina(m, r);
        System.out.println(ok ? "Rutina asignada." : "No se pudo asignar.");
    }

    private static void desasignarRutinaDeMiembro(Sistema sistema) {
        listarMiembros(sistema);
        System.out.print("Nombre del miembro: ");
        String nMiembro = sc.nextLine().trim();
        Miembro m = sistema.getGestorMiembros().buscarMiembroPorNombre(nMiembro);
        if (m == null) {
            System.out.println("Miembro no encontrado.");
            return;
        }

        boolean ok = sistema.getGestorRutinas().desasignarRutina(m);
        System.out.println(ok ? "Rutina desasignada." : "No se pudo desasignar (¿tenía rutina?).");
    }

    // =================== Reportes ===================

    private static void menuReportes(Sistema sistema) {
        System.out.println("\n--- Reportes ---");
        System.out.println("1) Rutina más popular");
        System.out.println("2) Número de rutinas activas");
        System.out.println("3) Entrenador con más alumnos");
        System.out.print("Opción: ");
        int op = leerEntero();

        switch (op) {
            case 1 -> {
                Rutina top = sistema.getReportes().rutinaMasPopular(sistema.getGestorRutinas());
                if (top == null) System.out.println("No hay datos suficientes.");
                else System.out.println("Rutina más popular: " + top.getNombre());
            }
            case 2 -> {
                int total = sistema.getReportes().totalRutinasActivas(sistema.getGestorRutinas());
                System.out.println("Rutinas activas: " + total);
            }
            case 3 -> {
                Entrenador mejor = sistema.getReportes().entrenadorConMasAlumnos(sistema.getGestorEntrenadores());
                if (mejor == null) System.out.println("No hay entrenadores registrados.");
                else System.out.println("Entrenador con más alumnos: " + mejor.getNombre()
                        + " (" + mejor.getCantidadAlumnos() + " alumnos)");
            }
            default -> System.out.println("Opción inválida.");
        }
    }
}
