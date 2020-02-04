package com.rightside.fisioclin;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.rightside.fisioclin.controller.NovoHorarioController;
import com.rightside.fisioclin.controller.NovoHorarioDialogFragment;
import com.rightside.fisioclin.fragment.DatePickerFragment;
import com.rightside.fisioclin.fragment.HourFragment;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.UUID;

public class MainActivity extends FragmentActivity implements HourFragment.TimePickerListener, DatePickerDialog.OnDateSetListener {
    private ImageView imageViewDoctorPicture;
    private CardView cardViewNovoHorario, cardViewHorarios,cardViewMinhasConsultasMedico;
    private Horario horario;
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
        cardViewMinhasConsultasMedico = findViewById(R.id.card_view_minhas_consultas_medico);

        FirebaseRepository.getMedico(FirebaseRepository.getIdPessoaLogada()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot != null && documentSnapshot.exists()) {
                    Medico medico = documentSnapshot.toObject(Medico.class);
                    alteraInformacaoPerfil(medico);
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

        cardViewMinhasConsultasMedico.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ConsultaActivity.class));
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
        horario = new Horario(hour, min, year, dayOfWeek, month, uniqueId);
        NovoHorarioDialogFragment.novoHorarioDialogFragment(horario).show(getSupportFragmentManager(), "horario");
    }

    private void alteraInformacaoPerfil(Medico medico) {
        textViewNameDoctor.setText(medico.getName());
        GeralUtils.mostraImagemCircular(this, imageViewDoctorPicture, medico.getProfilePictureUrl());
    }
}