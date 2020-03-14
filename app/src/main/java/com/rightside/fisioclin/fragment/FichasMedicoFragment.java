package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.adapter.FichaMedicoAdapter;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FichasMedicoFragment extends DialogFragment {

    private ViewModelFichas viewModelFichas;
    private FichaMedicoAdapter fichaMedicoAdapter;
    private Toolbar toolbar;
    private List<Ficha> fichaList = new ArrayList<>();




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
        recyclerViewFichas.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFichas.setHasFixedSize(true);
        Toolbar toolbar = view.findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Fichas");
        toolbar.setTitleTextColor(Color.WHITE);
        SearchView searchView = view.findViewById(R.id.searchMenu);
        searchView.setQueryHint("Buscar ficha");
        setHasOptionsMenu(true);
        viewModelFichas = ViewModelProviders.of(this).get(ViewModelFichas.class);

        fichaMedicoAdapter = new FichaMedicoAdapter(getContext());
        recyclerViewFichas.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerViewFichas.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerViewFichas.setAdapter(fichaMedicoAdapter);

        viewModelFichas.getFichas(FirebaseRepository.getIdPessoaLogada()).observe(this, fichaList -> {
            this.fichaList = fichaList;
            fichaMedicoAdapter.update(fichaList);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                findChannel(newText.toLowerCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                findChannel(newText.toLowerCase());
                return false;
            }
        });





        return view;
    }


    private void findChannel(String text) {
        if(fichaList.size() > 0 && !text.isEmpty()) {
            List<Ficha> fichalistsearch = new ArrayList<>();
            for(Ficha ficha : fichaList){
                if(ficha.contain(text)){
                    fichalistsearch.add(ficha);
                }
            }
            fichaMedicoAdapter.update(fichalistsearch);
        }else if(text.isEmpty()){
            //   findChannels();
                fichaMedicoAdapter.update(fichaList);
        }
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
