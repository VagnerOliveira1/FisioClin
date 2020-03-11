package com.rightside.fisioclin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.repository.FirebaseRepository;

import java.util.List;

public class ViewModelHorarios extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;

    public ViewModelHorarios(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository();
    }

    public LiveData<List<Horario>> getHorarios(Medico medico,String diaSemana) {
        return firebaseRepository.getMutableLiveData(medico, diaSemana);
    }
}
