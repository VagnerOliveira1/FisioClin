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
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.MainMedicoActivity;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Endereco;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.ws.SetupREST;
import com.santalu.maskedittext.MaskEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicoVerificationDataFragment extends DialogFragment {

    private MaskEditText maskEditTextTelefoneMedico;
    private TextInputEditText textInputEditTextCrefitoMedico, textInputEditTextNomeExibicaoMedico, textInputEditTextCep;
    private Button buttonSalvarMedico, buttonBuscarCidade;
    private TextView textViewCidadeResultado;

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
        textInputEditTextCep = view.findViewById(R.id.edit_text_cep_medico);
        buttonBuscarCidade = view.findViewById(R.id.button_buscar_cidade_medico);
        textViewCidadeResultado = view.findViewById(R.id.textView_medico_cidade_resultado);

        Bundle bundle = getArguments();
        Medico medico = (Medico) bundle.getSerializable("medico");

        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(textInputEditTextCep,simpleMaskFormatter);
        textInputEditTextCep.addTextChangedListener(maskTextWatcher);

        buttonBuscarCidade.setOnClickListener(click -> {
            String cep = textInputEditTextCep.getText().toString().replace("-", "").trim();
            if(GeralUtils.isCampoVazio(cep)) {
                Toast.makeText(getActivity(), "Informe o cep", Toast.LENGTH_SHORT).show();
            } else {
                int cepNumber =  Integer.valueOf(cep);
                try {

                    SetupREST.apiREST.endereco(cepNumber).enqueue(new Callback<Endereco>() {
                        @Override
                        public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                            if(response.isSuccessful()) {
                                textViewCidadeResultado.setText(response.body().getCidade());
                                medico.setEndereco(response.body());
                            } else {
                                GeralUtils.mostraAlerta("Oops!, tivemos um problema", "Não conseguimos encontrar o cep informado.", getActivity());
                            }
                        }

                        @Override
                        public void onFailure(Call<Endereco> call, Throwable t) {

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        textInputEditTextNomeExibicaoMedico.setText(medico.getName());


        buttonSalvarMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medico.setName(textInputEditTextNomeExibicaoMedico.getText().toString());
                medico.setCrefito(textInputEditTextCrefitoMedico.getText().toString());
                medico.setPhoneNumber(maskEditTextTelefoneMedico.getText().toString());

                if(GeralUtils.isCampoVazio(textInputEditTextCrefitoMedico.getText().toString())) {
                    textInputEditTextCrefitoMedico.requestFocus();
                    Toast.makeText(getActivity(), "Informe o crefito de registro", Toast.LENGTH_SHORT).show();
                }else if (GeralUtils.isCampoVazio(maskEditTextTelefoneMedico.getText().toString())) {
                    maskEditTextTelefoneMedico.requestFocus();
                    Toast.makeText(getActivity(), "Informe o telefone para contato", Toast.LENGTH_SHORT).show();
                } else if(textViewCidadeResultado.getText().toString().equalsIgnoreCase("")) {
                    textInputEditTextCep.requestFocus();
                    Toast.makeText(getActivity(), "Informe o cep para buscar a cidade:", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseRepository.saveDoctor(medico).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            startActivity(new Intent(getContext(), MainMedicoActivity.class));
                        }
                    });
                }

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
