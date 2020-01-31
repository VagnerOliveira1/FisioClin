package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Hour;

import java.util.ArrayList;
import java.util.List;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.MyViewHolder> {
    private List<Horario> horariosList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hora;

        MyViewHolder(View view) {
            super(view);
            hora = view.findViewById(R.id.horario_id);

        }
    }

    public HorarioAdapter(List<Horario> horariosList) {
        this.horariosList = horariosList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_horario, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Horario horario = horariosList.get(position);
        holder.hora.setText(horario.getHora());

    }

    @Override
    public int getItemCount() {
        return horariosList.size();
    }
}