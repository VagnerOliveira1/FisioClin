package com.rightside.fisioclin;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.GeralUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.util.List;

import fcm.androidtoandroid.FirebasePush;
import fcm.androidtoandroid.connection.PushNotificationTask;
import fcm.androidtoandroid.model.Notification;

public class PushNotificaTionFcm {



    public static void pushNotificationPacientes(List<Consulta> consultaList) {
        FirebasePush firebasePush = new FirebasePush("AIzaSyA3FZ-4UQ_4fMuzH_eVwi92gvkj5dwKdic");
        firebasePush.setAsyncResponse(new PushNotificationTask.AsyncResponse() {
            @Override
            public void onFinishPush(@NotNull String ouput) {
                Log.e("OUTPUT", ouput);
            }
        });

        for(Consulta consulta : consultaList) {
            firebasePush.setNotification(new Notification("Olá, " + consulta.getPaciente().getName() + " temos um lembrete:",
                    "Você possui uma consulta: " + GeralUtils.domiciliar(consulta.getHorario().isDomiciliar()) + " " + consulta.getHorario().getDataFormatada() + " " + consulta.getHorario().getHoraFormatada() + " " + GeralUtils.retornaDiaSemana(consulta.getHorario().getDiaDaSemanaFormatado())));
            firebasePush.sendToToken(consulta.getPaciente().getToken());
        }


        /*
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("firebaseTokenId1");
        jsonArray.put("firebaseTokenId2");
        jsonArray.put("firebaseTokenId3");
        firebasePush.sendToGroup(jsonArray); */
    }



    public static void pushNotificationUsers(List<User> userList, String titulo, String mensagem) {
        FirebasePush firebasePush = new FirebasePush("AIzaSyA3FZ-4UQ_4fMuzH_eVwi92gvkj5dwKdic");
        firebasePush.setAsyncResponse(new PushNotificationTask.AsyncResponse() {
            @Override
            public void onFinishPush(@NotNull String ouput) {
                Log.e("OUTPUT", ouput);
            }
        });

        firebasePush.setNotification(new Notification(titulo, mensagem));

        for (User user : userList) {
            firebasePush.sendToToken(user.getToken());
        }


        /*
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("firebaseTokenId1");
        jsonArray.put("firebaseTokenId2");
        jsonArray.put("firebaseTokenId3");
        firebasePush.sendToGroup(jsonArray); */
    }


}
