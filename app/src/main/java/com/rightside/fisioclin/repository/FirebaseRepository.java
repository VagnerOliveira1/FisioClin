package com.rightside.fisioclin.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rightside.fisioclin.models.Doctor;
import com.rightside.fisioclin.models.Hour;

import java.util.List;

public class FirebaseRepository {

    private MutableLiveData<List<Hour>> mutableLiveDataHorarios = new MutableLiveData<>();

    public static  FirebaseFirestore getDB() {
        return FirebaseFirestore.getInstance();
    }

    public static String getIdPersonLoggedIn(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static void save(Hour hour) {
        getDB().collection("horarios").document(hour.getId()).set(hour.map());
    }
    public static Task<Void> saveDoctor(final Doctor doctor) {
        return getDB().collection("doctors").document(doctor.getId()).set(doctor.returnDoctor());
    }

    public static DocumentReference getDoctor(String id) {
        return getDB().collection("doctors").document(id);
    }


    public static CollectionReference getHorarios() {
        return getDB().collection("horarios");

    }

    public static Task<Void> deleteHorarios(String id) {
       return getHorarios().document(id).delete();
    }

    public LiveData<List<Hour>> getMutableLiveData() {
        getHorarios().addSnapshotListener((queryDocumentSnapshots, e) -> {
            mutableLiveDataHorarios.setValue(queryDocumentSnapshots.toObjects(Hour.class));
        });
        return mutableLiveDataHorarios;
    }


}
