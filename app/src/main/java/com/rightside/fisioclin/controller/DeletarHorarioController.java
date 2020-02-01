package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.fragment.app.FragmentActivity;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Hour;
import com.rightside.fisioclin.repository.FirebaseRepository;

import org.w3c.dom.Text;


public class DeletarHorarioController {

    public static void alertaDeletarHorario(Hour hour, FragmentActivity activity) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(activity);




        TextView horarioData = new TextView(activity);
        TextView horarioDiaSemana = new TextView(activity);
        TextView horarioHora = new TextView(activity);
        horarioData.setText(hour.getDataFormatada());
        horarioDiaSemana.setText(hour.getDiaDaSemanaFormatado());
        horarioHora.setText(hour.getHoraFormatada());

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
                FirebaseRepository.deleteHorarios(hour.getId());
                activity.recreate();
            }
        }).setNegativeButton("Cancelar", null).show();

    }


}


