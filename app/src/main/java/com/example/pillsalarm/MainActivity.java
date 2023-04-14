package com.example.pillsalarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pillsalarm.databinding.ActivityMainBinding;

import DataBase.DataBase;
import Util.MyAdapter;

public class MainActivity extends Activity {
    private ActivityMainBinding binding;
    private DataBase dataBase;

    private Button newPillBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase = new DataBase();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        newPillBtn = binding.newPillBtn;
        dataBase.GeneretaFakeData(5);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), dataBase.GetPills()));

        newPillBtn.setOnClickListener(v -> {
            //abrir tela de nova pílula
            Intent intent = new Intent(this, NewPillActivity.class);
            startActivity(intent);
        });
    }
}