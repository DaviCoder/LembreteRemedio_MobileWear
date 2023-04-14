package Util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pillsalarm.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView ImageView;
    TextView Titulo,Value,Hour;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        ImageView = itemView.findViewById(R.id.imageview);
        Titulo = itemView.findViewById(R.id.name);
        Value = itemView.findViewById(R.id.value);
        Hour = itemView.findViewById(R.id.hour);
    }
}
