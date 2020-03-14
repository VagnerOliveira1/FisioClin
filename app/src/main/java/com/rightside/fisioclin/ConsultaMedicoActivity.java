package com.rightside.fisioclin;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.rightside.fisioclin.adapter.ConsultaMedicoAdapter;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.viewmodel.ViewModelConsultas;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;
import com.rightside.fisioclin.viewmodel.ViewModelHorarios;

import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicoActivity extends FragmentActivity {

    private List<Consulta>  consultas ;
    private ConsultaMedicoAdapter mAdapter;
    private ViewModelConsultas viewModelConsultas;
    private ViewModelHorarios viewModelHorarios;
    private Ficha ficha = new Ficha();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_medico);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_consultas_medico);
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        consultas = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new ConsultaMedicoAdapter(ConsultaMedicoActivity.this, ConsultaMedicoActivity.this);
        recyclerView.setAdapter(mAdapter);

        toolbar.setTitle("Minha agenda:");
        toolbar.setTitleTextColor(Color.WHITE);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new ConsultaMedicoAdapter( ConsultaMedicoActivity.this, ConsultaMedicoActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        viewModelConsultas = ViewModelProviders.of(this).get(ViewModelConsultas.class);



       viewModelConsultas.getConsultas(FirebaseRepository.getIdPessoaLogada()).observe(this, listaConsulta -> {
          this.consultas = listaConsulta;
           mAdapter.update(listaConsulta);
       });



    }



}
