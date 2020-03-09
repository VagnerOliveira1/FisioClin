package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.GeralUtils;

import org.jetbrains.annotations.NotNull;

import io.ghyeok.stickyswitch.widget.StickySwitch;

/**
 * A simple {@link Fragment} subclass.
 */
public class NovoHorarioDialogFragment extends DialogFragment {

    private TextView textViewData, textViewHora, textViewDiaSemana;
    private Button buttonSalvarHorario;
    private StickySwitch stickySwitchDomiciliar;
    private boolean domiciliar;


    public static NovoHorarioDialogFragment novoHorarioDialogFragment(Horario horario) {
        NovoHorarioDialogFragment novoHorarioDialogFragment = new NovoHorarioDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("horario", horario);
        novoHorarioDialogFragment.setArguments(bundle);
        return novoHorarioDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_novo_horario_dialog, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar_principal);

        toolbar.setTitle("Confirme os dados do novo horário: ");
        toolbar.setTitleTextColor(Color.WHITE);
        Bundle bundle = getArguments();
        Horario horario = (Horario) bundle.get("horario");

        textViewData = view.findViewById(R.id.textView_data);
        textViewDiaSemana = view.findViewById(R.id.textView_dia_semana);
        textViewHora = view.findViewById(R.id.textView_horario);
        buttonSalvarHorario = view.findViewById(R.id.button);
        stickySwitchDomiciliar = view.findViewById(R.id.sticky_switch_domiciliar);
        stickySwitchDomiciliar.setSwitchColor(Color.GREEN);
        stickySwitchDomiciliar.setSliderBackgroundColor(Color.LTGRAY);
        stickySwitchDomiciliar.setDirection(StickySwitch.Direction.RIGHT);
        stickySwitchDomiciliar.setActivated(true);



        stickySwitchDomiciliar.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String s) {
                if(s.equals("Sim")) {
                    domiciliar = true;
                } else {
                    domiciliar = false;
                }

            }
        });


        textViewData.setText(horario.getDataFormatada());
        textViewHora.setText(horario.getHoraFormatada());
        textViewDiaSemana.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));

        buttonSalvarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horario.setDomiciliar(domiciliar);
                FirebaseRepository.saveHour(horario);
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
