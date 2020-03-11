package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rightside.fisioclin.adapter.MedicosAdapter;
import com.rightside.fisioclin.viewmodel.ViewModelMedicos;

public class ListaMedicosActivity extends AppCompatActivity {

    private MedicosAdapter medicosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);
       RecyclerView recyclerView = findViewById(R.id.recycler_view_medicos_lista);
       recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        medicosAdapter = new MedicosAdapter(this);
        recyclerView.setAdapter(medicosAdapter);
        ViewModelMedicos viewModelMedicos = ViewModelProviders.of(this).get(ViewModelMedicos.class);

       viewModelMedicos.getMedicos().observe(this, medicoList -> {
           medicosAdapter.update(medicoList);
       });





    }
}
