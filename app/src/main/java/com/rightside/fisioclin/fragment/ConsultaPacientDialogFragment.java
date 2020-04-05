package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
            textViewDiaSemanaPacienteConsulta, textViewHoraPacienteConsulta, textViewCrefitoConsultaPaciente, textViewTelefoneMedicoConsultaPaciente, textViewDomiciliar, textViewEnderecoConsulta;
    private ImageView imageViewFotoPacienteConsulta;
    private LinearLayout linearLayoutEndereco;
    private Button buttonComoChegar;
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
        textViewDomiciliar = view.findViewById(R.id.textView_domiciliar_pacient_dialog);
        textViewDataPacienteConsulta = view.findViewById(R.id.textview_data_consulta_paciente);
        linearLayoutEndereco = view.findViewById(R.id.linear_endereco);
        textViewNomeMedicoFichaPaciente = view.findViewById(R.id.textView6);
        textViewEnderecoConsulta = view.findViewById(R.id.textView_endereco_consulta);
        textViewDiaSemanaPacienteConsulta = view.findViewById(R.id.textview_dia_consulta_paciente);
        imageViewFotoPacienteConsulta = view.findViewById(R.id.imageView_foto_paciente_consulta);
        buttonComoChegar = view.findViewById(R.id.button_como_chegar_paciente);
        textViewCrefitoConsultaPaciente = view.findViewById(R.id.textViewCrefitoConsultaPaciente);
        textViewTelefoneMedicoConsultaPaciente= view.findViewById(R.id.textVIewTelefoneConsultaPaciente);
        textViewHoraPacienteConsulta = view.findViewById(R.id.textview_hora_consulta_paciente);

        viewModelConsultaPaciente = ViewModelProviders.of(this).get(ViewModelConsultaPaciente.class);

        viewModelConsultaPaciente.getConsulta().observe(this, consulta -> {
            this.consulta = consulta;
            setText(consulta);
        });

        buttonComoChegar.setOnClickListener(click -> {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + consulta.getHorario().getMedico().getEndereco().getCidade() + "+" + consulta.getHorario().getMedico().getEndereco().getBairro());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
        return view;

    }

    private void setText(Consulta consulta) {

        if (consulta != null) {
            if(!consulta.getHorario().isDomiciliar()) {
                linearLayoutEndereco.setVisibility(View.VISIBLE);
            } else {
                linearLayoutEndereco.setVisibility(View.GONE);
            }

            Horario horario = consulta.getHorario();
            textViewNomeMedicoFichaPaciente.setText(horario.getMedico().getName());
            textViewHoraPacienteConsulta.setText(horario.getHoraFormatada());
            textViewCrefitoConsultaPaciente.setText("Crefito: " + horario.getMedico().getCrefito());
            textViewTelefoneMedicoConsultaPaciente.setText(horario.getMedico().getPhoneNumber());
            textViewDiaSemanaPacienteConsulta.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));
            textViewDataPacienteConsulta.setText(horario.getDataFormatada());
            textViewDomiciliar.setText(GeralUtils.domiciliar(horario.isDomiciliar()));
            GeralUtils.mostraImagemCircular(getContext(), imageViewFotoPacienteConsulta, horario.getMedico().getProfilePictureUrl());
            textViewEnderecoConsulta.setText("Cidade: " + horario.getMedico().getEndereco().getCidade() + "\n Bairro: " + horario.getMedico().getEndereco().getBairro() + "\n Rua: " + horario.getMedico().getEndereco().getLogradouro()
            + "\n Número: " + horario.getMedico().getEndereco().getLogradouro());
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
