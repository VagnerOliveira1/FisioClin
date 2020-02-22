package com.rightside.fisioclin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.repository.FirebaseRepository;

import java.util.List;

public class ViewModelUser extends AndroidViewModel {
    private FirebaseRepository firebaseRepository;

    public ViewModelUser(@NonNull Application application) {
        super(application);
        firebaseRepository = new FirebaseRepository();
    }

    public LiveData<User> getUser(String id) {
        return firebaseRepository.getMutableLiveDataUser(id);
    }

    public LiveData<List<User>> getUsers() {
        return firebaseRepository.getMutableLiveDataUsers();
    }
}
