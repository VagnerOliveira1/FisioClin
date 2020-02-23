package com.rightside.fisioclin;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.rightside.fisioclin.adapter.HorarioMedicoAdapter;
import com.rightside.fisioclin.controller.NovoHorarioController;
import com.rightside.fisioclin.fragment.DatePickerFragment;
import com.rightside.fisioclin.fragment.HourFragment;
import com.rightside.fisioclin.fragment.NovoHorarioDialogFragment;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.viewmodel.ViewModelHorarios;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HorarioMedicoActivity extends FragmentActivity implements HourFragment.TimePickerListener, DatePickerDialog.OnDateSetListener {
    private List<Horario> list;
    private HorarioMedicoAdapter mAdapter;
    private ViewModelHorarios viewModelHorarios;
    private int hour,min;
    private Horario horario;
    private Medico medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_horario);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_horarios_medico);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton_novo_horario_medico);
        list = new ArrayList<>();
        TabLayout tabLayout = findViewById(R.id.tabLayout_navigation);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new HorarioMedicoAdapter(this, HorarioMedicoActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        viewModelHorarios = ViewModelProviders.of(this).get(ViewModelHorarios.class);
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

        Intent intent = getIntent();
        medico = (Medico) intent.getSerializableExtra("medico");

        toolbar.setTitle("Horários:");
        toolbar.setSubtitle("Segunda-feira");

        tabLayout.addTab(tabLayout.newTab().setText("Seg"));
        tabLayout.addTab(tabLayout.newTab().setText("Ter"));
        tabLayout.addTab(tabLayout.newTab().setText("Qua"));
        tabLayout.addTab(tabLayout.newTab().setText("Qui"));
        tabLayout.addTab(tabLayout.newTab().setText("Sex"));
        tabLayout.addTab(tabLayout.newTab().setText("Sáb"));

        tabLayout.getTabAt(0).select();
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);

        floatingActionButton.setOnClickListener(view -> {
            NovoHorarioController.alertaDeNovoHorario(HorarioMedicoActivity.this);
        });

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               if(tab.getPosition() == 0) {
                   Toast.makeText(HorarioMedicoActivity.this, "seg", Toast.LENGTH_SHORT).show();
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
               }else if(tab.getPosition() == 5) {
                    observerHorarioDia("sáb");
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

    public void observerHorarioDia(String dia) {
        viewModelHorarios.getHorarios(dia).observe(this, listaHorario -> {
            this.list = listaHorario;
            mAdapter.update(listaHorario);
        });
    }

    
    @Override
    public void OnTimeSet(TimePicker timePicker, int hour, int min) {
        this.hour = hour;
        this.min = min;
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfWeek) {
        String uniqueId = UUID.randomUUID().toString();
        horario = new Horario(hour, min, year, dayOfWeek, month, uniqueId, uniqueId, medico);
        NovoHorarioDialogFragment.novoHorarioDialogFragment(horario).show(getSupportFragmentManager(), "horario");
    }

    @Override
    protected void onStart() {
        super.onStart();
        observerHorarioDia("seg");
    }
}
