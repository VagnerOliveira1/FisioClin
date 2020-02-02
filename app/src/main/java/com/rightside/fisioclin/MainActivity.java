package com.rightside.fisioclin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.rightside.fisioclin.controller.NovoHorarioController;
import com.rightside.fisioclin.controller.NovoHorarioDialogFragment;
import com.rightside.fisioclin.fragment.DatePickerFragment;
import com.rightside.fisioclin.fragment.HourFragment;
import com.rightside.fisioclin.models.Doctor;
import com.rightside.fisioclin.models.Hour;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.UUID;

public class MainActivity extends FragmentActivity implements HourFragment.TimePickerListener, DatePickerDialog.OnDateSetListener {
    private ImageView imageViewDoctorPicture;
    private CardView cardViewNovoHorario, cardViewHorarios;
    private Hour horario;
    private TextView textViewNameDoctor;
    private int hour,min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewDoctorPicture = findViewById(R.id.imageView_doctor_picture);
        cardViewNovoHorario = findViewById(R.id.cardview_novo_horario);
        cardViewHorarios = findViewById(R.id.cardview_horarios);
        textViewNameDoctor = findViewById(R.id.textView_name_doctor);

        FirebaseRepository.getDoctor(FirebaseRepository.getIdPersonLoggedIn()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot != null && documentSnapshot.exists()) {
                    Doctor doctor = documentSnapshot.toObject(Doctor.class);
                    alteraInformacaoPerfil(doctor);
                }
            }
        });


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

    private void alteraInformacaoPerfil(Doctor doctor) {
        textViewNameDoctor.setText(doctor.getName());
        GeralUtils.mostraImagemCircular(this, imageViewDoctorPicture, doctor.getProfilePictureUrl());
    }
}