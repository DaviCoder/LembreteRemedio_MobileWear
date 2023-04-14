package DataBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBase {
    private final ArrayList<Pill> Pills;

    public DataBase() {
        Pills = new ArrayList<Pill>();
    }

    public void AddPill(Pill pill) {
        Pills.add(pill);
    }

    public void RemovePill(Pill pill) {
        Pills.remove(pill);
    }

    public void RemovePill(int index) {
        Pills.remove(index);
    }

    public Pill GetPill(int index) {
        return Pills.get(index);
    }

    public List<Pill> GetPills() {
        return Pills;
    }

    public void GeneretaFakeData(int count){
        for (int i = 0; i < count; i++) {
            AddPill(new Pill("Paracetamol", "500mg", new Date()));
            AddPill(new Pill("Ibuprofen", "400mg", new Date()));
            AddPill(new Pill("Aspirin", "300mg", new Date()));
            AddPill(new Pill("Nurofen", "200mg", new Date()));
            AddPill(new Pill("Panadol", "100mg", new Date()));
        }
    }


}
