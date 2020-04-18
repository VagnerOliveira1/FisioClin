package com.rightside.fisioclin.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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
import com.rightside.fisioclin.MainPacientActivity;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Endereco;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.ws.SetupREST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnderecoVerificationFragment extends DialogFragment {
    private TextInputEditText editTextCidade, editTextNumero, editTextBairro, editTextRua, editTextCep;
    private TextView textViewConsultaTitulo;
    private Button buttonBuscar, buttonSalvar;
    private Endereco endereco = new Endereco();
    private FragmentActivity fragmentActivity;

    public static EnderecoVerificationFragment enderecoVerificationFragment(User user) {
        EnderecoVerificationFragment enderecoVerificationFragment = new EnderecoVerificationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        enderecoVerificationFragment.setArguments(bundle);
        return enderecoVerificationFragment;
    }

    public EnderecoVerificationFragment fragmentActivity(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        return this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_endereco_verification, container, false);
        editTextBairro = view.findViewById(R.id.edit_text_bairro);
        textViewConsultaTitulo = view.findViewById(R.id.textView_consulta_texto);
        editTextCep = view.findViewById(R.id.edit_text_cep);
        buttonSalvar = view.findViewById(R.id.buttonSalvar);
        editTextCidade = view.findViewById(R.id.edit_text_cidade);
        buttonBuscar = view.findViewById(R.id.button4);
        editTextNumero = view.findViewById(R.id.edit_text_numero);
        editTextRua = view.findViewById(R.id.edit_text_rua);

        Bundle bundle = getArguments();
        User user = (User) bundle.get("user");

        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(editTextCep,simpleMaskFormatter);
        editTextCep.addTextChangedListener(maskTextWatcher);


        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cep = editTextCep.getText().toString().replace("-", "").trim();

                if(GeralUtils.isCampoVazio(cep)) {
                    editTextCep.requestFocus();
                    Toast.makeText(getActivity(), "Informe o cep", Toast.LENGTH_SHORT).show();
                } else {
                    int cepNumber =  Integer.valueOf(cep);
                try {

                    SetupREST.apiREST.endereco(cepNumber).enqueue(new Callback<Endereco>() {
                        @Override
                        public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                            if(response.isSuccessful()) {
                                editTextBairro.setText(response.body().getBairro());
                                editTextCidade.setText(response.body().getCidade());
                                editTextRua.setText(response.body().getLogradouro());
                                editTextNumero.setText("");
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
            }});

        buttonSalvar.setOnClickListener(click -> {
                if(GeralUtils.isCampoVazio(editTextCep.getText().toString())) {
                    editTextCep.requestFocus();
                    Toast.makeText(getActivity(), "Informe o Cep:", Toast.LENGTH_SHORT).show();
                } else if(GeralUtils.isCampoVazio(editTextBairro.getText().toString())) {
                    editTextBairro.requestFocus();
                    Toast.makeText(getActivity(), "Informe o bairro", Toast.LENGTH_SHORT).show();
                }else if (GeralUtils.isCampoVazio(editTextCidade.getText().toString())) {
                    editTextCidade.requestFocus();
                    Toast.makeText(getActivity(), "Informe a cidade", Toast.LENGTH_SHORT).show();
                } else if (GeralUtils.isCampoVazio(editTextRua.getText().toString())) {
                    editTextRua.requestFocus();
                    Toast.makeText(getActivity(), "Informe a rua", Toast.LENGTH_SHORT).show();
                } else if (GeralUtils.isCampoVazio(editTextNumero.getText().toString())){
                    editTextNumero.requestFocus();
                    Toast.makeText(getActivity(), "Informe o número", Toast.LENGTH_SHORT).show();
                } else {
                    endereco.setCidade(editTextCidade.getText().toString());
                    endereco.setBairro(editTextBairro.getText().toString());
                    endereco.setLogradouro(editTextRua.getText().toString());
                    endereco.setNumero(editTextNumero.getText().toString());
                    user.setEndereco(endereco);

                    FirebaseRepository.saveUser(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            startActivity(new Intent(getContext(), MainPacientActivity.class));
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
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}
