package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.MainPacientActivity;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.santalu.maskedittext.MaskEditText;

import org.jetbrains.annotations.NotNull;

import io.ghyeok.stickyswitch.widget.StickySwitch;

/**
 * A simple {@link Fragment} subclass.
 */
public class PacientVerificationDataFragment extends DialogFragment implements NumberPicker.OnValueChangeListener {

    private MaskEditText maskEditTextTelefone;
    private MaskEditText maskEditTextDataNasc;
    private TextInputEditText editTextNomePaciente;
    private TextInputEditText editTextProfissaoPaciente;
    private TextInputEditText editTextQueixaPrincipal;
    private TextInputEditText editTextDiagnosticoMedico;
    private StickySwitch stickySwitchSexoPaciente;
    private Button buttonSalvaFichaPaciente;
    private int sessoes = 1;
    private TextView mostraNumeroSessoes;
    private String sexo = "";


    public static PacientVerificationDataFragment pacientVerificationDataFragment(Paciente paciente) {
        PacientVerificationDataFragment pacientVerificationDataFragment = new PacientVerificationDataFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("paciente", paciente);
        pacientVerificationDataFragment.setArguments(bundle);
        return pacientVerificationDataFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pacient_verification_data, container, false);

        maskEditTextTelefone = view.findViewById(R.id.editTelefonePaciente);
        maskEditTextDataNasc = view.findViewById(R.id.editDataNascPaciente);
        editTextNomePaciente = view.findViewById(R.id.editNomePaciente);
        editTextProfissaoPaciente = view.findViewById(R.id.editProfissaoPaciente);
        editTextQueixaPrincipal = view.findViewById(R.id.editQueixaPaciente);
        editTextDiagnosticoMedico = view.findViewById(R.id.editDiagnosticoMedico);
        stickySwitchSexoPaciente = view.findViewById(R.id.sticky_switch);
        buttonSalvaFichaPaciente = view.findViewById(R.id.btnSalvaFichaPaciente);
        mostraNumeroSessoes = view.findViewById(R.id.mostraNumeroSessoes);
        NumberPicker numberPicker = view.findViewById(R.id.numberPicker);
        editTextProfissaoPaciente.requestFocus();

        Bundle bundle = getArguments();
        Paciente paciente = (Paciente) bundle.get("paciente");
        paciente.setSessoes("0");;
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(30);
        numberPicker.setOnValueChangedListener(this);

        stickySwitchSexoPaciente.setSwitchColor(Color.GREEN);
        stickySwitchSexoPaciente.setSliderBackgroundColor(Color.GRAY);
        stickySwitchSexoPaciente.setDirection(StickySwitch.Direction.RIGHT);
        stickySwitchSexoPaciente.setActivated(true);



        editTextNomePaciente.setText(paciente.getName());
        maskEditTextTelefone.setText(paciente.getPhoneNumber());

        stickySwitchSexoPaciente.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                Toast.makeText(getContext(), "Selecionado "+ text,Toast.LENGTH_LONG).show();
                sexo = text;
            }
        });


        buttonSalvaFichaPaciente.setOnClickListener((v) -> {

            paciente.setName(editTextNomePaciente.getText().toString());
            paciente.setPhoneNumber(maskEditTextTelefone.getText().toString());
            paciente.setSexo(verificaSexo(sexo));
            paciente.setDataNascimento(maskEditTextDataNasc.getText().toString());
            paciente.setDescricaoMedica(editTextDiagnosticoMedico.getText().toString());
            paciente.setQueixa(editTextQueixaPrincipal.getText().toString());
            paciente.setSessoes(String.valueOf(sessoes));
            paciente.setProfissao(editTextProfissaoPaciente.getText().toString());


            FirebaseRepository.savePacient(paciente).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    startActivity(new Intent(getContext(), MainPacientActivity.class));
                }
            });
        });

        return view;

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
       sessoes = newVal;
       mostraNumeroSessoes.setText("Número de sessões: " + newVal);

    }


    private String verificaSexo(String sexo) {
         return sexo.equalsIgnoreCase("m") ? "Masculino" : "Feminino";
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
