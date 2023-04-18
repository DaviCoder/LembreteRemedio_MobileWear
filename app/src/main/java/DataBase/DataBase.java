package DataBase;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBase{
    private static final ArrayList<Pill> Pills = new ArrayList<>();

    public static void AddPill(Pill pill) {
        Pills.add(pill);
    }

    public static void RemovePill(Pill pill) {
        Pills.remove(pill);
    }

    public static void RemovePill(int index) {
        Pills.remove(index);
    }

    public static Pill GetPill(int index) {
        return Pills.get(index);
    }

    public static List<Pill> GetPills() {
        return Pills;
    }

    public static void GeneretaFakeData(int count){
        for (int i = 0; i < count; i++) {
            AddPill(new Pill("Paracetamol", "500mg", LocalDateTime.now()));
            AddPill(new Pill("Ibuprofen", "400mg", LocalDateTime.now()));
            AddPill(new Pill("Aspirin", "300mg", LocalDateTime.now()));
            AddPill(new Pill("Nurofen", "200mg", LocalDateTime.now()));
            AddPill(new Pill("Panadol", "100mg", LocalDateTime.now()));
        }
    }


}
