package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.repository.FirebaseRepository;


public class DeletarHorarioController {

    public static void alertaDeletarHorario(Horario horario, FragmentActivity activity) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(activity);




        TextView horarioData = new TextView(activity);
        TextView horarioDiaSemana = new TextView(activity);
        TextView horarioHora = new TextView(activity);
        horarioData.setText(horario.getDataFormatada());
        horarioDiaSemana.setText(horario.getDiaDaSemanaFormatado());
        horarioHora.setText(horario.getHoraFormatada());

        horarioData.setPadding(50, 10,50,10);
        horarioData.setTextSize(16);
        horarioData.setTextColor(Color.BLACK);
        horarioDiaSemana.setPadding(50,10,50,10);
        horarioDiaSemana.setTextColor(Color.BLACK);
        horarioDiaSemana.setTextSize(16);
        horarioHora.setPadding(50,10,50,10);
        horarioHora.setTextColor(Color.BLACK);
        horarioHora.setTextSize(16);

        LinearLayout container = new LinearLayout(activity.getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);
        container.addView(horarioData);
        container.addView(horarioDiaSemana);
        container.addView(horarioHora);

        alerta.setView(container);


        
        alerta.setTitle("Apagar Horário!").setMessage("Tem certeza disso?").setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseRepository.deleteHorarios(horario.getId());

            }
        }).setNegativeButton("Cancelar", null).show();

    }


}


