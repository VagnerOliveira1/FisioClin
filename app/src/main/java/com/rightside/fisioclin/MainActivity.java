package com.rightside.fisioclin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rightside.fisioclin.models.Doctor;

public class MainActivity extends FragmentActivity {

    private Button btnNewHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewHour = findViewById(R.id.btn_new_hour);
        btnNewHour.setOnClickListener(v -> {
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
        });

        Doctor doctor = new Doctor("Priscila", "www.google.com", "05/12/1995", "Feminino", "32991313947");

        Log.d("teste", String.valueOf(doctor.isAdmin()));
    }
}
