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

        for(int i = 0; i < consultaList.size(); i++){
            firebasePush.setNotification(new Notification("Olá, " + consultaList.get(i).getPaciente().getName() + " temos um lembrete:",
                    "Você possui uma consulta: " + consultaList.get(i).getHorario().getDataFormatada() + " " + consultaList.get(i).getHorario().getHoraFormatada() + " " + GeralUtils.retornaDiaSemana(consultaList.get(i).getHorario().getDiaDaSemanaFormatado())));
            firebasePush.sendToToken(consultaList.get(i).getPaciente().getToken());
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

        for(int i = 0; i < userList.size(); i++){
            firebasePush.sendToToken(userList.get(i).getToken());
        }


        /*
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("firebaseTokenId1");
        jsonArray.put("firebaseTokenId2");
        jsonArray.put("firebaseTokenId3");
        firebasePush.sendToGroup(jsonArray); */
    }


}
