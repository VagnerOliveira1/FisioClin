package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.adapter.FichaMedicoAdapter;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

/**
 * A simple {@link Fragment} subclass.
 */
public class FichasMedicoFragment extends DialogFragment {

    private ViewModelFichas viewModelFichas;
    private FichaMedicoAdapter fichaMedicoAdapter;



    public static FichasMedicoFragment novaInstancia(){
        FichasMedicoFragment fichasMedicoFragment = new FichasMedicoFragment();
        return fichasMedicoFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fichas_medico, container, false);
        RecyclerView recyclerViewFichas = view.findViewById(R.id.recycler_view_fichas_medico);
        Toolbar toolbar = view.findViewById(R.id.toolbar_principal);
        recyclerViewFichas.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFichas.setHasFixedSize(true);
        toolbar.setTitle("Fichas:");
        toolbar.setTitleTextColor(Color.WHITE);
        viewModelFichas = ViewModelProviders.of(this).get(ViewModelFichas.class);

        fichaMedicoAdapter = new FichaMedicoAdapter(getContext());


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewFichas.getContext(),
                layoutManager.getOrientation());

        recyclerViewFichas.addItemDecoration(dividerItemDecoration);;
        recyclerViewFichas.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFichas.setHasFixedSize(true);

        recyclerViewFichas.setAdapter(fichaMedicoAdapter);


        viewModelFichas.getFichas(FirebaseRepository.getIdPessoaLogada()).observe(this, fichaList -> {
            fichaMedicoAdapter.update(fichaList);
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog fichasMedico = getDialog();
        if (fichasMedico != null) {
            fichasMedico.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}
