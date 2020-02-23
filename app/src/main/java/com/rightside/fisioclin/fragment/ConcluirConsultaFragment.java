package com.rightside.fisioclin.fragment;



import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.ConstantUtils;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConcluirConsultaFragment extends DialogFragment {

    private ViewModelFichas viewModelFichas;
    private Ficha ficha;
    private Button button;
    private TextInputEditText textInputEditTextConcluirConsulta;
    private FragmentActivity fragmentActivity;

    public static ConcluirConsultaFragment novaInstancia(Consulta consulta){
        ConcluirConsultaFragment concluirConsultaFragment = new ConcluirConsultaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("consulta", consulta);
        concluirConsultaFragment.setArguments(bundle);
        return concluirConsultaFragment;
        // Required empty public constructor
    }

    public ConcluirConsultaFragment getFragment(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        return this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_concluir_consulta, container, false);
        button = view.findViewById(R.id.button2);
        textInputEditTextConcluirConsulta = view.findViewById(R.id.edittext_concluirconsulta);
        Bundle bundle = getArguments();
        Consulta consulta = (Consulta) bundle.get("consulta");

        viewModelFichas = ViewModelProviders.of(this).get(ViewModelFichas.class);
        viewModelFichas.getFicha(consulta.getPaciente().getId()).observe(this, ficha -> {
           this.ficha = ficha;
        });



        button.setOnClickListener(view1 -> {

            if (this.ficha == null)  {
                this.ficha = new Ficha();
            }

            if (textInputEditTextConcluirConsulta.getText().toString().isEmpty()) {
                GeralUtils.mostraAlerta("Atenção!", ConstantUtils.IMPORTANTE_FAZER_COMENTARIO_SOBRE_CONSULTA, getContext());
            } else {
                consulta.setComentarioPosConsulta(textInputEditTextConcluirConsulta.getText().toString());
                ficha.getConsulta().add(consulta);
                ficha.setPaciente(consulta.getPaciente());
                FirebaseRepository.saveFicha(ficha);
                FirebaseRepository.deleteConsulta(consulta);
                FirebaseRepository.deleteHorarios(consulta.getHorario());
                dismiss();
                fragmentActivity.finish();

            }


        });



        return view;
    }

}
