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
import android.widget.Toast;
import com.example.pillsalarm.databinding.NewPillBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
            String nome = binding.nomeTxt.getText().toString();
            String descricao = binding.descricaoTxt.getText().toString();
            String data = binding.dataTxt.getText().toString();
            String hora = binding.horaTxt.getText().toString();

            if (nome.isEmpty() || descricao.isEmpty() || hora.isEmpty()) {
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
            Toast.makeText(this, "Nova pílula adicionada", Toast.LENGTH_SHORT).show();
            this.finish();
        });

        mTimePickerDialog = new TimePickerDialog(this, (timePicker, hourOfDay, minute) ->
            binding.horaTxt.setText(String.format("%02d:%02d", hourOfDay, minute)),
                LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(), true);

        mDatePickerDialog = new DatePickerDialog(this, (datePicker, year, month, dayOfMonth) ->
            binding.dataTxt.setText(String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)),
                LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth());

        binding.btnSelecionarHora.setOnClickListener(view -> mTimePickerDialog.show());
        binding.btnSelecionarData.setOnClickListener(view -> mDatePickerDialog.show());
    }

    private void CriarAlarme(Pill pill) {

        int year = pill.Hour.getYear();
        int month = pill.Hour.getMonthValue();
        int dayOfMonth = pill.Hour.getDayOfMonth();
        int hourOfDay = pill.Hour.getHour();
        int minute = pill.Hour.getMinute();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
}
