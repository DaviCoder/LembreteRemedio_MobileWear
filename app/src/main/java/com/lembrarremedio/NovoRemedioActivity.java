package com.lembrarremedio;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lembrarremedio.databinding.ActivityNovoRemedioBinding;

public class NovoRemedioActivity extends Activity {
    private ActivityNovoRemedioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNovoRemedioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}