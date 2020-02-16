package com.rightside.fisioclin.fragment;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConcluirConsultaFragment extends DialogFragment {

    private ViewModelFichas viewModelFichas;
    private Ficha ficha;
    private Button button;
    public static ConcluirConsultaFragment novaInstancia(Consulta consulta){
        ConcluirConsultaFragment concluirConsultaFragment = new ConcluirConsultaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("consulta", consulta);
        concluirConsultaFragment.setArguments(bundle);
        return concluirConsultaFragment;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_concluir_consulta, container, false);
        button = view.findViewById(R.id.button2);
        Bundle bundle = getArguments();
        Consulta consulta = (Consulta) bundle.get("consulta");

        viewModelFichas = ViewModelProviders.of(this).get(ViewModelFichas.class);
        viewModelFichas.getFicha(consulta.getPaciente().getId()).observe(this, ficha -> {
           this.ficha = ficha;

        });

        button.setOnClickListener(view1 -> {

            if(ficha == null) {
                ficha = new Ficha();
            }
                consulta.setComentarioPosConsulta("teste");
                ficha.getConsulta().add(consulta);
                ficha.setPaciente(consulta.getPaciente());

            FirebaseRepository.saveFicha(ficha);
            FirebaseRepository.deleteConsulta(consulta);
            FirebaseRepository.deleteHorarios(consulta.getHorario());
        });



        return view;
    }

}
