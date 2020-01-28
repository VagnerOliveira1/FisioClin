package com.rightside.fisioclin;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rightside.fisioclin.models.Doctor;

public class MainActivity extends FragmentActivity {
    private ImageView imageViewDoctorPicture;
    private CardView cardViewNovoHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewDoctorPicture = findViewById(R.id.imageView_doctor_picture);
        cardViewNovoHorario = findViewById(R.id.cardview_novo_horario);

        cardViewNovoHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Doctor doctor = new Doctor("Priscila", "www.google.com", "05/12/1995", "Feminino", "32991313947");
        doctor.setProfilePictureUrl("http://3.bp.blogspot.com/-xVhOdAS5SWw/T-UNX6qzuHI/AAAAAAAAAks/uC4eXUcXmK4/s1600/IMG_4214.jpg");

        Glide.with(this).load(doctor.getProfilePictureUrl()).circleCrop().into(imageViewDoctorPicture);

        Log.d("teste", String.valueOf(doctor.isAdmin()));


    }
}
