package com.rightside.fisioclin;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.rightside.fisioclin.controller.NovoHorarioController;
import com.rightside.fisioclin.controller.NovoHorarioDialogFragment;
import com.rightside.fisioclin.fragment.DatePickerFragment;
import com.rightside.fisioclin.fragment.HourFragment;
import com.rightside.fisioclin.models.Doctor;
import com.rightside.fisioclin.models.Hour;

import java.util.UUID;

public class MainActivity extends FragmentActivity implements HourFragment.TimePickerListener, DatePickerDialog.OnDateSetListener {
    private ImageView imageViewDoctorPicture;
    private CardView cardViewNovoHorario, cardViewHorarios;
    private Hour horario;
    private int hour,min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewDoctorPicture = findViewById(R.id.imageView_doctor_picture);
        cardViewNovoHorario = findViewById(R.id.cardview_novo_horario);
        cardViewHorarios = findViewById(R.id.cardview_horarios);

        cardViewNovoHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NovoHorarioController.alertaDeNovoHorario(MainActivity.this);

            }
        });

        cardViewHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HorarioDoctorActivity.class));
            }
        });
        Doctor doctor = new Doctor("Priscila", "www.google.com", "05/12/1995", "Feminino", "32991313947");
        doctor.setProfilePictureUrl("http://3.bp.blogspot.com/-xVhOdAS5SWw/T-UNX6qzuHI/AAAAAAAAAks/uC4eXUcXmK4/s1600/IMG_4214.jpg");

        Glide.with(this).load(doctor.getProfilePictureUrl()).circleCrop().into(imageViewDoctorPicture);

        Log.d("teste", String.valueOf(doctor.isAdmin()));


    }

    @Override
    public void OnTimeSet(TimePicker timePicker, int hour, int min) {
      this.hour = hour;
      this.min = min;
       DatePickerFragment datePickerFragment = new DatePickerFragment();
       datePickerFragment.show(getSupportFragmentManager(), "data");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfWeek) {
        String uniqueId = UUID.randomUUID().toString();
        horario = new Hour(hour, min, year, dayOfWeek, month, uniqueId);
        NovoHorarioDialogFragment.novoHorarioDialogFragment(horario).show(getSupportFragmentManager(), "horario");
    }
}