package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.rightside.fisioclin.adapter.HorarioPacienteAdapter;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.viewmodel.ViewModelConsultaPaciente;
import com.rightside.fisioclin.viewmodel.ViewModelHorarios;

import java.util.ArrayList;
import java.util.List;

public class HorarioPacienteActivity extends AppCompatActivity {
        private List<Horario> list;
        private HorarioPacienteAdapter mAdapter;
        private ViewModelHorarios viewModelHorarios;
        private ViewModelConsultaPaciente viewModelConsultaPaciente;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_horario_paciente);
            RecyclerView recyclerView = findViewById(R.id.recyclerView_horarios_paciente);
            list = new ArrayList<>();

             Toolbar toolbar = findViewById(R.id.toolbar_principal);
             toolbar.setTitleTextColor(Color.WHITE);
             toolbar.setSubtitleTextColor(Color.WHITE);

             toolbar.setTitle("Horários:");
             toolbar.setSubtitle("Segunda-feira");


            Intent intent = getIntent();
            User usuario = (User) intent.getSerializableExtra("usuario");
            TabLayout tabLayout = findViewById(R.id.tabLayout_navigation_paciente);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mAdapter = new HorarioPacienteAdapter(this, HorarioPacienteActivity.this, usuario);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            viewModelHorarios = ViewModelProviders.of(this).get(ViewModelHorarios.class);
            viewModelConsultaPaciente = ViewModelProviders.of(this).get(ViewModelConsultaPaciente.class);

            tabLayout.addTab(tabLayout.newTab().setText("Seg"));
            tabLayout.addTab(tabLayout.newTab().setText("Ter"));
            tabLayout.addTab(tabLayout.newTab().setText("Qua"));
            tabLayout.addTab(tabLayout.newTab().setText("Qui"));
            tabLayout.addTab(tabLayout.newTab().setText("Sex"));

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if(tab.getPosition() == 0) {
                        observerHorarioDia("seg");
                        toolbar.setSubtitle("Segunda-feira");
                    } else if (tab.getPosition() == 1) {
                        observerHorarioDia("ter");
                        toolbar.setSubtitle("Terça-feira");
                    } else if(tab.getPosition() == 2) {
                        observerHorarioDia("qua");
                        toolbar.setSubtitle("Quarta-feira");
                    } else if(tab.getPosition() == 3) {
                        observerHorarioDia("qui");
                        toolbar.setSubtitle("Quinta-feira");
                    } else if(tab.getPosition() == 4) {
                        observerHorarioDia("sex");
                        toolbar.setSubtitle("Sexta-feira");
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
            mAdapter.update(list);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        observerHorarioDia("seg");
    }
}
