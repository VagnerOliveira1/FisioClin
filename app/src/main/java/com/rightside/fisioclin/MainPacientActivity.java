package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;

public class MainPacientActivity extends AppCompatActivity {

    private ImageView imageViewFotoPaciente;
    private TextView textViewNomePaciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pacient);
        imageViewFotoPaciente = findViewById(R.id.imageView_paciente_foto);
        textViewNomePaciente = findViewById(R.id.textview_paciente_nome);



        FirebaseRepository.getPaciente(FirebaseRepository.getIdPessoaLogada()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()) {
                    Paciente paciente = documentSnapshot.toObject(Paciente.class);
                    alteraInformacaoPerfil(paciente);
                }
            }
        });
    }



    private void alteraInformacaoPerfil(Paciente paciente) {
        textViewNomePaciente.setText(paciente.getName());
        GeralUtils.mostraImagemCircular(this, imageViewFotoPaciente, paciente.getProfilePictureUrl());
    }
}
