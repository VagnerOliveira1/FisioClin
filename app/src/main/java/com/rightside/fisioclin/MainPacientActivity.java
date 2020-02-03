package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;

public class MainPacientActivity extends AppCompatActivity {

    private ImageView imageViewFotoPaciente;
    private TextView textViewNomePaciente;
    private CardView cardViewNovaConsulta;
    private CardView cardViewMinhasConsultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pacient);
        imageViewFotoPaciente = findViewById(R.id.imageView_paciente_foto);
        textViewNomePaciente = findViewById(R.id.textview_paciente_nome);
        cardViewNovaConsulta = findViewById(R.id.card_view_paciente_horarios);
        cardViewMinhasConsultas = findViewById(R.id.card_view_paciente_consultas);



        FirebaseRepository.getPaciente(FirebaseRepository.getIdPessoaLogada()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    Paciente paciente = documentSnapshot.toObject(Paciente.class);
                    alteraInformacaoPerfil(paciente);
                }
            }
        });

        cardViewNovaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPacientActivity.this, HorarioPacienteActivity.class));

            }
        });

        cardViewMinhasConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPacientActivity.this, ConsultaActivity.class));
            }
        });
    }



    private void alteraInformacaoPerfil(Paciente paciente) {
        textViewNomePaciente.setText(paciente.getName());
        GeralUtils.mostraImagemCircular(this, imageViewFotoPaciente, paciente.getProfilePictureUrl());
    }
}
