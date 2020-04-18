package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.fragment.PacienteVerificationDataFragment;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.repository.FirebaseRepository;

import com.rightside.fisioclin.utils.ConstantUtils;
import com.rightside.fisioclin.utils.GeralUtils;


public class MarcarConsultaController {
    public static void marcarConsulta(Horario horario, User user, FragmentActivity activity) {
        final androidx.appcompat.app.AlertDialog.Builder alerta = new androidx.appcompat.app.AlertDialog.Builder(activity);

        TextView horarioData = new TextView(activity);
        TextView horarioDiaSemana = new TextView(activity);
        TextView horarioHora = new TextView(activity);
        horarioData.setText(horario.getDataFormatada());
        horarioDiaSemana.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));
        horarioHora.setText(horario.getHoraFormatada());
        ImageView calendario = new ImageView(activity);
        ImageView relogio = new ImageView(activity);
        ImageView calendariot = new ImageView(activity);


        horarioData.setTextSize(14);
        horarioData.setTextColor(Color.BLACK);
        horarioDiaSemana.setTextColor(Color.BLACK);
        horarioDiaSemana.setTextSize(14);
        horarioHora.setTextColor(Color.BLACK);
        horarioHora.setTextSize(14);
        relogio.setPadding(15,0,15,0);
        calendario.setImageResource(R.drawable.ic_date_range_black_24dp);
        relogio.setImageResource(R.drawable.ic_access_time_black_24dp);

        calendariot.setImageResource(R.drawable.ic_date_range_black_24dp);

        LinearLayout container = new LinearLayout(activity.getApplicationContext());
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setGravity(Gravity.CENTER);
        container.addView(calendario);
        container.addView(horarioData);
        container.addView(relogio);
        container.addView(horarioHora);
        container.addView(calendariot);
        container.addView(horarioDiaSemana);
        alerta.setView(container);


        alerta.setTitle("Confirmar consulta:").setMessage(ConstantUtils.ESCOLHER_ESSE_HORARIO).setPositiveButton("SIM", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                PacienteVerificationDataFragment.novaInstancia(horario, user).show(activity.getSupportFragmentManager(), "consulta");

            }
        }).setNegativeButton("Cancelar", null).show();

    }


}
