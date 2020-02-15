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
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.santalu.maskedittext.MaskEditText;

import org.jetbrains.annotations.NotNull;

import io.ghyeok.stickyswitch.widget.StickySwitch;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserVerificationDataFragment extends DialogFragment {

    private MaskEditText maskEditTextTelefone;
    private MaskEditText maskEditTextDataNasc;
    private TextInputEditText editTextNomePaciente;
    private TextInputEditText editTextProfissaoPaciente;
    private StickySwitch stickySwitchSexoPaciente;
    private Button buttonSalvaFichaPaciente;
    private String sexo = "";


    public static UserVerificationDataFragment pacientVerificationDataFragment(User user) {
        UserVerificationDataFragment userVerificationDataFragment = new UserVerificationDataFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        userVerificationDataFragment.setArguments(bundle);
        return userVerificationDataFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_verification_data, container, false);

        maskEditTextTelefone = view.findViewById(R.id.editTelefonePaciente);
        maskEditTextDataNasc = view.findViewById(R.id.editDataNascPaciente);
        editTextNomePaciente = view.findViewById(R.id.editNomePaciente);
        editTextProfissaoPaciente = view.findViewById(R.id.editProfissaoPaciente);
        stickySwitchSexoPaciente = view.findViewById(R.id.sticky_switch);
        buttonSalvaFichaPaciente = view.findViewById(R.id.btnSalvaFichaPaciente);
        editTextProfissaoPaciente.requestFocus();

        Bundle bundle = getArguments();
        User user = (User) bundle.get("user");

        stickySwitchSexoPaciente.setSwitchColor(Color.GREEN);
        stickySwitchSexoPaciente.setSliderBackgroundColor(Color.GRAY);
        stickySwitchSexoPaciente.setDirection(StickySwitch.Direction.RIGHT);
        stickySwitchSexoPaciente.setActivated(true);



        editTextNomePaciente.setText(user.getName());
        maskEditTextTelefone.setText(user.getPhoneNumber());

        stickySwitchSexoPaciente.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                Toast.makeText(getContext(), "Selecionado "+ text,Toast.LENGTH_LONG).show();
                sexo = text;
            }
        });


        buttonSalvaFichaPaciente.setOnClickListener((v) -> {

            user.setName(editTextNomePaciente.getText().toString());
            user.setPhoneNumber(maskEditTextTelefone.getText().toString());
            user.setSexo(verificaSexo(sexo));
            user.setDataNascimento(maskEditTextDataNasc.getText().toString());
            user.setProfissao(editTextProfissaoPaciente.getText().toString());


            FirebaseRepository.saveUser(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    startActivity(new Intent(getContext(), MainPacientActivity.class));
                }
            });
        });

        return view;

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
