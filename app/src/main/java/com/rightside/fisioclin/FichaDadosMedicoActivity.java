package com.rightside.fisioclin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.rightside.fisioclin.adapter.ConsultasRealizadasPacienteAdapter;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.List;

public class FichaDadosMedicoActivity extends AppCompatActivity {

    private TextView textViewPacienteNome, textViewPacienteSexo, textViewPacienteData, textViewPacienteTelefone,
    textViewPacienteProfissao, textViewPacienteEmail;
    private ImageView imageViewPacienteFoto;
    private Button buttonRelatorio;
    private ConsultasRealizadasPacienteAdapter consultasRealizadasPacienteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_dados_medico);

        Toolbar toolbar = findViewById(R.id.toolbar_principal);

        textViewPacienteNome = findViewById(R.id.textView_ficha_medico_nome_paciente);
        textViewPacienteSexo = findViewById(R.id.textView_ficha_medico_paciente_sexo);
        textViewPacienteData = findViewById(R.id.textView_ficha_medico_paciente_nascimento);
        textViewPacienteTelefone = findViewById(R.id.textView_ficha_medico_paciente_telefone);
        buttonRelatorio = findViewById(R.id.btn_gerar_relatorio);
        textViewPacienteProfissao = findViewById(R.id.textView_ficha_medico_paciente_profissao);
        textViewPacienteEmail = findViewById(R.id.textView_ficha_medico_paciente_email);
        imageViewPacienteFoto = findViewById(R.id.imageView_ficha_medico_paciente_foto);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_ficha_medico_consultas_realizadas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();

        Paciente paciente = (Paciente) intent.getSerializableExtra("paciente");
        List<Consulta> consultasList = (List<Consulta>) intent.getSerializableExtra("consultaList");
        Medico medico = (Medico) intent.getSerializableExtra("medico");

        buttonRelatorio.setOnClickListener(view -> {

            if(medico.getRelatorio() > 0) {
                medico.setRelatorio(medico.getRelatorio() - 1);
                FirebaseRepository.atualizaPontoMedico(medico).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            GeralUtils.gerarRelatorio(FichaDadosMedicoActivity.this,paciente, consultasList);
                        }
                    }
                });

            } else {
                GeralUtils.mostraAlerta("Falha ao salvar relatório.", "Você não possui créditos de relatório suficiente, compre na loja com FisioPoints.", FichaDadosMedicoActivity.this);
            }

        });

        textViewPacienteNome.setText(paciente.getName());
        textViewPacienteSexo.setText(paciente.getSexo());
        textViewPacienteData.setText(paciente.getDataNascimento());
        textViewPacienteTelefone.setText(paciente.getPhoneNumber());
        textViewPacienteProfissao.setText(paciente.getProfissao());
        textViewPacienteEmail.setText(paciente.getEmail());
        toolbar.setTitle("Detalhes da Ficha:");
        toolbar.setTitleTextColor(Color.WHITE);


        consultasRealizadasPacienteAdapter = new ConsultasRealizadasPacienteAdapter(this, consultasList);

        recyclerView.setAdapter(consultasRealizadasPacienteAdapter);



        GeralUtils.mostraImagemCircular(this, imageViewPacienteFoto, paciente.getProfilePictureUrl());


    }
}
