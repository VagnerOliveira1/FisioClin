package com.rightside.fisioclin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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

import com.rightside.fisioclin.fragment.EscolherAlvoNotificacaoFragment;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import com.rightside.fisioclin.fragment.FichasMedicoFragment;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelConsultas;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;
import com.rightside.fisioclin.viewmodel.ViewModelMedicos;
import com.rightside.fisioclin.viewmodel.ViewModelUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainMedicoActivity extends FragmentActivity {

    private ImageView imageViewDoctorPicture;
    private CardView  cardViewHorarios,cardViewMinhasConsultasMedico, cardViewFichasMedico, cardViewPushNotification, cardViewLoja;
    private TextView textViewNameDoctor, textViewQuantidadeConsultasMarcadas, textViewConsultasFinalizadas;
    private Medico medico;
    private ViewModelConsultas viewModelConsultas;
    private ViewModelFichas viewModelFichas;
    private ViewModelMedicos viewModelMedico;
    private TextView textViewFisioPoints;
    private static final int REQUEST_PERMISSAO_ARQUIVOS = 1;
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
        textViewFisioPoints = findViewById(R.id.textViewFisioPoints);
        cardViewLoja = findViewById(R.id.card_view_loja);
        textViewConsultasFinalizadas = findViewById(R.id.textViewConsultasFInalizadas);
        textViewQuantidadeConsultasMarcadas = findViewById(R.id.textViewNumeroConsultas);

        verificaPermissaoArquivos();

        viewModelConsultas = ViewModelProviders.of(this).get(ViewModelConsultas.class);
        viewModelFichas = ViewModelProviders.of(this).get(ViewModelFichas.class);
        viewModelMedico = ViewModelProviders.of(this).get(ViewModelMedicos.class);


        Date date = new Date();
        String diaHoje = new SimpleDateFormat("EEE", new Locale("pt", "BR")).format(date).toLowerCase();


        viewModelMedico.getMedico().observe(this, medico1 -> {
            medico = medico1;
            alteraInformacaoPerfil(medico);
        });


        viewModelConsultas.getConsultas(FirebaseRepository.getIdPessoaLogada(), diaHoje).observe(this, consultaList -> {
            if(consultaList != null) {
                textViewQuantidadeConsultasMarcadas.setText(String.valueOf(consultaList.size()));
                this.consultaList = consultaList;
            } else {
                textViewQuantidadeConsultasMarcadas.setText("0");

            }
        });

        viewModelFichas.getFichas(FirebaseRepository.getIdPessoaLogada()).observe(this, fichaList -> {
            if(fichaList != null) {
                textViewConsultasFinalizadas.setText(String.valueOf(fichaList.size()));
            } else {
                textViewConsultasFinalizadas.setText("0");
            }
        });

        cardViewPushNotification.setOnClickListener(view -> {
            EscolherAlvoNotificacaoFragment.novaInstancia(consultaList, medico).setFragmentActivity(this).show(getSupportFragmentManager(), "notificacao");
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

        cardViewLoja.setOnClickListener(view -> {
            startActivity(new Intent(MainMedicoActivity.this, LojaActivity.class));
        });



    }


    private void alteraInformacaoPerfil(Medico medico) {
        textViewNameDoctor.setText(medico.getName());
        GeralUtils.mostraImagemCircular(this, imageViewDoctorPicture, medico.getProfilePictureUrl());
        textViewFisioPoints.setText("FisioPoints:" + medico.getFisioPoints().getPoints() + "\nNotificações: " + medico.getNotificacao() + " Relatórios: " + medico.getRelatorio());
    }

    public void verificaPermissaoArquivos() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MainMedicoActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSAO_ARQUIVOS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case REQUEST_PERMISSAO_ARQUIVOS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getBaseContext(), "Permissão para arquivos foi negada", Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
            }
        }

    }


}
