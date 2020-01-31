package com.rightside.fisioclin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.adapter.HorarioAdapter;
import com.rightside.fisioclin.models.Horario;

import java.util.ArrayList;
import java.util.List;

public class HorarioActivity extends AppCompatActivity {
    private List<Horario> horarioList = new ArrayList<>();
    private HorarioAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new HorarioAdapter(horarioList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }
    private void prepareMovieData() {
        Horario horario = new Horario("10:30");
        horarioList.add(horario);
        horario = new Horario(" 14:30");
        horarioList.add(horario);


        mAdapter.notifyDataSetChanged();
    }
}
