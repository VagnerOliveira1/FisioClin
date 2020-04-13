package com.rightside.fisioclin.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Endereco;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComoChegarFragment extends DialogFragment {

    private TextView textViewCidade, textViewBairro, textViewNumero, textViewRua;
    private Button buttonChegar;
    private Context context;

    public static ComoChegarFragment comoChegarFragment(Endereco endereco){
    ComoChegarFragment comoChegarFragment = new ComoChegarFragment();
    Bundle bundle = new Bundle();
    bundle.putSerializable("endereco", endereco);
    comoChegarFragment.setArguments(bundle);
    return comoChegarFragment;
    }

    public ComoChegarFragment setContext(Context context) {
        this.context = context;
        return this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_como_chegar, container, false);
        textViewCidade = view.findViewById(R.id.textView3);
        textViewBairro = view.findViewById(R.id.textViewBairro);
        textViewNumero = view.findViewById(R.id.textViewNum);
        textViewRua = view.findViewById(R.id.textViewRua);
        buttonChegar = view.findViewById(R.id.buttonVernoMapa);
        Bundle bundle = getArguments();
        Endereco endereco = (Endereco) bundle.getSerializable("endereco");

        textViewCidade.setText(endereco.getCidade());
        textViewBairro.setText(endereco.getBairro());
        textViewNumero.setText(endereco.getNumero());
        textViewRua.setText(endereco.getLogradouro());

        buttonChegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + endereco.getCidade() + "+" + endereco.getBairro());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog detalhes = getDialog();
        if ( detalhes  != null) {
            detalhes .getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
