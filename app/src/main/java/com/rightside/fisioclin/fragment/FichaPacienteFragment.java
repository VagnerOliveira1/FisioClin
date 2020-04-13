package com.rightside.fisioclin.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.adapter.FichaPacienteAdapter;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FichaPacienteFragment extends DialogFragment {


    private TextView textViewFichaPacienteNome, textViewFichaPacienteSexo, textViewFichaPacienteTelefone,
            textViewFichaPacienteEmail, textViewFichaPacienteProfissao, textViewFichaPacienteDataNascimento;
    private ImageView imageViewPacienteFichaFoto;

    private FichaPacienteAdapter fichaPacienteAdapter;
    private FragmentActivity fragmentActivity;
    private Context context;


    public static FichaPacienteFragment novaInstancia(Ficha ficha){
        FichaPacienteFragment fichaPacienteFragment = new FichaPacienteFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ficha", ficha);
        fichaPacienteFragment.setArguments(bundle);
        return fichaPacienteFragment;
    }

    public FichaPacienteFragment setActivity(FragmentActivity fragmentActivity, Context context) {
        this.fragmentActivity = fragmentActivity;
        this.context = context;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ficha_paciente,container, false);
        RecyclerView recyclerViewFicha = view.findViewById(R.id.recyclerView_ficha_paciente_consultas_realizadas);
        Toolbar toolbar = view.findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Minha Ficha:");
        toolbar.setTitleTextColor(Color.WHITE);
        textViewFichaPacienteDataNascimento = view.findViewById(R.id.textView_ficha_paciente_nascimento);
        textViewFichaPacienteNome = view.findViewById(R.id.textView_ficha_paciente_nome);
        textViewFichaPacienteTelefone = view.findViewById(R.id.textView_ficha_paciente_telefone);
        textViewFichaPacienteProfissao = view.findViewById(R.id.textView_ficha_paciente_profissao);
        textViewFichaPacienteSexo = view.findViewById(R.id.textView_ficha_paciente_sexo);
        textViewFichaPacienteEmail = view.findViewById(R.id.textView_ficha_paciente_email);
        imageViewPacienteFichaFoto = view.findViewById(R.id.imageView_ficha_paciente_picture);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewFicha.getContext(),
                layoutManager.getOrientation());

        recyclerViewFicha.addItemDecoration(dividerItemDecoration);;
        recyclerViewFicha.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFicha.setHasFixedSize(true);


        Bundle bundle = getArguments();
        Ficha ficha = (Ficha) bundle.getSerializable("ficha");

        fichaPacienteAdapter = new FichaPacienteAdapter(fragmentActivity, context, ficha);
        recyclerViewFicha.setAdapter(fichaPacienteAdapter);
        List<Consulta> consultaList = ficha.getConsulta();
        fichaPacienteAdapter.update(consultaList);
        Paciente paciente = ficha.getPaciente();
        setInformacoesPacient(paciente);


        return view;

    }


    public void setInformacoesPacient(Paciente paciente) {
        textViewFichaPacienteNome.setText(paciente.getName());
        textViewFichaPacienteSexo.setText(paciente.getSexo());
        textViewFichaPacienteProfissao.setText(paciente.getProfissao());
        textViewFichaPacienteTelefone.setText(paciente.getPhoneNumber());
        textViewFichaPacienteDataNascimento.setText(paciente.getDataNascimento());
        textViewFichaPacienteEmail.setText(paciente.getEmail());
        GeralUtils.mostraImagemCircular(getContext(), imageViewPacienteFichaFoto, paciente.getProfilePictureUrl());

    }
    @Override
    public void onStart(){

        super.onStart();

        Dialog fichaPaciente = getDialog();
        if (fichaPaciente != null) {
            fichaPaciente.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

}
