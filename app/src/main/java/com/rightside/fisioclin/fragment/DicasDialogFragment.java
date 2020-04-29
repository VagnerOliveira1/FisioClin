package com.rightside.fisioclin.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rightside.fisioclin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DicasDialogFragment extends DialogFragment {
    private TextView textViewDicasTitulo, textViewDicasMensagem;
    private Button buttonDicas;

    public static DicasDialogFragment novaInstancia(String titulo, String mensagem) {
        DicasDialogFragment dicasDialogFragment = new DicasDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("titulo", titulo);
        bundle.putString("mensagem", mensagem);
        dicasDialogFragment.setArguments(bundle);
        return dicasDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dicas_dialog, container, false);
        textViewDicasTitulo = view.findViewById(R.id.textView_dicas_titulo);
        textViewDicasMensagem = view.findViewById(R.id.textView_dicas_mensagem);
        buttonDicas = view.findViewById(R.id.button_dicas_dialog);
        Bundle bundle = getArguments();
        String titulo = bundle.getString("titulo");
        String mensagem = bundle.getString("mensagem");
        textViewDicasTitulo.setText(titulo);
        textViewDicasMensagem.setText(mensagem);

        buttonDicas.setOnClickListener(view1 -> {
            dismiss();
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
