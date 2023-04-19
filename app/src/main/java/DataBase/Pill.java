package DataBase;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Pill {
    public String Name;
    public String Value;
    public LocalDateTime Hour;

    public Pill(String name, String value, LocalDateTime hora) {
        Name = name;
        Value = value;
        Hour = hora;
    }
}
