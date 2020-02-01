package com.rightside.fisioclin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.rightside.fisioclin.adapter.HorarioAdapter;
import com.rightside.fisioclin.models.Hour;
import com.rightside.fisioclin.repository.FirebaseRepository;

import java.util.ArrayList;
import java.util.List;

public class HorarioDoctorActivity extends AppCompatActivity {
   private List<Hour> list;
    private HorarioAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_horario);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new HorarioAdapter(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        FirebaseRepository.getHorarios().get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Hour hour = documentSnapshot.toObject(Hour.class);
                        Log.d("horario", hour.getId());
                        list.add(hour);
                        Log.d("size", String.valueOf(list.size()));
                    }
                }
                if (list.size() > 0 ) {
                    mAdapter.update(list);
                }
            }



        });


        Log.d("teste", String.valueOf(list.size()));







    }

}
