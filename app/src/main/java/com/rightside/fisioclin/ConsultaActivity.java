package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rightside.fisioclin.adapter.ConsultaAdapter;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.viewmodel.ViewModelConsultas;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {

    private List<Consulta>  consultas ;
    private ConsultaAdapter mAdapter;

    private ViewModelConsultas viewModelConsultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        consultas = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new ConsultaAdapter(this);
        recyclerView.setAdapter(mAdapter);


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new ConsultaAdapter( ConsultaActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


//        viewModelConsultas = ViewModelProviders.of(this).get(ViewModelConsultas.class);
//
//
//
//        viewModelConsultas.getConsultas().observe(this, listaConsulta -> {
//            this.consultas = listaConsulta;
//            mAdapter.update(listaConsulta);
//        });
//
//











    }

}
