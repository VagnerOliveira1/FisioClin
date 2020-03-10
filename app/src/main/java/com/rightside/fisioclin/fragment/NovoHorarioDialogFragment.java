package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
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
import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Endereco;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.ws.SetupREST;

import org.jetbrains.annotations.NotNull;

import io.ghyeok.stickyswitch.widget.StickySwitch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NovoHorarioDialogFragment extends DialogFragment {

    private TextView textViewData, textViewHora, textViewDiaSemana, textViewCidadeResultado;
    private Button buttonSalvarHorario, buttonBuscarCidade;
    private TextInputEditText textInputEditTextCep;
    private StickySwitch stickySwitchDomiciliar;
    private boolean domiciliar = false;


    public static NovoHorarioDialogFragment novoHorarioDialogFragment(Horario horario) {
        NovoHorarioDialogFragment novoHorarioDialogFragment = new NovoHorarioDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("horario", horario);
        novoHorarioDialogFragment.setArguments(bundle);
        return novoHorarioDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_novo_horario_dialog, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_principal);

        toolbar.setTitle("Confirme os dados do novo horário: ");
        toolbar.setTitleTextColor(Color.WHITE);
        Bundle bundle = getArguments();
        Horario horario = (Horario) bundle.get("horario");

        textViewData = view.findViewById(R.id.textView_data);
        textViewCidadeResultado = view.findViewById(R.id.textView_cidade_result);
        textViewDiaSemana = view.findViewById(R.id.textView_dia_semana);
        textViewHora = view.findViewById(R.id.textView_horario);
        buttonSalvarHorario = view.findViewById(R.id.button);
        buttonBuscarCidade = view.findViewById(R.id.button_buscar_cidade);
        textInputEditTextCep = view.findViewById(R.id.edit_text_cep_horario);
        stickySwitchDomiciliar = view.findViewById(R.id.sticky_switch_domiciliar);
        stickySwitchDomiciliar.setSwitchColor(Color.GREEN);
        stickySwitchDomiciliar.setSliderBackgroundColor(Color.LTGRAY);
        stickySwitchDomiciliar.setDirection(StickySwitch.Direction.RIGHT);
        stickySwitchDomiciliar.setActivated(true);


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
                                horario.setEndereco(response.body());
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


        stickySwitchDomiciliar.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String s) {
                if(s.equals("Sim")) {
                    domiciliar = true;
                } else {
                    domiciliar = false;
                }

            }
        });


        textViewData.setText(horario.getDataFormatada());
        textViewHora.setText(horario.getHoraFormatada());
        textViewDiaSemana.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));
        textViewCidadeResultado.setText(horario.getMedico().getEndereco().getCidade());

        buttonSalvarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horario.setDomiciliar(domiciliar);
                if(textViewCidadeResultado.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "É necessário informar a cidade!", Toast.LENGTH_SHORT).show();
                    textInputEditTextCep.requestFocus();
                } else {
                    FirebaseRepository.saveHour(horario);
                    dismiss();
                }

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog novoHorarioDialog = getDialog();
        if (novoHorarioDialog != null) {
            novoHorarioDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
