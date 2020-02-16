package com.rightside.fisioclin;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.DatePickerDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_horario);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_consultas_medico);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton_novo_horario_medico);
        list = new ArrayList<>();
        TabLayout tabLayout = findViewById(R.id.tabLayout_navigation);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new HorarioMedicoAdapter(this, HorarioMedicoActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        viewModelHorarios = ViewModelProviders.of(this).get(ViewModelHorarios.class);

        tabLayout.addTab(tabLayout.newTab().setText("Seg"));
        tabLayout.addTab(tabLayout.newTab().setText("Ter"));
        tabLayout.addTab(tabLayout.newTab().setText("Qua"));
        tabLayout.addTab(tabLayout.newTab().setText("Qui"));
        tabLayout.addTab(tabLayout.newTab().setText("Sex"));

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
               } else if (tab.getPosition() == 1) {
                   observerHorarioDia("ter");
               } else if(tab.getPosition() == 2) {
                   observerHorarioDia("qua");
               } else if(tab.getPosition() == 3) {
                    observerHorarioDia("qui");
               } else if(tab.getPosition() == 4) {
                   observerHorarioDia("sex");
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
        horario = new Horario(hour, min, year, dayOfWeek, month, uniqueId, uniqueId);
        NovoHorarioDialogFragment.novoHorarioDialogFragment(horario).show(getSupportFragmentManager(), "horario");
    }

    @Override
    protected void onStart() {
        super.onStart();
        observerHorarioDia("seg");
    }
}
