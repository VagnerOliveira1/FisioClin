package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Hour;
import com.rightside.fisioclin.repository.FirebaseRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class NovoHorarioDialogFragment extends DialogFragment {

    private TextView textViewData, textViewHora, textViewDiaSemana;
    private Button buttonSalvarHorario;


    public static NovoHorarioDialogFragment novoHorarioDialogFragment(Hour hour) {
        NovoHorarioDialogFragment novoHorarioDialogFragment = new NovoHorarioDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("horario", hour);
        novoHorarioDialogFragment.setArguments(bundle);
        return novoHorarioDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_novo_horario_dialog, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_dados_consulta);

        toolbar.setTitle("Confirme os dados: ");
        Bundle bundle = getArguments();
        Hour hour = (Hour) bundle.get("horario");

        textViewData = view.findViewById(R.id.textView_data);
        textViewDiaSemana = view.findViewById(R.id.textView_dia_semana);
        textViewHora = view.findViewById(R.id.textView_horario);
        buttonSalvarHorario = view.findViewById(R.id.button);

        textViewData.setText(hour.getDataFormatada());
        textViewHora.setText(hour.getHoraFormatada());
        textViewDiaSemana.setText(hour.getDiaDaSemanaFormatado());

        buttonSalvarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRepository.save(hour);
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog novoHorarioDialog = getDialog();
        if (novoHorarioDialog != null) {
            novoHorarioDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
