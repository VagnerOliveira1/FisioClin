package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.rightside.fisioclin.fragment.ConcluirConsultaFragment;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ConcluirConsultaController {

    public static void alertaConcluirConsulta(FragmentActivity fragmentActivity, Consulta consulta) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(fragmentActivity);
        LinearLayout container = new LinearLayout(fragmentActivity.getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);

        alerta.setView(container);


            alerta.setTitle("Concluir consulta").setMessage("Deseja concluir a consulta do paciente?")
                    .setPositiveButton("Concluir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ConcluirConsultaFragment.novaInstancia(consulta).show(fragmentActivity.getSupportFragmentManager(), "concluir");

                        }}).setNegativeButton("Cancelar", null).show();
    }





}
