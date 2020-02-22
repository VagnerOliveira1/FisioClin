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
import com.rightside.fisioclin.utils.GeralUtils;


import java.util.List;

public class NovaNotificacaoPacientesController {

    public static void alertaNovaNotificacao(FragmentActivity fragmentActivity, DialogFragment dialogFragment, List<Consulta> consultaList) {
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

        alerta.setTitle("Enviar Notificacao!").setMessage("Deseja enviar notificação para os pacientes?")
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PushNotificaTionFcm.pushNotificationPacientes(consultaList);
                        GeralUtils.mostraAlerta("Atenção!", "suas notificações foram enviadas com sucesso!", fragmentActivity);

                        dialogFragment.dismiss();

                    }}).setNegativeButton("Cancelar", null).show();
    }
}
