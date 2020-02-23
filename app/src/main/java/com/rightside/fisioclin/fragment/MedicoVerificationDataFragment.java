package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.MainMedicoActivity;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.santalu.maskedittext.MaskEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicoVerificationDataFragment extends DialogFragment {

    private MaskEditText maskEditTextTelefoneMedico;
    private TextInputEditText textInputEditTextCrefitoMedico, textInputEditTextNomeExibicaoMedico;
    private Button buttonSalvarMedico;

    public static MedicoVerificationDataFragment medicoVerificationDataFragment(Medico medico) {
        MedicoVerificationDataFragment medicoVerificationDataFragment = new MedicoVerificationDataFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("medico", medico);
        medicoVerificationDataFragment.setArguments(bundle);
        return medicoVerificationDataFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medico_verification_data, container, false);
        buttonSalvarMedico = view.findViewById(R.id.btnSalvarMedico);
        textInputEditTextNomeExibicaoMedico = view.findViewById(R.id.editNomeExibicaoMedico);
        textInputEditTextCrefitoMedico = view.findViewById(R.id.editCrefitoMedico);
        maskEditTextTelefoneMedico = view.findViewById(R.id.maskTelefoneMedico);



        Bundle bundle = getArguments();
        Medico medico = (Medico) bundle.getSerializable("medico");
        textInputEditTextNomeExibicaoMedico.setText(medico.getName());


        buttonSalvarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medico.setName(textInputEditTextNomeExibicaoMedico.getText().toString());
                medico.setCrefito(textInputEditTextCrefitoMedico.getText().toString());
                medico.setPhoneNumber(maskEditTextTelefoneMedico.getText().toString());

                FirebaseRepository.saveDoctor(medico).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getContext(), MainMedicoActivity.class));
                    }
                });
            }
        });



        return view;
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
