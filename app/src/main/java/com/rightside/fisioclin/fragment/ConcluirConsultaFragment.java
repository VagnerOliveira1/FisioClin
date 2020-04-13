package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.ConstantUtils;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConcluirConsultaFragment extends DialogFragment {

    private ViewModelFichas viewModelFichas, viewModelFichasMedico;
    private Ficha fichaPaciente, fichaMedico;
    private Button button;
    private TextInputEditText textInputEditTextConcluirConsulta;
    private FragmentActivity fragmentActivity;
    private ProgressBar progressBar;

    public static ConcluirConsultaFragment novaInstancia(Consulta consulta){
        ConcluirConsultaFragment concluirConsultaFragment = new ConcluirConsultaFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("consulta", consulta);
        concluirConsultaFragment.setArguments(bundle);
        return concluirConsultaFragment;
        // Required empty public constructor
    }

    public ConcluirConsultaFragment getFragment(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_concluir_consulta, container, false);
        button = view.findViewById(R.id.button2);
        textInputEditTextConcluirConsulta = view.findViewById(R.id.edittext_concluirconsulta);
        progressBar = view.findViewById(R.id.progressBarSaveFicha);
        Bundle bundle = getArguments();
        Consulta consulta = (Consulta) bundle.get("consulta");
        viewModelFichas = ViewModelProviders.of(this).get(ViewModelFichas.class);
        viewModelFichasMedico = ViewModelProviders.of(this).get(ViewModelFichas.class);

        viewModelFichasMedico.getFichaMedico(FirebaseRepository.getIdPessoaLogada(), consulta.getPaciente().getId()).observe(this, fichaMedico -> {
            this.fichaMedico = fichaMedico;
        });
        viewModelFichas.getFicha(consulta.getPaciente().getId()).observe(this, ficha -> {
           this.fichaPaciente = ficha;
        });



        button.setOnClickListener(view1 -> {


            if(this.fichaMedico == null) {
                this.fichaMedico = new Ficha();
            }
            if (this.fichaPaciente == null)  {
                this.fichaPaciente = new Ficha();
            }

            if (textInputEditTextConcluirConsulta.getText().toString().isEmpty()) {
                GeralUtils.mostraAlerta("Atenção!", ConstantUtils.IMPORTANTE_FAZER_COMENTARIO_SOBRE_CONSULTA, getContext());
            } else {
                progressBar.setVisibility(View.VISIBLE);
                consulta.setComentarioPosConsulta(textInputEditTextConcluirConsulta.getText().toString());
                fichaPaciente.getConsulta().add(consulta);
                fichaPaciente.setPaciente(consulta.getPaciente());
                fichaMedico.getConsulta().add(consulta);
                fichaMedico.setPaciente(consulta.getPaciente());

                FirebaseRepository.concluirConsultaMedico(consulta).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                        FirebaseRepository.concluirConsultaUsuario(consulta).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                FirebaseRepository.deleteHorarios(consulta.getHorario()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        FirebaseRepository.saveFichaMedico(fichaMedico, FirebaseRepository.getIdPessoaLogada()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()) {
                                                    FirebaseRepository.saveFichaPaciente(fichaPaciente).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            progressBar.setVisibility(View.GONE);
                                                            dismiss();
                                                            fragmentActivity.finish();
                                                        }
                                                    });

                                                }

                                            }
                                        });
                                    }
                                });

                                }
                            }
                        });
                        }
                    }
                });

            }


        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog consultaPacienteDialog = getDialog();
        if ( consultaPacienteDialog  != null) {
            consultaPacienteDialog .getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

}
