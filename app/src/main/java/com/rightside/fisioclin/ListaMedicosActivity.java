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
import android.widget.SearchView;

import com.rightside.fisioclin.adapter.MedicosAdapter;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.viewmodel.ViewModelMedicos;

import java.util.ArrayList;
import java.util.List;

public class ListaMedicosActivity extends AppCompatActivity {

    private MedicosAdapter medicosAdapter;
    private List<Medico> medicoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);
       RecyclerView recyclerView = findViewById(R.id.recycler_view_medicos_lista);
        SearchView searchView = findViewById(R.id.searchBuscarMedico);
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
       recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("usuario");
        medicosAdapter = new MedicosAdapter(this,user, this);
        recyclerView.setAdapter(medicosAdapter);
        toolbar.setTitle("Escolha o fisioterapeuta:");
        toolbar.setTitleTextColor(Color.WHITE);
        ViewModelMedicos viewModelMedicos = ViewModelProviders.of(this).get(ViewModelMedicos.class);

       viewModelMedicos.getMedicos().observe(this, medicoList -> {
           this.medicoList = medicoList;
           medicosAdapter.update(medicoList);
       });

       searchView.setQueryHint("Informe o nome ");
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               findChannel(s);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
               findChannel(s);
               return false;
           }
       });



    }

    private void findChannel(String text) {
        if(medicoList.size() > 0 && !text.isEmpty()) {
            List<Medico> medicoSearch = new ArrayList<>();
            for(Medico medico : medicoList){
                if(medico.contain(text)){
                    medicoSearch.add(medico);
                }
            }
            medicosAdapter.update(medicoSearch);
        }else if(text.isEmpty()){
            //   findChannels();
            medicosAdapter.update(medicoList);
        }
    }
}
