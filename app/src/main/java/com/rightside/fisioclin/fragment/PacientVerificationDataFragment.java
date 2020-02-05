package com.rightside.fisioclin.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.MainPacientActivity;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.views.FichaPacienteActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PacientVerificationDataFragment extends DialogFragment {

    private TextInputEditText textInputEditTextPacientPhone;
    private TextView pacientName, pacientEmail;
    private Button buttonConfirmarPacient;


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

        textInputEditTextPacientPhone = view.findViewById(R.id.edittext_phone_pacient_verifica);
        pacientEmail = view.findViewById(R.id.textview_paciente_email_verifica);
        pacientName = view.findViewById(R.id.textview_paciente_nome_verifica);
        buttonConfirmarPacient = view.findViewById(R.id.button_confirmar);
        Bundle bundle = getArguments();
        Paciente paciente = (Paciente) bundle.get("paciente");
        pacientName.setText(paciente.getName());
        pacientEmail.setText(paciente.getEmail());
        textInputEditTextPacientPhone.setText(paciente.getPhoneNumber());

        buttonConfirmarPacient.setOnClickListener((v) -> {

            String number = textInputEditTextPacientPhone.getText().toString();
            paciente.setPhoneNumber(number);

            FirebaseRepository.savePacient(paciente).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                   // startActivity(new Intent(getContext(), MainPacientActivity.class));
                    startActivity(new Intent(getContext(), FichaPacienteActivity.class));
                }
            });
        });

        return view;

    }

}
