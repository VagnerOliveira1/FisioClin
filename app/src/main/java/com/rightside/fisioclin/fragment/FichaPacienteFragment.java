package com.rightside.fisioclin.fragment;

import android.app.Dialog;
import android.graphics.Color;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.adapter.FichaPacienteAdapter;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FichaPacienteFragment extends DialogFragment {

    private ViewModelFichas viewModelFichaPaciente;


    private FichaPacienteAdapter fichaPacienteAdapter;


    public static FichaPacienteFragment novaInstancia(){
        FichaPacienteFragment fichaPacienteFragment = new FichaPacienteFragment();
        return fichaPacienteFragment;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ficha_paciente,container, false);
        RecyclerView recyclerViewFicha = view.findViewById(R.id.recycler_view_ficha_paciente);
        Toolbar toolbar = view.findViewById(R.id.toolbar_principal);
        recyclerViewFicha.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFicha.setHasFixedSize(true);
        toolbar.setTitle("Minha Ficha:");
        toolbar.setTitleTextColor(Color.WHITE);
        viewModelFichaPaciente = ViewModelProviders.of(this).get(ViewModelFichas.class);

        fichaPacienteAdapter = new FichaPacienteAdapter(getContext());
        recyclerViewFicha.setAdapter(fichaPacienteAdapter);



        viewModelFichaPaciente.getFicha(FirebaseRepository.getIdPessoaLogada()).observe(this, fichaPaciente -> {
            fichaPacienteAdapter.update(fichaPaciente);
        });

        return view;

    }
    @Override
    public void onStart(){

        super.onStart();

        Dialog fichaPaciente = getDialog();
        if (fichaPaciente != null) {
            fichaPaciente.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

}
