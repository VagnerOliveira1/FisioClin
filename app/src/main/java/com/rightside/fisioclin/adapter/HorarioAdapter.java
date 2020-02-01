package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.controller.DeletarHorarioController;
import com.rightside.fisioclin.models.Hour;

import java.util.ArrayList;
import java.util.List;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.ViewHolder> {
    private List<Hour> horarios = new ArrayList<>();
    private Context context;
    private FragmentActivity fragmentActivity;

    public HorarioAdapter(Context context, FragmentActivity activity) {
        this.context = context;
        this.fragmentActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doctor_item_horario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewHora.setText(String.valueOf(horarios.get(position).getHoraFormatada()));
        holder.textViewData.setText(String.valueOf(horarios.get(position).getDataFormatada()));
        holder.textViewDiaSemana.setText(String.valueOf(horarios.get(position).getDiaDaSemanaFormatado()));
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DeletarHorarioController.alertaDeletarHorario(horarios.get(position),fragmentActivity);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return horarios.size();
    }
    public void update(List<Hour> horarios) {
        this.horarios = horarios;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewData, textViewHora, textViewDiaSemana;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewData = itemView.findViewById(R.id.horario_data);
            textViewDiaSemana = itemView.findViewById(R.id.horario_dia_semana);
            textViewHora = itemView.findViewById(R.id.horario_hora);
            cardView = itemView.findViewById(R.id.card_view_doctor_horarios);
        }
    }
}