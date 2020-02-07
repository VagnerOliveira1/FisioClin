package com.rightside.fisioclin.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GeralUtils {

    public static void mostraImagemCircular(Context context, ImageView imageView, String url){
        Glide.with(context).load(url).circleCrop().into(imageView);
    }

    public static String capitalize(String palavra) {
        return palavra.substring(0,1).toUpperCase() + palavra.substring(1).toLowerCase();
    }

    public static void mostraAlerta(String titulo, String mensagem, Context context) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle(titulo).setMessage(mensagem).setNeutralButton("Ok", (dialog, which) -> dialog.cancel()).show();
    }
}
