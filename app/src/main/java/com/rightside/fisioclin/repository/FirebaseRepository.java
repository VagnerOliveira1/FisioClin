package com.rightside.fisioclin.repository;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.models.Pontuacao;
import com.rightside.fisioclin.models.User;

import java.util.List;


public class FirebaseRepository {


    private MutableLiveData<List<Horario>> mutableLiveDataHorarios = new MutableLiveData<>();

    private MutableLiveData<List<Consulta>> mutableLiveDataConsultas = new MutableLiveData<>();

    private MutableLiveData<Consulta> mutableLiveDataConsultaPaciente = new MutableLiveData<>();

    private MutableLiveData<List<Ficha>> mutableLiveDataFichas = new MutableLiveData<>();
    private MutableLiveData<Ficha> mutableLiveDataFichasMedico = new MutableLiveData<>();

    private  MutableLiveData<Ficha> mutableLiveDataFicha = new MutableLiveData<>();

    private MutableLiveData<User> mutableLiveDataUser = new MutableLiveData<>();

    private MutableLiveData<List<User>> mutableLiveDataUsersList = new MutableLiveData<>();

    private MutableLiveData<List<Medico>> mutableLiveDataMedicosList = new MutableLiveData<>();
    private MutableLiveData<Medico> mutableLiveDataMedico = new MutableLiveData<>();

    private MutableLiveData<Pontuacao> mutableLiveDataPontuacao = new MutableLiveData<>();
    private MutableLiveData<Pontuacao> mutableLiveDataPontuacaoPaciente = new MutableLiveData<>();


    public static FirebaseFirestore getDB() {
        return FirebaseFirestore.getInstance();
    }

    public static String getIdPessoaLogada() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static void saveHour(Horario horario) {
        getDB().collection("horarios").document(horario.getDiaDaSemanaFormatado()).collection("horariosID").document(horario.getId()).set(horario.map());

    }

    public static void saveHourTeste(Horario horario) {
        getDB().collection("horariosids").document(getIdPessoaLogada()).collection("diadasemana").document(horario.getDiaDaSemanaFormatado()).collection("horarios").document(horario.getId()).set(horario.map());
    }

    public static Task<Void> saveDoctor(final Medico medico) {
        return getDB().collection("medicos").document(medico.getId()).set(medico.returnDoctor());
    }

    public static Task<Void> savePacient(final Paciente paciente) {

        return getDB().collection("pacientes").document(paciente.getId()).set(paciente.returnPacient());
    }

    public static Task<Void> saveUser(final User user) {
        return getDB().collection("usuarios").document(user.getId()).set(user.returnUser());
    }

    public static DocumentReference getMedico(String id) {
        return getDB().collection("medicos").document(id);
    }

    public static CollectionReference getMedicos() {
        return getDB().collection("medicos");
    }

    public static CollectionReference getMedico() {
        return getDB().collection("medicos");
    }

    public static DocumentReference getPaciente(String id) {
        return getDB().collection("pacientes").document(id);
    }

    public static DocumentReference getUser(String id) {
        return getDB().collection("usuarios").document(id);
    }

    public static CollectionReference getUsers(){
        return getDB().collection("usuarios");
    }


    public static CollectionReference getHorarios() {

        return getDB().collection("horariosids");

    }

    public static Task<Void> saveConsultaUser(final Consulta consulta) {
        return getDB().collection("consultas").document(FirebaseRepository.getIdPessoaLogada()).set(consulta.returnConsulta());

    }

    public static Task<Void> saveConsultaMedico(final Consulta consulta) {
        return getDB().collection("consultas").document(consulta.getHorario().getMedico().getId()).collection("diasemana").document(consulta.getHorario().getDiaDaSemanaFormatado()).collection("usuario").document(consulta.getPaciente().getId()).set(consulta.returnConsulta());

    }


    public static Task<Void> saveFichaMedico(final Ficha ficha, String medicoID) {
        return getDB().collection("fichas").document(medicoID).collection("usuarios").document(ficha.getPaciente().getId()).set(ficha.returnFicha());
    }

    public static Task<Void> saveFichaPaciente(final Ficha ficha) {
        return getDB().collection("fichas").document(ficha.getPaciente().getId()).set(ficha.returnFicha());
    }

    public static DocumentReference getConsultaPacienteLogado() {
        return getDB().collection("consultas").document(FirebaseRepository.getIdPessoaLogada());
    }

    public static CollectionReference getConsultas() {
        return getDB().collection("consultas");

    }

    public static Task<Void> atualizaPontoMedico(Medico medico) {
        return getMedico(medico.getId()).update(medico.returnDoctor());
    }

    public static Task<Void> atualizaFichaPaciente(Ficha ficha) {
        return getFichas().document(FirebaseRepository.getIdPessoaLogada()).update(ficha.returnFicha());
    }

    public static Task<Void> atualizaFichaMedico(Ficha ficha, int position) {
        return getFichas().document(ficha.getConsulta().get(position).getHorario().getMedico().getId()).collection("usuarios").document(FirebaseRepository.getIdPessoaLogada()).update(ficha.returnFicha());
    }

    public static Task<Void> atualizaPaciente(Paciente paciente, Consulta consulta) {
        return getPaciente(paciente.getId()).update(paciente.returnPacient());
    }

    public static Task<Void> atualizaHorarioMarcado(Horario horario) {
        horario.setMarcado(true);
        return getHorarios().document(horario.getMedico().getId()).collection("diadasemana").document(horario.getDiaDaSemanaFormatado()).collection("horarios").document(horario.getId()).update(horario.map());
    }

    public static Task<Void> deleteHorarios(Horario horario) {
        return getHorarios().document(getIdPessoaLogada()).collection("diadasemana").document(horario.getDiaDaSemanaFormatado()).collection("horarios").document(horario.getHorarioNumber()).delete();
    }

    public static Task<Void> concluirConsultaMedico(Consulta consulta) {
        return getConsultas().document(consulta.getHorario().getMedico().getId()).collection("diasemana").document(consulta.getHorario().getDiaDaSemanaFormatado()).collection("usuario").document(consulta.getPaciente().getId()).delete();
    }

    public static Task<Void> concluirConsultaUsuario(Consulta consulta) {
        return getConsultas().document(consulta.getPaciente().getId()).delete();
    }

    public static Task<Void> savePontuacao(Medico medico, Pontuacao pontuacao) {
       return getDB().collection("pontuacao").document(medico.getId()).set(pontuacao.returnPontuacao());
    }

    public static Task<Void> savePontuacaoPaciente(Consulta consulta, Pontuacao pontuacao, String idPaciente) {
        return getDB().collection("pontuacao").document(idPaciente).collection("medico").document(consulta.getHorario().getMedico().getId()).collection("consulta").document(consulta.getHorario().getHorarioNumber()).set(pontuacao.returnPontuacao());
    }

    public static DocumentReference getPontuacao(Medico medico) {
        return getDB().collection("pontuacao").document(medico.getId());
    }

    public static DocumentReference getPontuacaoPaciente(Consulta consulta, String id) {
        return getDB().collection("pontuacao").document(id).collection("medico").document(consulta.getHorario().getMedico().getId()).collection("consulta").document(consulta.getHorario().getHorarioNumber());
    }

    public static CollectionReference getFichas() {
        return getDB().collection("fichas");
    }

    public LiveData<Pontuacao> getMutableLiveDataPontuacaoGeral (Medico medico) {
        getPontuacao(medico).addSnapshotListener((documentSnapshot, e) -> {
            mutableLiveDataPontuacao.setValue(documentSnapshot.toObject(Pontuacao.class));
        });

        return mutableLiveDataPontuacao;
    }

    public LiveData<Pontuacao> getMutableLiveDataPontuacaoPaciente (String id, Consulta consulta) {
        getPontuacaoPaciente(consulta, id).addSnapshotListener((documentSnapshot, e) -> {
            mutableLiveDataPontuacaoPaciente.setValue(documentSnapshot.toObject(Pontuacao.class));
        });

        return mutableLiveDataPontuacaoPaciente;
    }
    public LiveData<List<Horario>> getMutableLiveData(Medico medico, String diaSemana) {
        getHorarios().document(medico.getId()).collection("diadasemana").document(diaSemana).collection("horarios").addSnapshotListener((queryDocumentSnapshots, e) -> {
            mutableLiveDataHorarios.setValue(queryDocumentSnapshots.toObjects(Horario.class));
        });
        return mutableLiveDataHorarios;
    }

    public LiveData<List<Consulta>> getMutableLiveDataConsultas(String idMedicoLogado, String diaSemana) {
        getConsultas().document(idMedicoLogado).collection("diasemana").document(diaSemana).collection("usuario").addSnapshotListener((queryDocumentSnapshots, e) -> {
            mutableLiveDataConsultas.setValue(queryDocumentSnapshots.toObjects(Consulta.class));
        });
        return mutableLiveDataConsultas;
    }

    public LiveData<Consulta> getMutableLiveDataConsultaPaciente() {
        getConsultaPacienteLogado().addSnapshotListener((documentSnapshot, e) -> {
            mutableLiveDataConsultaPaciente.setValue(documentSnapshot.toObject(Consulta.class));
        });

        return mutableLiveDataConsultaPaciente;
    }


    public LiveData<List<Ficha>> getMutableLiveDataFichas(String medicoId) {
        getFichas().document(medicoId).collection("usuarios").addSnapshotListener((queryDocumentSnapshots, e) -> {
            mutableLiveDataFichas.setValue(queryDocumentSnapshots.toObjects(Ficha.class));
        });
        return mutableLiveDataFichas;
    }


    public LiveData<Ficha> getMutableLiveDataFichasPacienteMedico(String idMedico, String idPaciente) {
         getFichas().document(idMedico).collection("usuarios").document(idPaciente).addSnapshotListener((documentSnapshot, e) -> {
             mutableLiveDataFichasMedico.setValue(documentSnapshot.toObject(Ficha.class));
         });

         return mutableLiveDataFichasMedico;
    }



    public LiveData<Ficha> getMutableLiveDataFicha(String id) {
        getFichas().document(id).addSnapshotListener((documentSnapshot, e) -> {
            mutableLiveDataFicha.setValue(documentSnapshot.toObject(Ficha.class));
        });
        return mutableLiveDataFicha;
    }

    public LiveData<User> getMutableLiveDataUser(String id) {
        getUser(id).addSnapshotListener((documentSnapshot, e) -> {
           mutableLiveDataUser.setValue(documentSnapshot.toObject(User.class));
        });

        return mutableLiveDataUser;
    }

    public LiveData<List<User>> getMutableLiveDataUsers() {
        getUsers().addSnapshotListener((queryDocumentSnapshots, e) -> {
           mutableLiveDataUsersList.setValue(queryDocumentSnapshots.toObjects(User.class));
        });
       return mutableLiveDataUsersList;
    }

    public LiveData<List<Medico>> getMutableLiveDataMedicos() {
        getMedicos().addSnapshotListener((queryDocumentSnapshots, e) -> {
            mutableLiveDataMedicosList.setValue(queryDocumentSnapshots.toObjects(Medico.class));
        });

        return mutableLiveDataMedicosList;
    }

    public LiveData<Medico> getMutableLiveDataMedico() {
        getMedico(FirebaseRepository.getIdPessoaLogada()).addSnapshotListener((documentSnapshot, e) -> {
            mutableLiveDataMedico.setValue(documentSnapshot.toObject(Medico.class));
        });
        return mutableLiveDataMedico;
    }

    //mudei a forma de salvar o horario para adicionarmos varios medicos ao mesmo app. ainda é necessario atualizar o delete do horario consulta, update do horario e delete horario;

}









