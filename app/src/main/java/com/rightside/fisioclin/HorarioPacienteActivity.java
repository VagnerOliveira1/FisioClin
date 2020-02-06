package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.rightside.fisioclin.adapter.HorarioPacienteAdapter;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.viewmodel.ViewModelConsultaPaciente;
import com.rightside.fisioclin.viewmodel.ViewModelHorarios;

import java.util.ArrayList;
import java.util.List;

public class HorarioPacienteActivity extends AppCompatActivity {
        private List<Horario> list;
        private HorarioPacienteAdapter mAdapter;
        private ViewModelHorarios viewModelHorarios;
        private ViewModelConsultaPaciente viewModelConsultaPaciente;
        private String diaSemana = "segunda-feira";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_horario_paciente);
            RecyclerView recyclerView = findViewById(R.id.recyclerView_consultas_medico);
            list = new ArrayList<>();

            Intent intent = getIntent();
            Paciente paciente = (Paciente) intent.getSerializableExtra("paciente");
            TabLayout tabLayout = findViewById(R.id.tabLayout_navigation_paciente);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mAdapter = new HorarioPacienteAdapter(this, HorarioPacienteActivity.this, paciente);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            viewModelHorarios = ViewModelProviders.of(this).get(ViewModelHorarios.class);
            viewModelConsultaPaciente = ViewModelProviders.of(this).get(ViewModelConsultaPaciente.class);

            tabLayout.addTab(tabLayout.newTab().setText("Segunda"));
            tabLayout.addTab(tabLayout.newTab().setText("Terça"));
            tabLayout.addTab(tabLayout.newTab().setText("Quarta"));
            tabLayout.addTab(tabLayout.newTab().setText("Quinta"));
            tabLayout.addTab(tabLayout.newTab().setText("Sexta"));

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if(tab.getPosition() == 0) {
                        observerHorarioDia("segunda-feira");
                    } else if (tab.getPosition() == 1) {
                        observerHorarioDia("terça-feira");

                    } else if(tab.getPosition() == 2) {
                        observerHorarioDia("quarta-feira");
                    } else if(tab.getPosition() == 3) {
                        observerHorarioDia("quinta-feira");
                    } else if(tab.getPosition() == 4) {
                        observerHorarioDia("sexta-feira");
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            viewModelConsultaPaciente.getConsulta().observe(this, consulta -> {
                mAdapter.setConsulta(consulta);
            });



        }

    public void observerHorarioDia(String dia) {
        viewModelHorarios.getHorarios(dia).observe(this, listaHorario -> {
            this.list = listaHorario;
            mAdapter.update(listaHorario);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        observerHorarioDia("segunda-feira");
    }
}
