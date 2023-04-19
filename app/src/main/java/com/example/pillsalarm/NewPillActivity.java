package com.example.pillsalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.SystemClock;
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
import Util.AlarmReceiver;

public class NewPillActivity extends Activity {
    private NewPillBinding binding;

    private TimePickerDialog mTimePickerDialog;
    private DatePickerDialog mDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NewPillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int pos = getIntent().getIntExtra("POS",-1);

        if(pos != -1){
            Pill pill = DataBase.GetPills().get(pos);

            binding.nomeTxt.setText(pill.Name);
            binding.descricaoTxt.setText(pill.Value);
            binding.dataTxt.setText(pill.Hour.getDayOfMonth() + "/" + pill.Hour.getMonthValue() + "/"  +  pill.Hour.getYear());
            binding.horaTxt.setText(pill.Hour.getHour() + ":" + pill.Hour.getMinute());
        }

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
            if(pos != -1){
                DataBase.UpdatePill(pos,pill);
            }else{
                DataBase.AddPill(pill);
            }

            CriarAlarme(pill);
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

    private void CriarAlarme(Pill pill) {
        // Obter os valores da hora e da data desejados
        int year = pill.Hour.getYear();
        int month = pill.Hour.getMonthValue(); // Janeiro é 0, Fevereiro é 1, Março é 2, etc.
        int day = pill.Hour.getDayOfMonth();
        int hour = pill.Hour.getHour();
        int minute = pill.Hour.getMinute();

// Criar um objeto de calendário com os valores da hora e da data definidos
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

// Definir o tempo de disparo do alarme usando o objeto de calendário
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
}
