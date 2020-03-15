package com.rightside.fisioclin.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Pontuacao;
import com.rightside.fisioclin.repository.FirebaseRepository;

import java.util.List;

public class ViewModelPontuacao extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;

    public ViewModelPontuacao(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository();
    }


    public LiveData<Pontuacao> getPontuacaoTotal(Medico medico) {
        return firebaseRepository.getMutableLiveDataPontuacaoGeral(medico);
    }

    public LiveData<Pontuacao> getPontuacaoPaciente(Consulta consulta, String paciente) {
        return firebaseRepository.getMutableLiveDataPontuacaoPaciente(paciente, consulta);
    }
}
