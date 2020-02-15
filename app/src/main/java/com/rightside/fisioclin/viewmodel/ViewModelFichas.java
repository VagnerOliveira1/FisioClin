package com.rightside.fisioclin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.repository.FirebaseRepository;

import java.util.List;

public class ViewModelFichas extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;

    public ViewModelFichas(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository();
    }

    public LiveData<List<Ficha>> getFichas() {
        return firebaseRepository.getMutableLiveDataFichas();

    }

    public LiveData<Ficha> getFicha(String id) {
        return firebaseRepository.getMutableLiveDataFicha(id);
    }
}
