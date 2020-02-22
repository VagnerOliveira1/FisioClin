package com.rightside.fisioclin;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.rightside.fisioclin.fragment.EscolherAlvoNotificacaoFragment;
import com.rightside.fisioclin.fragment.FichasMedicoFragment;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelConsultas;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;
import com.rightside.fisioclin.viewmodel.ViewModelUser;

import java.util.ArrayList;
import java.util.List;

public class MainMedicoActivity extends FragmentActivity {

    private ImageView imageViewDoctorPicture;
    private CardView  cardViewHorarios,cardViewMinhasConsultasMedico, cardViewFichasMedico, cardViewPushNotification;
    private TextView textViewNameDoctor, textViewQuantidadeConsultasMarcadas, textViewConsultasFinalizadas;
    private Medico medico;
    private ViewModelConsultas viewModelConsultas;
    private ViewModelFichas viewModelFichas;
    private List<Consulta> consultaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_medico);

        imageViewDoctorPicture = findViewById(R.id.imageView_doctor_picture);
        cardViewHorarios = findViewById(R.id.cardview_horarios);
        textViewNameDoctor = findViewById(R.id.textView_name_doctor);
        cardViewPushNotification = findViewById(R.id.card_view_push);
        cardViewMinhasConsultasMedico = findViewById(R.id.card_view_minhas_consultas_medico);
        cardViewFichasMedico = findViewById(R.id.card_view_fichas_medico);
        textViewConsultasFinalizadas = findViewById(R.id.textViewConsultasFInalizadas);
        textViewQuantidadeConsultasMarcadas = findViewById(R.id.textViewNumeroConsultas);

        viewModelConsultas = ViewModelProviders.of(this).get(ViewModelConsultas.class);
        viewModelFichas = ViewModelProviders.of(this).get(ViewModelFichas.class);

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
                this.consultaList = consultaList;
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

        cardViewPushNotification.setOnClickListener(view -> {
            EscolherAlvoNotificacaoFragment.novaInstancia(consultaList).setFragmentActivity(this).show(getSupportFragmentManager(), "notificacao");
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