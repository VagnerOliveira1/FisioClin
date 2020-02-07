package com.rightside.fisioclin.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.repository.FirebaseRepository;

import org.w3c.dom.Text;

import java.util.List;

public class ConcluirConsultaController {

    public static void alertaConcluirConsulta(FragmentActivity fragmentActivity, Consulta consulta) {
        final AlertDialog.Builder alerta = new AlertDialog.Builder(fragmentActivity);
        LinearLayout container = new LinearLayout(fragmentActivity.getApplicationContext());
        container.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(fragmentActivity);
        EditText editText = new EditText(fragmentActivity);

        container.addView(textView);
        container.addView(editText);
        alerta.setView(container);


        textView.setText("Faça uma descrição a respeito dessa consulta:");

        editText.setText("Ex: Nessa consulta fizemos exercicio tal tal e o paciente teve avanço no quadro");

            alerta.setTitle("Concluir consulta").setMessage("Deseja concluir a consulta do paciente?")
                    .setPositiveButton("Concluir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Ficha ficha = new Ficha(editText.getText().toString(), consulta, consulta.getPaciente());

                            FirebaseRepository.saveFicha(ficha).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        FirebaseRepository.deleteConsulta(consulta);
                                        FirebaseRepository.deleteHorarios(consulta.getHorario());

                                        Toast.makeText(fragmentActivity, "Consulta finalizada, agora o usuario pode marcar outra consulta", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }).setNegativeButton("Cancelar", null).show();
    }
}
