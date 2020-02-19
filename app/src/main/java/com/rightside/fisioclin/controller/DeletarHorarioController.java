package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.repository.FirebaseRepository;
<<<<<<< HEAD
import com.rightside.fisioclin.utils.ConstantUtils;
=======
import com.rightside.fisioclin.utils.GeralUtils;
>>>>>>> 46238eda42f74f65906914c0755777030bbfb0b5


public class DeletarHorarioController {

    public static void alertaDeletarHorario(Horario horario, FragmentActivity activity) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(activity);




        TextView horarioData = new TextView(activity);
        TextView horarioDiaSemana = new TextView(activity);
        TextView horarioHora = new TextView(activity);
        ImageView calendario = new ImageView(activity);
        ImageView relogio = new ImageView(activity);
        ImageView calendariot = new ImageView(activity);
        horarioData.setText(horario.getDataFormatada());
        horarioDiaSemana.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));
        horarioHora.setText(horario.getHoraFormatada());
        relogio.setPadding(10,0,10,0);
        calendario.setImageResource(R.drawable.ic_date_range_black_24dp);
        relogio.setImageResource(R.drawable.ic_access_time_black_24dp);
        calendariot.setImageResource(R.drawable.ic_date_range_black_24dp);


        horarioData.setTextSize(14);
        horarioData.setTextColor(Color.BLACK);
        horarioDiaSemana.setTextColor(Color.BLACK);
        horarioDiaSemana.setTextSize(14);
        horarioHora.setTextColor(Color.BLACK);
        horarioHora.setTextSize(14);

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


        
<<<<<<< HEAD
        alerta.setTitle("Apagar Horário!").setMessage(ConstantUtils.TEM_CERTEZA_DISSO).setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
=======
        alerta.setTitle("Excluir Horário").setMessage("Tem certeza que deseja fazer isso?").setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
>>>>>>> 46238eda42f74f65906914c0755777030bbfb0b5
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseRepository.deleteHorarios(horario);

            }
        }).setNegativeButton("Cancelar", null).show();

    }


}


