package com.rightside.fisioclin.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rightside.fisioclin.ConsultaActivity;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.adapter.ConsultaMedicoAdapter;
import com.rightside.fisioclin.adapter.ConsultaPacienteAdapter;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.viewmodel.ViewModelConsultas;
import com.rightside.fisioclin.viewmodel.ViewModelConsultasPaciente;

import java.util.ArrayList;
import java.util.List;

public class ConsultaPacienteActivity extends AppCompatActivity {

    private List<Consulta> consultas ;
    private ConsultaPacienteAdapter mAdapter;
    private ViewModelConsultasPaciente viewModelConsultasPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_paciente);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        consultas = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new ConsultaPacienteAdapter(this);
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new ConsultaPacienteAdapter( ConsultaPacienteActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        viewModelConsultasPaciente.getConsultaPacienteLogado().observe(this, listaConsulta -> {
            this.consultas = listaConsulta;
            mAdapter.update(listaConsulta);
        });


    }
}
