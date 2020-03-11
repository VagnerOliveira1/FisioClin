package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.rightside.fisioclin.adapter.MedicosAdapter;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.viewmodel.ViewModelMedicos;

public class ListaMedicosActivity extends AppCompatActivity {

    private MedicosAdapter medicosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);
       RecyclerView recyclerView = findViewById(R.id.recycler_view_medicos_lista);
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
       recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("usuario");
        medicosAdapter = new MedicosAdapter(this,user);
        recyclerView.setAdapter(medicosAdapter);
        toolbar.setTitle("Escolha o fisioterapeuta:");
        toolbar.setTitleTextColor(Color.WHITE);
        ViewModelMedicos viewModelMedicos = ViewModelProviders.of(this).get(ViewModelMedicos.class);

       viewModelMedicos.getMedicos().observe(this, medicoList -> {
           medicosAdapter.update(medicoList);
       });





    }
}
