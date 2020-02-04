package com.rightside.fisioclin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.repository.FirebaseRepository;

import java.util.List;

public class ViewModelConsultas extends AndroidViewModel {

    private FirebaseRepository firebaseRepository;

    public ViewModelConsultas(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository();
    }

    public LiveData<List<Consulta>> getConsultas() {
        return firebaseRepository.getMutableLiveDataConsultas();

    }

}
