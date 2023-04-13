package DataBase;

import androidx.annotation.NonNull;

public class Remedio {
    public String Nome;
    public String Valor;

    public Remedio(String nome, String valor) {
        Nome = nome;
        Valor = valor;
    }

    @NonNull
    @Override
    public String toString() {
        return Nome + " - " + Valor;
    }
}
