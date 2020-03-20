package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.android.billingclient.api.BillingClient;
import com.rightside.fisioclin.adapter.ProdutosAdapter;
import com.rightside.fisioclin.models.Medico;

import java.util.ArrayList;
import java.util.List;

public class LojaActivity extends AppCompatActivity {

    BillingClient billingClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Loja");
        toolbar.setTitleTextColor(Color.WHITE);
        Intent intent = getIntent();
        Medico medico = (Medico) intent.getSerializableExtra("medico");

        RecyclerView recyclerView = findViewById(R.id.recyclerview_loja);

        List<String> produtos = new ArrayList<>();
        produtos.add("1 Notificação - 512 FisioPoints");
        produtos.add("3 Notificações - 1375 FisioPoints");
        produtos.add("10 Notificações - 3180 FisioPoints");

        ProdutosAdapter produtosAdapter = new ProdutosAdapter(produtos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
       recyclerView.setAdapter(produtosAdapter);

    }
}
