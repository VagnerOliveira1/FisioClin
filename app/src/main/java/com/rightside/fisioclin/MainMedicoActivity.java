package com.rightside.fisioclin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.rightside.fisioclin.fragment.FichasMedicoFragment;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelConsultas;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

public class MainMedicoActivity extends FragmentActivity {

    private ImageView imageViewDoctorPicture;
    private CardView  cardViewHorarios,cardViewMinhasConsultasMedico, cardViewFichasMedico;
    private TextView textViewNameDoctor, textViewQuantidadeConsultasMarcadas, textViewConsultasFinalizadas;
    private Medico medico;
    private ViewModelConsultas viewModelConsultas;
    private ViewModelFichas viewModelFichas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_medico);

        imageViewDoctorPicture = findViewById(R.id.imageView_doctor_picture);
        cardViewHorarios = findViewById(R.id.cardview_horarios);
        textViewNameDoctor = findViewById(R.id.textView_name_doctor);
        cardViewMinhasConsultasMedico = findViewById(R.id.card_view_minhas_consultas_medico);
        cardViewFichasMedico = findViewById(R.id.card_view_fichas_medico);
        textViewConsultasFinalizadas = findViewById(R.id.textViewConsultasFInalizadas);
        textViewQuantidadeConsultasMarcadas = findViewById(R.id.textViewNumeroConsultas);

        viewModelConsultas = ViewModelProviders.of(this).get(ViewModelConsultas.class);
        viewModelFichas = ViewModelProviders.of(this).get(ViewModelFichas.class);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(!task.isSuccessful()){
                    Log.w("falha", "getInstanceId failed", task.getException());
                    return;

                }

                String token = task.getResult().getToken();

                String msg = token;
                Log.d("token", msg);
                Toast.makeText(MainMedicoActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });


        FirebaseRepository.getMedico(FirebaseRepository.getIdPessoaLogada()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot != null && documentSnapshot.exists()) {
                     medico = documentSnapshot.toObject(Medico.class);
                    alteraInformacaoPerfil(medico);
                }
            }
        });

        viewModelConsultas.getConsultas().observe(this, consultaList -> {

            if(consultaList != null) {
                textViewQuantidadeConsultasMarcadas.setText(String.valueOf(consultaList.size()));
            } else {
                textViewQuantidadeConsultasMarcadas.setText("0");
            }

        });

        viewModelFichas.getFichas().observe(this, fichaList -> {
            if(fichaList != null) {
                textViewConsultasFinalizadas.setText(String.valueOf(fichaList.size()));
            } else {
                textViewConsultasFinalizadas.setText("0");
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