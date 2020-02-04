package com.rightside.fisioclin;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rightside.fisioclin.adapter.ConsultaMedicoAdapter;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.viewmodel.ViewModelConsultas;

import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicoActivity extends FragmentActivity {

    private List<Consulta>  consultas ;
    private ConsultaMedicoAdapter mAdapter;
    private ViewModelConsultas viewModelConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_medico);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_consultas_medico);
        consultas = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new ConsultaMedicoAdapter(this);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new ConsultaMedicoAdapter( ConsultaMedicoActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        viewModelConsultas = ViewModelProviders.of(this).get(ViewModelConsultas.class);


       viewModelConsultas.getConsultas().observe(this, listaConsulta -> {
          this.consultas = listaConsulta;
           mAdapter.update(listaConsulta);
       });








    }

}
