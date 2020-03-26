package com.rightside.fisioclin.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Pontuacao;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelPontuacao;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvaliarFragment extends DialogFragment {

    private Medico medico;
    private RatingBar ratingBar;
    private Button avaliarMedicoButton;
    private Pontuacao pontuacao, pontuacaoPaciente;
    private ViewModelPontuacao viewModelPontuacao, viewModelPontuacaoPaciente;
    private ProgressBar progressBar;

    public static AvaliarFragment avaliarFragment(Consulta consulta) {
        // Required empty public constructor
        AvaliarFragment avaliarFragment = new AvaliarFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("medico", consulta);
        avaliarFragment.setArguments(bundle);
        return avaliarFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_avaliar, container, false);
        ratingBar = view.findViewById(R.id.smile_rating);
        progressBar = view.findViewById(R.id.progressBar2);
        avaliarMedicoButton = view.findViewById(R.id.avaliarMedicoButton);
        ratingBar.setNumStars(5);
        viewModelPontuacao = ViewModelProviders.of(this).get(ViewModelPontuacao.class);
        viewModelPontuacaoPaciente = ViewModelProviders.of(this).get(ViewModelPontuacao.class);
        Bundle bundle = getArguments();
        Consulta consulta = (Consulta) bundle.get("medico");
        medico = consulta.getHorario().getMedico();


        viewModelPontuacaoPaciente.getPontuacaoPaciente(consulta, FirebaseRepository.getIdPessoaLogada()).observe(this, pontuacaopaciente -> {
            this.pontuacaoPaciente = pontuacaopaciente;
        });


        viewModelPontuacao.getPontuacaoTotal(medico).observe(this,pontuacao1 -> {
            this.pontuacao = pontuacao1;
        });




        avaliarMedicoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(pontuacaoPaciente!= null) {
              GeralUtils.mostraAlerta("Erro ao avaliar consulta.", "Só é possivel avaliar uma vez por consulta", getContext());
                 dismiss();
             }else  {


                int voto = (int) ratingBar.getRating();

                if(voto > 0 ) {

                    if(pontuacao == null) {
                        pontuacao = new Pontuacao();
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    pontuacao.setQuantidadeDeVotos(1);
                    pontuacao.setPontos(voto);
                    pontuacao.setMedia(pontuacao.getPontos() / pontuacao.getQuantidadeDeVotos());
                    medico.setPontuacao(pontuacao);

                    FirebaseRepository.savePontuacao(medico, pontuacao).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful() || task.isComplete()) {
                                FirebaseRepository.savePontuacaoPaciente(consulta, pontuacao, FirebaseRepository.getIdPessoaLogada()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            FirebaseRepository.atualizaPontoMedico(medico).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        progressBar.setVisibility(View.GONE);

                                                        dismiss();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });

                            }

                        }
                    });

                } else {
                    Toast.makeText(getContext(), "Você deve escolher um valor para avaliar", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
