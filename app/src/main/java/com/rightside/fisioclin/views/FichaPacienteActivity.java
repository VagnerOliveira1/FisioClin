package com.rightside.fisioclin.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.rightside.fisioclin.R;
import com.santalu.maskedittext.MaskEditText;

import org.jetbrains.annotations.NotNull;

import io.ghyeok.stickyswitch.widget.StickySwitch;


public class FichaPacienteActivity extends AppCompatActivity {

    private MaskEditText maskEditTextTelefone;
    private MaskEditText maskEditTextDataNasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_paciente);

        maskEditTextTelefone = findViewById(R.id.editTelefonePaciente);
        maskEditTextDataNasc = findViewById(R.id.editDataNascPaciente);

        StickySwitch stickySwitch = findViewById(R.id.sticky_switch);

        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                Toast.makeText(FichaPacienteActivity.this, "Selecionado "+ text,Toast.LENGTH_LONG).show();
            }
        });



    }
}
