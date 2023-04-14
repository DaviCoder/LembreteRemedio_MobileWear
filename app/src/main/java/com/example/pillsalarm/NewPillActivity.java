package com.example.pillsalarm;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pillsalarm.databinding.ActivityMainBinding;
import com.example.pillsalarm.databinding.NewPillBinding;

public class NewPillActivity extends Activity {
    private NewPillBinding binding;

    private TimePickerDialog mTimePickerDialog;
    private DatePickerDialog mDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NewPillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.NewPillBtnNew.setOnClickListener(v -> {
            Toast.makeText(this, "Nova pílula adicionada", Toast.LENGTH_SHORT).show();
            this.finish();
        });

        mTimePickerDialog = new TimePickerDialog(this, (timePicker, hourOfDay, minute) ->
            binding.horaTxt.setText(String.format("%02d:%02d", hourOfDay, minute)),
                12, 0, true);

        mDatePickerDialog = new DatePickerDialog(this, (datePicker, year, month, dayOfMonth) ->
            binding.dataTxt.setText(String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)),
                2021, 0, 1);

        binding.btnSelecionarHora.setOnClickListener(view -> mTimePickerDialog.show());
        binding.btnSelecionarData.setOnClickListener(view -> mDatePickerDialog.show());
    }
}
