package com.rightside.fisioclin.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.PushNotificaTionFcm;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.List;

public class NovaNotificacaoUsersController {

    public static void alertaNovaNotificacaoUsers(Medico medico, FragmentActivity fragmentActivity, DialogFragment dialogFragment, List<User> userList, Context context) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(fragmentActivity);
        LinearLayout container = new LinearLayout(fragmentActivity.getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER);
        container.setPadding(15,15,15,15);
        TextView textView = new TextView(fragmentActivity);
        textView.setTextColor(Color.BLACK);




        textView.setText("Ao enviar notificação para os usuários, todos os usuários do aplicativo irão receber, incluindo aqueles que ainda não consultaram com você. \nA notificação enviada será uma divulgação do seu trabalho, e serão consumidas 10 notificações \nNão é maravilhoso?!.");
        textView.setTextSize(16);
        container.addView(textView);

        alerta.setView(container);

        alerta.setTitle("Enviar notificação").setMessage("Deseja se divulgar para todos os usuários?")
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(medico.getNotificacao() >= 10) {
                            medico.setNotificacao(medico.getNotificacao() - 10);
                            PushNotificaTionFcm.pushNotificationUsers(userList, "Eii, tem sentido algo?", "Me chamo " + medico.getName() + " e estou atendendo agora!! Marque já sua consulta no app comigo!");
                            GeralUtils.mostraAlerta("Sucesso!", "suas notificações foram enviadas para os usuários.", fragmentActivity);
                            dialogFragment.dismiss();
                        } else {
                            GeralUtils.mostraAlerta("Falha", "Você não possui créditos de notificações suficientes, compre com FisioPoints na Loja", context);
                        }


                    }}).setNegativeButton("Cancelar", null).show();
    }
}
