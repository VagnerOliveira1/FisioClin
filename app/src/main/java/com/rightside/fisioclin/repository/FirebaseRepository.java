package com.rightside.fisioclin.repository;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.FirebaseFirestore;
import com.rightside.fisioclin.models.Hour;

public class FirebaseRepository {
    private static FirebaseFirestore getDB() {
        return FirebaseFirestore.getInstance();
    }

    public static void save(Hour hour) {
        getDB().collection("horarios").document(hour.getId()).set(hour.map());
    }


    public static CollectionReference getHorarios() {
        return getDB().collection("horarios");

    }

    public static Task<Void> deleteHorarios(String id) {
       return getHorarios().document(id).delete();
    }


}
