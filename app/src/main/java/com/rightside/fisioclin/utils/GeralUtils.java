package com.rightside.fisioclin.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.rightside.fisioclin.R;

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

    public static void mostraMensagem(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }


    public static String retornaDiaSemana(String dia) {
        switch (dia) {
            case "seg":
                return "Segunda-feira";
            case "ter":
                return "Terça-feira";
            case "qua":
                return "Quarta-feira";
            case "qui":
                return "Quinta-feira";
            case "sex":
                return "Sexta-feira";
            case "sab":
                return "Sábado";
        }
        return dia;

    }

}
