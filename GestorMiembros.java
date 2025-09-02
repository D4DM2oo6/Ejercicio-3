import java.util.ArrayList;
import java.util.List;

public class GestorMiembros {
    private final List<Miembro> miembros = new ArrayList<>();

    public boolean agregarMiembro(Miembro miembro) {
        if (miembro == null) return false;
        return miembros.add(miembro);
    }

    public boolean eliminarMiembro(Miembro miembro) {
        return miembros.remove(miembro);
    }

    public Miembro buscarMiembroPorNombre(String nombre) {
        for (Miembro m : miembros) {
            if (m.getNombre().equalsIgnoreCase(nombre)) return m;
        }
        return null;
    }

    public List<Miembro> listarMiembros() {
        return new ArrayList<>(miembros);
    }
}
