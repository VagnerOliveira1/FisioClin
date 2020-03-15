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
import com.rightside.fisioclin.models.Clinica;
import com.rightside.fisioclin.models.Endereco;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Pontuacao;
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
    private TextInputEditText textInputEditTextCrefitoMedico, textInputEditTextNomeExibicaoMedico, textInputEditTextCep, textInputEditTextBairro, textInputEditTextRua, textInputEditTextNomeClinica, textInputEditTextNumero;
    private Button buttonSalvarMedico, buttonBuscarCidade;
    private TextView textViewCidadeResultado;
    private Clinica clinica = new Clinica();
    private Endereco endereco = new Endereco();

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
        textInputEditTextBairro = view.findViewById(R.id.edit_text_bairro_medico);
        textInputEditTextNomeClinica = view.findViewById(R.id.edit_text_nome_clinica);
        textInputEditTextRua = view.findViewById(R.id.edit_text_rua_medico);
        textInputEditTextNumero = view.findViewById(R.id.edit_text_numero_medico);
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
                                textInputEditTextBairro.setText(response.body().getBairro());
                                textInputEditTextRua.setText(response.body().getLogradouro());

                                endereco = response.body();

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
            public void onClick(View view) { ;
                if(GeralUtils.isCampoVazio(textInputEditTextCrefitoMedico.getText().toString())) {
                    textInputEditTextCrefitoMedico.requestFocus();
                    Toast.makeText(getActivity(), "Informe o crefito de registro", Toast.LENGTH_SHORT).show();
                }else if (GeralUtils.isCampoVazio(maskEditTextTelefoneMedico.getText().toString())) {
                    maskEditTextTelefoneMedico.requestFocus();
                    Toast.makeText(getActivity(), "Informe o telefone para contato", Toast.LENGTH_SHORT).show();
                } else if(textViewCidadeResultado.getText().toString().equalsIgnoreCase("")) {
                    textInputEditTextCep.requestFocus();
                    Toast.makeText(getActivity(), "Informe o cep para buscar a cidade:", Toast.LENGTH_SHORT).show();
                }else if(GeralUtils.isCampoVazio(textInputEditTextNomeClinica.getText().toString())) {
                    textInputEditTextNomeClinica.requestFocus();
                    Toast.makeText(getActivity(), "Informe o nome do local em que atende", Toast.LENGTH_SHORT).show();
                } else if (GeralUtils.isCampoVazio(textInputEditTextRua.getText().toString())) {
                    textInputEditTextRua.requestFocus();
                    Toast.makeText(getActivity(), "Informe a rua", Toast.LENGTH_SHORT).show();
                }
                else if (GeralUtils.isCampoVazio(textInputEditTextBairro.getText().toString())) {
                    textInputEditTextBairro.requestFocus();
                    Toast.makeText(getActivity(), "Informe o bairro", Toast.LENGTH_SHORT).show();
                }  else if (GeralUtils.isCampoVazio(textInputEditTextNumero.getText().toString())) {
                    textInputEditTextNumero.requestFocus();
                    Toast.makeText(getActivity(), "Informe o número", Toast.LENGTH_SHORT).show();
                } else{
                    endereco.setBairro(textInputEditTextBairro.getText().toString());
                    endereco.setCidade(textViewCidadeResultado.getText().toString());
                    endereco.setLogradouro(textInputEditTextRua.getText().toString());
                    endereco.setNumero(textInputEditTextNumero.getText().toString());
                    clinica.setNome(textInputEditTextNomeClinica.getText().toString());
                    clinica.setEndereco(endereco);
                    medico.setClinica(clinica);
                    medico.setEndereco(endereco);
                    medico.setName(textInputEditTextNomeExibicaoMedico.getText().toString());
                    medico.setCrefito(textInputEditTextCrefitoMedico.getText().toString());
                    medico.setPhoneNumber(maskEditTextTelefoneMedico.getText().toString());
                    medico.setPontuacao(new Pontuacao());


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
