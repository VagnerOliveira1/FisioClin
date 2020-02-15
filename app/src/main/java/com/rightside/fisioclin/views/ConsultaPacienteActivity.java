package com.rightside.fisioclin.views;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelConsultaPaciente;

public class ConsultaPacienteActivity extends FragmentActivity {

    private ViewModelConsultaPaciente viewModelConsultaPaciente;
    private TextView textViewNomePacienteConsulta, textViewTelefonePacienteConsulta, textViewDataPacienteConsulta,
    textViewDiaSemanaPacienteConsulta, textViewHoraPacienteConsulta;
    private ImageView imageViewFotoPacienteConsulta;
    private Consulta consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_paciente);

        textViewDataPacienteConsulta = findViewById(R.id.textview_data_consulta_paciente);
        textViewNomePacienteConsulta = findViewById(R.id.textview_nome_paciente_consulta);
        textViewDiaSemanaPacienteConsulta = findViewById(R.id.textview_dia_consulta_paciente);
        textViewTelefonePacienteConsulta = findViewById(R.id.textview_telefone_paciente_consulta);
        imageViewFotoPacienteConsulta = findViewById(R.id.imageView_foto_paciente_consulta);
        textViewHoraPacienteConsulta = findViewById(R.id.textview_hora_consulta_paciente);

        viewModelConsultaPaciente = ViewModelProviders.of(this).get(ViewModelConsultaPaciente.class);

        viewModelConsultaPaciente.getConsulta().observe(this, consulta -> {
            this.consulta = consulta;
            setText(consulta);
        });

    }

    private void setText(Consulta consulta) {

        if(consulta != null) {
        User paciente = consulta.getPaciente();
        Horario horario = consulta.getHorario();
        textViewNomePacienteConsulta.setText(paciente.getName());
        textViewHoraPacienteConsulta.setText(horario.getHoraFormatada());
        textViewTelefonePacienteConsulta.setText(paciente.getPhoneNumber());
        textViewDiaSemanaPacienteConsulta.setText(horario.getDiaDaSemanaFormatado());
        textViewDataPacienteConsulta.setText(horario.getDataFormatada());
        GeralUtils.mostraImagemCircular(this,imageViewFotoPacienteConsulta,paciente.getProfilePictureUrl());
        } else {

        }

    }


    @Override
    protected void onStart() {
        super.onStart();

    }
}
