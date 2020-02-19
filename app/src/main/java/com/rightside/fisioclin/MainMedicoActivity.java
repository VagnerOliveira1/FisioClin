package com.rightside.fisioclin;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.rightside.fisioclin.fragment.FichasMedicoFragment;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;

public class MainMedicoActivity extends FragmentActivity {

    private ImageView imageViewDoctorPicture;
    private CardView  cardViewHorarios,cardViewMinhasConsultasMedico, cardViewFichasMedico;
    private TextView textViewNameDoctor;
    private Medico medico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_medico);

        imageViewDoctorPicture = findViewById(R.id.imageView_doctor_picture);
        cardViewHorarios = findViewById(R.id.cardview_horarios);
        textViewNameDoctor = findViewById(R.id.textView_name_doctor);
        cardViewMinhasConsultasMedico = findViewById(R.id.card_view_minhas_consultas_medico);
        cardViewFichasMedico = findViewById(R.id.card_view_fichas_medico);

        FirebaseRepository.getMedico(FirebaseRepository.getIdPessoaLogada()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot != null && documentSnapshot.exists()) {
                     medico = documentSnapshot.toObject(Medico.class);
                    alteraInformacaoPerfil(medico);
                }
            }
        });



        cardViewHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMedicoActivity.this, HorarioMedicoActivity.class);
                intent.putExtra("medico", medico);
                startActivity(intent);
            }
        });

        cardViewMinhasConsultasMedico.setOnClickListener(view -> {
            startActivity(new Intent(MainMedicoActivity.this, ConsultaMedicoActivity.class));
        });

        cardViewFichasMedico.setOnClickListener(view -> {
            FichasMedicoFragment.novaInstancia().show(getSupportFragmentManager(), "fichas");
        });

    }


    private void alteraInformacaoPerfil(Medico medico) {
        textViewNameDoctor.setText(medico.getName());
        GeralUtils.mostraImagemCircular(this, imageViewDoctorPicture, medico.getProfilePictureUrl());
    }
}