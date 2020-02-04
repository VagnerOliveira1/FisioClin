package com.rightside.fisioclin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.repository.FirebaseRepository;

import java.util.List;

public class ViewModelConsultasPaciente extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;

    public ViewModelConsultasPaciente(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository();
    }

    public LiveData<List<Consulta>> getConsultaPacienteLogado() {
        return firebaseRepository.getMutableLiveDataConsultasPaciente();

    }

}
