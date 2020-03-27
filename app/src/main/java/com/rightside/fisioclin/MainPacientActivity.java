package com.rightside.fisioclin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.rightside.fisioclin.fragment.ConsultaPacientDialogFragment;
import com.rightside.fisioclin.fragment.FichaPacienteFragment;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.ConstantUtils;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelConsultaPaciente;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;
import com.rightside.fisioclin.viewmodel.ViewModelUser;


public class MainPacientActivity extends AppCompatActivity {

    private ImageView imageViewFotoPaciente;
    private TextView textViewNomePaciente;
    private CardView cardViewNovaConsulta;
    private CardView cardViewMinhasConsultas;
    private CardView cardViewMinhaFicha;
    private Consulta consulta;
    private ViewModelConsultaPaciente viewModelConsultaPaciente;
    private ViewModelUser viewModelUser;
    private ViewModelFichas viewModelFichaPaciente;
    private User usuario;
    private Ficha ficha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pacient);
        imageViewFotoPaciente = findViewById(R.id.imageView_paciente_foto);
        textViewNomePaciente = findViewById(R.id.textview_paciente_nome);
        cardViewNovaConsulta = findViewById(R.id.card_view_paciente_horarios);
        cardViewMinhasConsultas = findViewById(R.id.card_view_paciente_consultas);
        cardViewMinhaFicha = findViewById(R.id.card_view_minha_ficha);


        viewModelConsultaPaciente = ViewModelProviders.of(this).get(ViewModelConsultaPaciente.class);
        viewModelUser = ViewModelProviders.of(this).get(ViewModelUser.class);
        viewModelFichaPaciente = ViewModelProviders.of(this).get(ViewModelFichas.class);

        viewModelUser.getUser(FirebaseRepository.getIdPessoaLogada()).observe(this, usuario -> {
            alteraInformacaoPerfil(usuario);
            setToken(usuario);
            this.usuario = usuario;
        });

        viewModelConsultaPaciente.getConsulta().observe(this, consulta -> {
            this.consulta = consulta;
        });

        viewModelFichaPaciente.getFicha(FirebaseRepository.getIdPessoaLogada()).observe(this, ficha -> {
            this.ficha = ficha;
        });





        cardViewNovaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPacientActivity.this, ListaMedicosActivity.class);
                intent.putExtra("usuario", usuario);
               startActivity(intent);

            }
        });

        cardViewMinhasConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (consulta != null) {
                    ConsultaPacientDialogFragment.novaInstancia().show(getSupportFragmentManager(), "Minha Consulta");

                } else {
                    GeralUtils.mostraAlerta("Você ainda não tem consulta", ConstantUtils.MARQUE_UMA_CONSULTA_ANTES, MainPacientActivity.this);
                }

            }
        });


        cardViewMinhaFicha.setOnClickListener(view -> {
            if(ficha == null) {
                GeralUtils.mostraAlerta("Oops", "Você ainda não possui uma ficha!", this);
            } else {
                FichaPacienteFragment.novaInstancia(ficha).setActivity(this, this).show(getSupportFragmentManager(), "Minha Ficha");
            }

        });

    }


    private void alteraInformacaoPerfil(User paciente) {
        textViewNomePaciente.setText(paciente.getName());
        GeralUtils.mostraImagemCircular(this, imageViewFotoPaciente, paciente.getProfilePictureUrl());
    }


    private void setToken(User usuario) {

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()){
                    Log.w("falha", "getInstanceId failed", task.getException());
                    return;
                }
                String token = task.getResult().getToken();
               usuario.setToken(token);
               FirebaseRepository.getUser(FirebaseRepository.getIdPessoaLogada()).update(usuario.returnUser());
            }
        });

    }



}






