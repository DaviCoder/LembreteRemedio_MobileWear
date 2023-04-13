package com.lembrarremedio;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lembrarremedio.databinding.ActivityMainBinding;

import DataBase.DataBase;
import DataBase.Remedio;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DataBase dataBase = new DataBase();
        dataBase.GeneretaFakeData(5);

        LinearLayout linearLayout = binding.LinearLoyout;

        for (Remedio remedio : dataBase.GetPills()) {
            TextView textView = new TextView(this);
            textView.setText(remedio.toString());
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT));
            textView.setTextColor(Color.RED);
            linearLayout.addView(textView);

        }
    }
}