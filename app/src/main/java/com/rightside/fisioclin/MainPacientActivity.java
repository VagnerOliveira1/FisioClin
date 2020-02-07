package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelConsultaPaciente;
import com.rightside.fisioclin.views.ConsultaPacienteActivity;

public class MainPacientActivity extends AppCompatActivity {

    private ImageView imageViewFotoPaciente;
    private TextView textViewNomePaciente;
    private CardView cardViewNovaConsulta;
    private CardView cardViewMinhasConsultas;
    private Paciente paciente;
    private Consulta consulta;
    private ViewModelConsultaPaciente viewModelConsultaPaciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pacient);
        imageViewFotoPaciente = findViewById(R.id.imageView_paciente_foto);
        textViewNomePaciente = findViewById(R.id.textview_paciente_nome);
        cardViewNovaConsulta = findViewById(R.id.card_view_paciente_horarios);
        cardViewMinhasConsultas = findViewById(R.id.card_view_paciente_consultas);

        viewModelConsultaPaciente = ViewModelProviders.of(this).get(ViewModelConsultaPaciente.class);
        viewModelConsultaPaciente.getConsulta().observe(this,consulta -> {
            this.consulta = consulta;
        });



        FirebaseRepository.getPaciente(FirebaseRepository.getIdPessoaLogada()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                     paciente = documentSnapshot.toObject(Paciente.class);
                     alteraInformacaoPerfil(paciente);
                }
            }
        });

        cardViewNovaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPacientActivity.this, HorarioPacienteActivity.class);
                intent.putExtra("paciente", paciente);
               startActivity(intent);

            }
        });

        cardViewMinhasConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(consulta != null) {
                        startActivity(new Intent(MainPacientActivity.this, ConsultaPacienteActivity.class));
                    } else {
                        GeralUtils.mostraAlerta("Você ainda não tem consulta", "marque uma consulta antes!", MainPacientActivity.this);
                    }

            }
        });
    }



    private void alteraInformacaoPerfil(Paciente paciente) {
        textViewNomePaciente.setText(paciente.getName());
        GeralUtils.mostraImagemCircular(this, imageViewFotoPaciente, paciente.getProfilePictureUrl());
    }


}
