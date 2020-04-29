package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.rightside.fisioclin.fragment.HourFragment;
import com.rightside.fisioclin.utils.ConstantUtils;

public class NovoHorarioController {

    public static void alertaDeNovoHorario(final FragmentActivity activity) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(activity);

        LinearLayout container = new LinearLayout(activity.getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(60, -20, 60, 0);

        alerta.setTitle("Novo Horário").setMessage(ConstantUtils.CADASTRAR_UM_NOVO_HORARIO).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HourFragment hourFragment = new HourFragment();
                hourFragment.setCancelable(false);
                hourFragment.show(activity.getSupportFragmentManager(), "teste");
            }
        }).setNegativeButton("Cancelar", null).show();
    }

}
