package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.rightside.fisioclin.models.Hour;
import com.rightside.fisioclin.repository.FirebaseRepository;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HorarioActivity extends AppCompatActivity {


    private TextView textViewData, textViewDiaSemana, textViewHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        textViewData = findViewById(R.id.textview_data);
        textViewHora = findViewById(R.id.textview_hora);
        textViewDiaSemana = findViewById(R.id.textView_dia_semana);

        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        Hour horario = (Hour) bundle.getSerializable("horario");



        textViewHora.setText(horario.getHoraFormatada());
        textViewData.setText(horario.getDiaDaSemanaFormatado());

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        FirebaseRepository.save(horario);

    }

}
