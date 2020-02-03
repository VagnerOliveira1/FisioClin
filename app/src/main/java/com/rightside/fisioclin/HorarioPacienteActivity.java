package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.rightside.fisioclin.adapter.HorarioAdapter;
import com.rightside.fisioclin.adapter.HorarioPacienteAdapter;
import com.rightside.fisioclin.models.Horario;
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
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            list = new ArrayList<>();
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mAdapter = new HorarioPacienteAdapter(this, HorarioPacienteActivity.this);
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
