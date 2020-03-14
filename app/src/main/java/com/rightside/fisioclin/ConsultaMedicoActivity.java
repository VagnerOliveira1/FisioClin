package com.rightside.fisioclin;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
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
    private Ficha ficha = new Ficha();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_medico);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_consultas_medico);
        toolbar = findViewById(R.id.toolbar_principal);
        TabLayout tabLayout = findViewById(R.id.tabLayout_navigation_agenda_medico);
        consultas = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new ConsultaMedicoAdapter(ConsultaMedicoActivity.this, ConsultaMedicoActivity.this);
        recyclerView.setAdapter(mAdapter);

        String id = FirebaseRepository.getIdPessoaLogada();

        toolbar.setTitle("Minha agenda:");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

        tabLayout.addTab(tabLayout.newTab().setText("Seg"));
        tabLayout.addTab(tabLayout.newTab().setText("Ter"));
        tabLayout.addTab(tabLayout.newTab().setText("Qua"));
        tabLayout.addTab(tabLayout.newTab().setText("Qui"));
        tabLayout.addTab(tabLayout.newTab().setText("Sex"));
        tabLayout.addTab(tabLayout.newTab().setText("Sáb"));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new ConsultaMedicoAdapter( ConsultaMedicoActivity.this, ConsultaMedicoActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        viewModelConsultas = ViewModelProviders.of(this).get(ViewModelConsultas.class);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0) {
                    observerHorarioDia(id, "seg");
                    toolbar.setSubtitle("Segunda-feira");
                } else if (tab.getPosition() == 1) {
                    observerHorarioDia(id,"ter");
                    toolbar.setSubtitle("Terça-feira");
                } else if(tab.getPosition() == 2) {
                    observerHorarioDia(id,"qua");
                    toolbar.setSubtitle("Quarta-feira");
                } else if(tab.getPosition() == 3) {
                    observerHorarioDia(id,"qui");
                    toolbar.setSubtitle("Quinta-feira");
                } else if(tab.getPosition() == 4) {
                    observerHorarioDia(id,"sex");
                    toolbar.setSubtitle("Sexta-feira");
                }else if(tab.getPosition() == 5) {
                    observerHorarioDia(id,"sáb");
                    toolbar.setSubtitle("Sábado");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModelConsultas.getConsultas(FirebaseRepository.getIdPessoaLogada(), "seg").observe(this, listaConsulta -> {
            this.consultas = listaConsulta;
            mAdapter.update(listaConsulta);
        });

    }

    public void observerHorarioDia(String medico, String dia) {
        toolbar.setSubtitle("Segunda-feira");
        viewModelConsultas.getConsultas(medico, dia).observe(this, consultaList -> {
            this.consultas = consultaList;
            mAdapter.update(consultas);
        });
    }




}
