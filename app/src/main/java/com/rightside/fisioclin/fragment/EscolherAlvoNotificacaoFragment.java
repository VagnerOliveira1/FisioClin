package com.rightside.fisioclin.fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rightside.fisioclin.PushNotificaTionFcm;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.controller.NovaNotificacaoPacientesController;
import com.rightside.fisioclin.controller.NovaNotificacaoUsersController;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.viewmodel.ViewModelUser;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EscolherAlvoNotificacaoFragment extends DialogFragment {

    private CardView cardViewNotificacaoPacientes, cardViewNotificacaoUsuarios;
    private ViewModelUser viewModelUserList;
    private List<User> userList;
    private FragmentActivity fragmentActivity;


    public static EscolherAlvoNotificacaoFragment novaInstancia(List<Consulta> consultaList){
        EscolherAlvoNotificacaoFragment escolherAlvoNotificacaoFragment = new EscolherAlvoNotificacaoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listConsulta", (Serializable) consultaList);
        escolherAlvoNotificacaoFragment.setArguments(bundle);
        return escolherAlvoNotificacaoFragment;
    }

    public EscolherAlvoNotificacaoFragment setFragmentActivity(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        return this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_escolher_alvo_notificacao, container, false);
        cardViewNotificacaoPacientes = view.findViewById(R.id.cardview_notificacao_pacientes);
        cardViewNotificacaoUsuarios = view.findViewById(R.id.cardview_notificacao_usuarios);

        Bundle bundle = getArguments();
        List<Consulta> listConsulta = (List<Consulta>) bundle.getSerializable("listConsulta");

        viewModelUserList = ViewModelProviders.of(this).get(ViewModelUser.class);

        viewModelUserList.getUsers().observe(this, userList -> {
            this.userList = userList;
        });

        Toolbar toolbar = view.findViewById(R.id.toolbar_principal);

        toolbar.setTitle("Notificação");
        toolbar.setSubtitle("Escolha o publico alvo");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.WHITE);

        cardViewNotificacaoPacientes.setOnClickListener(view1 -> {
           if(listConsulta != null) {
               NovaNotificacaoPacientesController.alertaNovaNotificacao(fragmentActivity, this, listConsulta);
           }
        });

        cardViewNotificacaoUsuarios.setOnClickListener(view1 -> {
            if(userList!= null) {
                NovaNotificacaoUsersController.alertaNovaNotificacaoUsers(fragmentActivity,this, userList);
            }
        });



        return view;


    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}
