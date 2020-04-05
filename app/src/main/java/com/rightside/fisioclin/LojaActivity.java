package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.rightside.fisioclin.adapter.ProdutosAdapter;
import com.rightside.fisioclin.models.FisioPoints;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Produto;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelMedicos;

import java.util.ArrayList;
import java.util.List;

public class LojaActivity extends AppCompatActivity implements RewardedVideoAdListener {


    private RewardedVideoAd mRewardedVideoAd;
    private TextView textViewFisioPoints;
    private Button buttonFisioPoints;
    private Medico medico;
    private FisioPoints fisioPoints;
    private ViewModelMedicos viewModelMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);
        Toolbar toolbar = findViewById(R.id.toolbar_principal);
        textViewFisioPoints = findViewById(R.id.textview_fisio_points);
        toolbar.setTitle("Loja");
        toolbar.setTitleTextColor(Color.WHITE);
        buttonFisioPoints = findViewById(R.id.button_adquirir_fisio_points);
        viewModelMedicos = ViewModelProviders.of(this).get(ViewModelMedicos.class);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_loja);

        Produto notificacao = new Produto();
        notificacao.setNome("1 - Notificação");
        notificacao.setPreco(975);
        notificacao.setQuantidade(1);
        notificacao.setTipo(1);

        Produto notificacoestres = new Produto();
        notificacoestres.setNome("3 Notificações");
        notificacoestres.setPreco(2600);
        notificacoestres.setQuantidade(3);
        notificacoestres.setTipo(1);

        Produto notificacoesdez = new Produto();
        notificacoesdez.setNome("10 Notificações");
        notificacoesdez.setPreco(5550);
        notificacoesdez.setQuantidade(10);
        notificacoesdez.setTipo(1);


        Produto relatorio = new Produto();
        relatorio.setNome("1 Backup da ficha em PDF salva no dispositivo");
        relatorio.setPreco(975);
        relatorio.setQuantidade(1);
        relatorio.setTipo(2);

        Produto relatoriotres = new Produto();
        relatoriotres.setNome("3 Backups da ficha em PDF salva no dispositivo");
        relatoriotres.setPreco(2600);
        relatoriotres.setQuantidade(3);
        relatoriotres.setTipo(2);

        Produto relatoriodez = new Produto();
        relatoriodez.setNome("10 Backups da ficha em PDF salva no dispositivo");
        relatoriodez.setPreco(5550);
        relatoriodez.setQuantidade(10);
        relatoriodez.setTipo(2);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(notificacao);
        produtos.add(notificacoestres);
        produtos.add(notificacoesdez);
        produtos.add(relatorio);
        produtos.add(relatoriotres);
        produtos.add(relatoriodez);

        ProdutosAdapter produtosAdapter = new ProdutosAdapter(produtos, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(produtosAdapter);

        viewModelMedicos.getMedico().observe(this, medico1 -> {
            medico = medico1;
            fisioPoints = medico.getFisioPoints();
            textViewFisioPoints.setText("Você possui " + String.valueOf(medico1.getFisioPoints().getPoints()) + "");
            produtosAdapter.updateMedico(medico1);
        });



        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
        buttonFisioPoints.setOnClickListener(view -> {
            buttonFisioPoints.setEnabled(false);
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
            }
        });


    }


    @Override
    public void onRewardedVideoAdLoaded() {
        buttonFisioPoints.setEnabled(true);
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {


    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        fisioPoints.setPoints(medico.getFisioPoints().getPoints() + 150);
        medico.setFisioPoints(fisioPoints);
        FirebaseRepository.atualizaPontoMedico(medico);
        GeralUtils.mostraAlerta("Uhuuul!", "Você ganhou +150 FisioPoints!", this);
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {
    }
}
