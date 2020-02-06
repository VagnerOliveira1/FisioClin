package com.rightside.fisioclin;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.rightside.fisioclin.adapter.HorarioMedicoAdapter;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.viewmodel.ViewModelHorarios;

import java.util.ArrayList;
import java.util.List;

public class HorarioMedicoActivity extends FragmentActivity {
    private List<Horario> list;
    private HorarioMedicoAdapter mAdapter;
    private ViewModelHorarios viewModelHorarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_horario);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_consultas_medico);
        list = new ArrayList<>();
        TabLayout tabLayout = findViewById(R.id.tabLayout_navigation);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new HorarioMedicoAdapter(this, HorarioMedicoActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        viewModelHorarios = ViewModelProviders.of(this).get(ViewModelHorarios.class);

        tabLayout.addTab(tabLayout.newTab().setText("segunda"));
        tabLayout.addTab(tabLayout.newTab().setText("terça"));
        tabLayout.addTab(tabLayout.newTab().setText("quarta"));
        tabLayout.addTab(tabLayout.newTab().setText("quinta"));
        tabLayout.addTab(tabLayout.newTab().setText("sexta"));

        tabLayout.getTabAt(0).select();
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);


       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               if(tab.getPosition() == 0) {
                   Toast.makeText(HorarioMedicoActivity.this, "segunda", Toast.LENGTH_SHORT).show();
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









        Log.d("teste", String.valueOf(list.size()));



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

    }
}
