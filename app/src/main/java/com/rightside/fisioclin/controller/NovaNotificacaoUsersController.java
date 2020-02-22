package com.rightside.fisioclin.controller;

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
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.List;

public class NovaNotificacaoUsersController {

    public static void alertaNovaNotificacaoUsers(FragmentActivity fragmentActivity, DialogFragment dialogFragment, List<User> userList) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(fragmentActivity);
        LinearLayout container = new LinearLayout(fragmentActivity.getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER);
        container.setPadding(15,15,15,15);
        TextView textView = new TextView(fragmentActivity);
        textView.setTextColor(Color.BLACK);
        TextView textViewAbuse = new TextView(fragmentActivity);
        TextInputEditText textInputEditTextTitulo = new TextInputEditText(fragmentActivity);
        textInputEditTextTitulo.setHint("Titulo");

        TextInputEditText textInputEditTextMensagem = new TextInputEditText(fragmentActivity);
        textInputEditTextMensagem.setHint("Mensagem");
        textViewAbuse.setText("Atenção! não abuse disso, pode causar rejeição...");
        textViewAbuse.setTextColor(Color.BLACK);


        textView.setText("Ao enviar notificação para os usuarios, todos irão receber, incluindo os pacientes.");
        container.addView(textView);
        container.addView(textViewAbuse);
        container.addView(textInputEditTextTitulo);
        container.addView(textInputEditTextMensagem);

        alerta.setView(container);

        alerta.setTitle("Nova notificação!").setMessage("Deseja enviar notificação para todos os usuários?")
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PushNotificaTionFcm.pushNotificationUsers(userList, textInputEditTextTitulo.getText().toString(), textInputEditTextMensagem.getText().toString());
                        GeralUtils.mostraAlerta("Atenção!", "suas notificações foram enviadas com sucesso!", fragmentActivity);
                        dialogFragment.dismiss();

                    }}).setNegativeButton("Cancelar", null).show();
    }
}
