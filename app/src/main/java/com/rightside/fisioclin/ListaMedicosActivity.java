package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.rightside.fisioclin.adapter.MedicosAdapter;
import com.rightside.fisioclin.fragment.DicasDialogFragment;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelMedicos;
import com.rightside.fisioclin.viewmodel.ViewModelPontuacao;

import java.util.ArrayList;
import java.util.List;

public class ListaMedicosActivity extends AppCompatActivity {

    private MedicosAdapter medicosAdapter;
    private List<Medico> medicoList;
    private boolean firstRun;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_medicos_lista);
        ViewModelMedicos viewModelMedicos = ViewModelProviders.of(this).get(ViewModelMedicos.class);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("usuario");
        medicoList = (List<Medico>) intent.getSerializableExtra("medicos");
        SearchView searchView = findViewById(R.id.searchBuscarMedico);
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
       recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        firstRun = prefs.getBoolean("firstRun", true);

        medicosAdapter = new MedicosAdapter(this,user, this);
        toolbar.setTitle("Escolha o fisioterapeuta:");
        toolbar.setTitleTextColor(Color.WHITE);

        recyclerView.setAdapter(medicosAdapter);

        medicosAdapter.update(medicoList);



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

    @Override
    public void onResume(){
        super.onResume();

        if(firstRun) {
            DicasDialogFragment.novaInstancia("Que bom ver você por aqui! vou te dar algumas dicas:", "Para escolher um fisioterapeuta e ver seus horários basta clicar, se quiser ver mais detalhes de localização basta clicar e segurar").show(getSupportFragmentManager(), "dicas");
            editor.putBoolean("firstRun",false);
            editor.commit();
        }

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
