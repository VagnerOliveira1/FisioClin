package com.rightside.fisioclin.controller;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.rightside.fisioclin.PushNotificaTionFcm;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;


import java.util.List;

public class NovaNotificacaoPacientesController {

    public static void alertaNovaNotificacao(Medico medico, FragmentActivity fragmentActivity, DialogFragment dialogFragment, List<Consulta> consultaList) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(fragmentActivity);
        LinearLayout container = new LinearLayout(fragmentActivity.getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER);
        container.setPadding(15,15,15,15);
        TextView textView = new TextView(fragmentActivity);
        textView.setTextColor(Color.BLACK);

        textView.setText("Ao enviar notificação para os pacientes, todos que possuem uma consulta marcada serão alertados dos seus respectivos horários");
        container.addView(textView);
        alerta.setView(container);

        alerta.setTitle("Enviar Notificacao").setMessage("Deseja lembrar os pacientes de suas consultas?")
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(medico.getNotificacao() > 0) {
                            medico.setNotificacao(medico.getNotificacao() - 1);
                            FirebaseRepository.atualizaPontoMedico(medico);
                            PushNotificaTionFcm.pushNotificationPacientes(consultaList);
                            GeralUtils.mostraAlerta("Atenção!", "suas notificações foram enviadas com sucesso! \nVocê utilizou 1 notificação", fragmentActivity);
                            dialogFragment.dismiss();
                        } else {
                            GeralUtils.mostraAlerta("Falha!", "Você não possui crédito de notificação suficiente, compre uma utilizando FisioPoints na loja", fragmentActivity);
                            dialogFragment.dismiss();
                        }


                    }}).setNegativeButton("Cancelar", null).show();
    }
}
