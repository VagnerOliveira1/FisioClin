package com.rightside.fisioclin.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;

import java.util.List;

public class FirebaseRepository {



    private MutableLiveData<List<Horario>> mutableLiveDataHorarios = new MutableLiveData<>();

    private MutableLiveData<List<Consulta>> mutableLiveDataConsultas = new MutableLiveData<>();

    public static  FirebaseFirestore getDB() {
        return FirebaseFirestore.getInstance();
    }

    public static String getIdPessoaLogada(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static void saveHour(Horario horario) {
        getDB().collection("horarios").document(horario.getId()).set(horario.map());
    }
    public static Task<Void> saveDoctor(final Medico medico) {
        return getDB().collection("medicos").document(medico.getId()).set(medico.returnDoctor());
    }
    public static Task<Void> savePacient(final Paciente paciente) {
        return getDB().collection("pacientes").document(paciente.getId()).set(paciente.returnPacient());
    }

    public static DocumentReference getMedico(String id) {
        return getDB().collection("medicos").document(id);
    }

    public static DocumentReference getPaciente(String id) {
        return getDB().collection("pacientes").document(id);
    }


    public static CollectionReference getHorarios() {
        return getDB().collection("horarios");

    }


    public static Task<Void> saveConsulta(final Consulta consulta) {
        return getDB().collection("consultas").document(consulta.getPaciente().getId()).set(consulta.returnConsulta());
    }

    public static CollectionReference getConsultas() {
        return getDB().collection("consultas");

    }
















    public static Task<Void> deleteHorarios(String id) {
       return getHorarios().document(id).delete();
    }

    public LiveData<List<Horario>> getMutableLiveData() {
        getHorarios().addSnapshotListener((queryDocumentSnapshots, e) -> {
            mutableLiveDataHorarios.setValue(queryDocumentSnapshots.toObjects(Horario.class));
        });
        return mutableLiveDataHorarios;
    }

    public LiveData<List<Consulta>> getMutableLiveDataConsultas() {
        getConsultas().addSnapshotListener((queryDocumentSnapshots, e) -> {
            mutableLiveDataConsultas.setValue(queryDocumentSnapshots.toObjects(Consulta.class));
        });
        return mutableLiveDataConsultas;
    }




}
