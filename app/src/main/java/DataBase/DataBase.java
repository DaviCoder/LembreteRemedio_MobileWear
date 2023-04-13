package DataBase;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final ArrayList<Remedio> Pills;

    public DataBase() {
        Pills = new ArrayList<Remedio>();
    }

    public void AddPill(Remedio pill) {
        Pills.add(pill);
    }

    public void RemovePill(Remedio pill) {
        Pills.remove(pill);
    }

    public void RemovePill(int index) {
        Pills.remove(index);
    }

    public Remedio GetPill(int index) {
        return Pills.get(index);
    }

    public List<Remedio> GetPills() {
        return Pills;
    }

    public void GeneretaFakeData(int count){
        for (int i = 0; i < count; i++) {
            AddPill(new Remedio("Paracetamol", "500mg"));
            AddPill(new Remedio("Ibuprofen", "400mg"));
            AddPill(new Remedio("Aspirin", "300mg"));
            AddPill(new Remedio("Nurofen", "200mg"));
            AddPill(new Remedio("Panadol", "100mg"));
        }
    }
}
