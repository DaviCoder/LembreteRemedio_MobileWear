package Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pillsalarm.R;

import java.util.Date;
import java.util.List;

import DataBase.Pill;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context Context;
    List<Pill> Pills;

    public MyAdapter(android.content.Context context, List<Pill> pills) {
        Context = context;
        Pills = pills;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(Context).inflate(R.layout.pill_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Titulo.setText(Pills.get(position).Name);
        holder.Value.setText(Pills.get(position).Value);
        holder.Hour.setText(MontarHorario(Pills.get(position).Hour));
        holder.ImageView.setImageResource(com.google.android.gms.base.R.drawable.googleg_standard_color_18);
    }

    @Override
    public int getItemCount() {
        return Pills.size();
    }

    private String MontarHorario(Date Hour){
        return Hour.getDay() + "/" + Hour.getMonth() + "/"  +  Hour.getYear()  + "   " + Hour.getHours() + ":" + Hour.getMinutes();
    }
}
