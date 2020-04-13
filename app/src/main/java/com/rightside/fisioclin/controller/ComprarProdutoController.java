package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.rightside.fisioclin.models.FisioPoints;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Produto;
import com.rightside.fisioclin.repository.FirebaseRepository;

public class ComprarProdutoController {

    public static void alertaComprarProduto(FragmentActivity fragmentActivity, Medico medico, Produto produto) {
        final androidx.appcompat.app.AlertDialog.Builder alerta = new androidx.appcompat.app.AlertDialog.Builder(fragmentActivity);
        int quantiaRestante = medico.getFisioPoints().getPoints() - produto.getPreco();
        TextView valor = new TextView(fragmentActivity);
        TextView possui = new TextView(fragmentActivity);
        possui.setText(produto.getNome() + " " + produto.getPreco() + " Fps");
        possui.setTextColor(Color.BLACK);
        possui.setTextSize(18);
        possui.setGravity(Gravity.CENTER);
        valor.setTextColor(Color.BLACK);
        valor.setTextSize(16);
        valor.setText(medico.getFisioPoints().getPoints() +" - " + produto.getPreco() + " sobrará " + quantiaRestante + " Fps");
        LinearLayout container = new LinearLayout(fragmentActivity.getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);
        container.addView(possui);
        container.addView(valor);
        valor.setGravity(Gravity.CENTER);
        container.setGravity(Gravity.CENTER);
        alerta.setView(container);

        alerta.setTitle("Comprar Recurso:").setMessage("Tem certeza que deseja comprar esse item?").setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (produto.getTipo() == 1) {
                    medico.getFisioPoints().setPoints(medico.getFisioPoints().getPoints() - produto.getPreco());
                    medico.setNotificacao(medico.getNotificacao() + produto.getQuantidade());
                    FirebaseRepository.atualizaPontoMedico(medico);
                } else  {
                    medico.getFisioPoints().setPoints(medico.getFisioPoints().getPoints() - produto.getPreco());
                    medico.setRelatorio(medico.getRelatorio() + produto.getQuantidade());
                    FirebaseRepository.atualizaPontoMedico(medico);
                }

            }
        }).setNegativeButton("Cancelar", null).show();
    }
}
