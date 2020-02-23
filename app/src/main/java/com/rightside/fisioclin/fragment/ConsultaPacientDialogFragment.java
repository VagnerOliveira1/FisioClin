package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelConsultaPaciente;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultaPacientDialogFragment extends DialogFragment {

    private ViewModelConsultaPaciente viewModelConsultaPaciente;
    private TextView textViewNomeMedicoFichaPaciente, textViewDataPacienteConsulta,
            textViewDiaSemanaPacienteConsulta, textViewHoraPacienteConsulta;
    private ImageView imageViewFotoPacienteConsulta;
    private Consulta consulta;





    public static ConsultaPacientDialogFragment novaInstancia(){
        ConsultaPacientDialogFragment consultaPacientDialogFragment = new ConsultaPacientDialogFragment();
        return consultaPacientDialogFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consulta_pacient_dialog, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_principal);

        toolbar.setTitle("Minha Consulta");
        toolbar.setTitleTextColor(Color.WHITE);

        textViewDataPacienteConsulta = view.findViewById(R.id.textview_data_consulta_paciente);
        textViewNomeMedicoFichaPaciente = view.findViewById(R.id.textView6);
        textViewDiaSemanaPacienteConsulta = view.findViewById(R.id.textview_dia_consulta_paciente);
        imageViewFotoPacienteConsulta = view.findViewById(R.id.imageView_foto_paciente_consulta);
        textViewHoraPacienteConsulta = view.findViewById(R.id.textview_hora_consulta_paciente);

        viewModelConsultaPaciente = ViewModelProviders.of(this).get(ViewModelConsultaPaciente.class);

        viewModelConsultaPaciente.getConsulta().observe(this, consulta -> {
            this.consulta = consulta;
            setText(consulta);
        });
        return view;

    }

    private void setText(Consulta consulta) {

        if (consulta != null) {
            User paciente = consulta.getPaciente();
            Horario horario = consulta.getHorario();
            textViewNomeMedicoFichaPaciente.setText(horario.getMedico().getName());
            textViewHoraPacienteConsulta.setText(horario.getHoraFormatada());
            textViewDiaSemanaPacienteConsulta.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));
            textViewDataPacienteConsulta.setText(horario.getDataFormatada());
            GeralUtils.mostraImagemCircular(getContext(), imageViewFotoPacienteConsulta, horario.getMedico().getProfilePictureUrl());
        } else {

        }

    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog consultaPacienteDialog = getDialog();
        if ( consultaPacienteDialog  != null) {
            consultaPacienteDialog .getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

}
