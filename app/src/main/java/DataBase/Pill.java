package DataBase;

import androidx.annotation.NonNull;

import java.util.Date;

public class Pill {
    public String Name;
    public String Value;
    public Date Hour;

    public Pill(String name, String value, Date hora) {
        Name = name;
        Value = value;
        Hour = hora;
    }
}
