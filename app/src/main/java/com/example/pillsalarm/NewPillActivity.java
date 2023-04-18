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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import DataBase.DataBase;
import DataBase.Pill;

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
            String nome = binding.nomeTxt.getText().toString();
            String descricao = binding.descricaoTxt.getText().toString();
            String data = binding.dataTxt.getText().toString();
            String hora = binding.horaTxt.getText().toString();

            if (nome == "" || descricao == "" || data == "" || hora == "") {
                Toast.makeText(this, "Preencha todas as informações!", Toast.LENGTH_LONG).show();
                return;
            }
            int day = Integer.parseInt(data.split("/")[0]);
            int mouth = Integer.parseInt(data.split("/")[1]);
            int year = Integer.parseInt(data.split("/")[2]);

            int hour = Integer.parseInt(hora.split(":")[0]);
            int minutes = Integer.parseInt(hora.split(":")[1]);


            LocalDateTime dataHora = LocalDate.of(year,mouth,day).atTime(hour,minutes);

            Pill pill = new Pill(nome,descricao, dataHora);
            DataBase.AddPill(pill);

            List<Pill> datas = DataBase.GetPills();
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
