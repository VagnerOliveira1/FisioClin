package com.rightside.fisioclin.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Paciente;
import com.santalu.maskedittext.MaskEditText;

import org.jetbrains.annotations.NotNull;

import io.ghyeok.stickyswitch.widget.StickySwitch;


public class FichaPacienteActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private MaskEditText maskEditTextTelefone;
    private MaskEditText maskEditTextDataNasc;
    private TextInputEditText editTextNomePaciente;
    private TextInputEditText editTextProfissaoPaciente;
    private TextInputEditText editTextQueixaPrincipal;
    private TextInputEditText editTextDiagnosticoMedico;
    private TextInputEditText editTextTelefonePaciente;
    private TextInputEditText editEditTextDataNascPaciente;
    private StickySwitch stickySwitchSexoPaciente;


    private TextView mostraNumeroSessoes;

    private Button buttonSalvaFichaPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_paciente);

        maskEditTextTelefone = findViewById(R.id.editTelefonePaciente);
        maskEditTextDataNasc = findViewById(R.id.editDataNascPaciente);
        editTextNomePaciente = findViewById(R.id.editNomePaciente);
        editTextProfissaoPaciente = findViewById(R.id.editProfissaoPaciente);
        editTextQueixaPrincipal = findViewById(R.id.editQueixaPaciente);
        editTextDiagnosticoMedico = findViewById(R.id.editDiagnosticoMedico);
        stickySwitchSexoPaciente = findViewById(R.id.sticky_switch);



        buttonSalvaFichaPaciente = findViewById(R.id.btnSalvaFichaPaciente);

        mostraNumeroSessoes = findViewById(R.id.mostraNumeroSessoes);
        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(30);
        numberPicker.setOnValueChangedListener(this);



        stickySwitchSexoPaciente.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                Toast.makeText(FichaPacienteActivity.this, "Selecionado "+ text,Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        mostraNumeroSessoes.setText("Numero de sessões  " + newVal );

    }
}
