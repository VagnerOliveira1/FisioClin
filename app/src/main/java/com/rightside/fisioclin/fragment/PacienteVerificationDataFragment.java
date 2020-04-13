package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.DiagnosticoMedico;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.ConstantUtils;
import com.rightside.fisioclin.utils.GeralUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PacienteVerificationDataFragment extends DialogFragment implements NumberPicker.OnValueChangeListener {

    private String sessoes = "";
    private TextView textViewmostrarSessoes;
    private TextInputEditText textInputEditTextQueixaPaciente, textInputEditTextDiagnosticoMedico;
    private Button buttonSalvarConsulta;
    private DiagnosticoMedico diagnosticoMedico = new DiagnosticoMedico();
    private TextInputLayout textInputLayoutDiagnosticoMedico;
    private LinearLayout linearLayout;
    private Paciente paciente;
    private Switch aSwitch;


    public static PacienteVerificationDataFragment novaInstancia(Horario horario, User usuario) {
        PacienteVerificationDataFragment pacienteVerificationDataFragment = new PacienteVerificationDataFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("horario", horario);
        bundle.putSerializable("usuario", usuario);
        pacienteVerificationDataFragment.setArguments(bundle);
        return pacienteVerificationDataFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_paciente_verification_data, container, false);
        textViewmostrarSessoes = view.findViewById(R.id.mostraNumeroSessoes);
        textInputEditTextDiagnosticoMedico = view.findViewById(R.id.editDiagnosticoMedico);
        textInputEditTextQueixaPaciente = view.findViewById(R.id.editQueixaPaciente);
        linearLayout = view.findViewById(R.id.linearLayoutSessoes);
        textInputLayoutDiagnosticoMedico = view.findViewById(R.id.textInputLayoutDiagnosticoMedico);
        buttonSalvarConsulta = view.findViewById(R.id.button_salvar_consulta);
        NumberPicker numberPicker = view.findViewById(R.id.numberPicker);
        aSwitch = view.findViewById(R.id.switch2);
        Bundle bundle = getArguments();
        Horario horario = (Horario) bundle.get("horario");
        User usuario = (User) bundle.get("usuario");

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(30);
        numberPicker.setOnValueChangedListener(this);

        aSwitch.setTextOff("Não");
        aSwitch.setTextOn("Sim");

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    textInputLayoutDiagnosticoMedico.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    textInputLayoutDiagnosticoMedico.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });

        buttonSalvarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (GeralUtils.isCampoVazio(textInputEditTextQueixaPaciente.getText().toString())){
                    textInputEditTextQueixaPaciente.requestFocus();
                    Toast.makeText(getContext(), "Informe o que está sentindo", Toast.LENGTH_SHORT).show();
                } else  {
                    diagnosticoMedico.setQueixa(textInputEditTextQueixaPaciente.getText().toString());
                    diagnosticoMedico.setDescricaoMedica(textInputEditTextDiagnosticoMedico.getText().toString());
                    paciente = new Paciente(usuario, diagnosticoMedico, sessoes);
                    Consulta consulta = new Consulta(horario,paciente);
                    consulta.setAvaliada(false);
                    FirebaseRepository.saveConsultaUser(consulta).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                FirebaseRepository.saveConsultaMedico(consulta).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseRepository.atualizaHorarioMarcado(horario);
                                            FirebaseRepository.savePacient(paciente);
                                            GeralUtils.mostraAlerta("Sucesso!","Sua consulta foi marcada, se atente ao horário no menu minhas consultas.", getContext());
                                            dismiss();
                                        }
                                    }
                                });


                            }
                        }
                    });
                }





            }
        });


        return  view;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
        sessoes = String.valueOf(newVal);
        textViewmostrarSessoes.setText("Quantidade de sessões: " + newVal);
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
