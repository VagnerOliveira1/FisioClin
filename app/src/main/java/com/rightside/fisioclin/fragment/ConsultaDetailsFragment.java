package com.rightside.fisioclin.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.utils.GeralUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultaDetailsFragment extends DialogFragment {

    private TextView textViewPacienteNome, textViewPacienteData, textViewPacienteTelefone, textViewPacienteEmail, textViewPacienteProfissao, textViewPacienteSexo, textViewQueixa;
    private TextView textViewCidade, textViewBairro, textViewRua, textViewNumero, textViewConsultaTIpo;
    private ImageView imageViewPacienteSexo, imageViewFotoPaciente;
    private LinearLayout linearLayout;
    public static ConsultaDetailsFragment consultaDetailsFragment(Paciente paciente, Horario horario) {
        ConsultaDetailsFragment consultaDetailsFragment = new ConsultaDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("paciente", paciente);
        bundle.putSerializable("horario", horario);
        consultaDetailsFragment.setArguments(bundle);
        return consultaDetailsFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_consulta_details, container, false);
        linearLayout = view.findViewById(R.id.linearDomiciliar);

        Bundle bundle = getArguments();
        Paciente paciente = (Paciente) bundle.getSerializable("paciente");
        Horario horario = (Horario) bundle.get("horario");

        textViewPacienteNome = view.findViewById(R.id.textView_detalhe_nome);
        textViewPacienteData = view.findViewById(R.id.textView_detalhes_data);
        textViewPacienteEmail = view.findViewById(R.id.textView_detalhes_email);
        imageViewPacienteSexo = view.findViewById(R.id.imageView_detalhes_sexo);
        textViewCidade = view.findViewById(R.id.textViewCidade);
        textViewBairro = view.findViewById(R.id.textViewBairro);
        textViewRua = view.findViewById(R.id.textViewRua);
        textViewNumero = view.findViewById(R.id.textViewNumero);
        textViewPacienteProfissao = view.findViewById(R.id.textView_detalhes_profissao);
        textViewPacienteTelefone = view.findViewById(R.id.textView_detalhes_telefone);
        textViewPacienteSexo = view.findViewById(R.id.textView_sexo);
        imageViewFotoPaciente = view.findViewById(R.id.imageViewDetailsConsulta);
        textViewQueixa = view.findViewById(R.id.textView_queixa);
        textViewConsultaTIpo = view.findViewById(R.id.textView_consulta_details_tipo);

        textViewConsultaTIpo.setText(GeralUtils.domiciliar(horario.isDomiciliar()));

        if(paciente.getSexo().equalsIgnoreCase("feminino")) {
            imageViewPacienteSexo.setImageResource(R.drawable.ic_fem);
        } else {
            imageViewPacienteSexo.setImageResource(R.drawable.ic_male);
        }

        if(horario.isDomiciliar()) {
            linearLayout.setVisibility(View.VISIBLE);
            textViewNumero.setText("Número: " + paciente.getEndereco().getNumero());
            textViewBairro.setText("Bairro: " + paciente.getEndereco().getBairro());
            textViewCidade.setText("Cidade: " + paciente.getEndereco().getCidade());
            textViewRua.setText("Rua: " + paciente.getEndereco().getLogradouro());
        } else {
            linearLayout.setVisibility(View.GONE);
        }

        textViewPacienteSexo.setText(paciente.getSexo());
        textViewPacienteNome.setText(paciente.getName());
        textViewPacienteData.setText(paciente.getDataNascimento());
        textViewPacienteTelefone.setText(paciente.getPhoneNumber());
        textViewPacienteEmail.setText(paciente.getEmail());
        textViewPacienteProfissao.setText(paciente.getProfissao());
        textViewQueixa.setText(paciente.getDiagnosticoMedico().getQueixa());


        GeralUtils.mostraImagemCircular(getContext(), imageViewFotoPaciente, paciente.getProfilePictureUrl());

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog detalhes = getDialog();
        if ( detalhes  != null) {
            detalhes .getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
