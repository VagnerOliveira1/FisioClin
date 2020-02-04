package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.rightside.fisioclin.adapter.HorarioPacienteAdapter;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.viewmodel.ViewModelHorarios;

import java.util.ArrayList;
import java.util.List;

public class HorarioPacienteActivity extends AppCompatActivity {
        private List<Horario> list;
        private HorarioPacienteAdapter mAdapter;
        private ViewModelHorarios viewModelHorarios;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_horario_paciente);
            RecyclerView recyclerView = findViewById(R.id.recyclerView_consultas_medico);
            list = new ArrayList<>();

            Intent intent = getIntent();
            Paciente paciente = (Paciente) intent.getSerializableExtra("paciente");

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mAdapter = new HorarioPacienteAdapter(this, HorarioPacienteActivity.this, paciente);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);
            viewModelHorarios = ViewModelProviders.of(this).get(ViewModelHorarios.class);
            viewModelHorarios.getHorarios().observe(this, listaHorario -> {
                this.list = listaHorario;
                mAdapter.update(listaHorario);
            });





                Log.d("teste", String.valueOf(list.size()));


        }

}
