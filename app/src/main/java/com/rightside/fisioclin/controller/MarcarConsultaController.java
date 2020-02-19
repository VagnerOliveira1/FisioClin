package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.rightside.fisioclin.fragment.PacienteVerificationDataFragment;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.ConstantUtils;

public class MarcarConsultaController {
    public static void marcarConsulta(Horario horario, User user, FragmentActivity activity) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(activity);

        TextView horarioData = new TextView(activity);
        TextView horarioDiaSemana = new TextView(activity);
        TextView horarioHora = new TextView(activity);
        horarioData.setText(horario.getHoraFormatada());
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


        alerta.setTitle("Marcar Horário!").setMessage(ConstantUtils.ESCOLHER_ESSE_HORARIO).setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                PacienteVerificationDataFragment.novaInstancia(horario, user).show(activity.getSupportFragmentManager(), "consulta");

            }
        }).setNegativeButton("Cancelar", null).show();

    }


}
