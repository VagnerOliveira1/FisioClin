package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Hour;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.ViewHolder> {
    private List<Hour> horarios = new ArrayList<>();
    private Context context;

    public HorarioAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_horario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewHora.setText(String.valueOf(horarios.get(position).getHoraFormatada()));
        holder.textViewData.setText(String.valueOf(horarios.get(position).getDataFormatada()));
        holder.textViewDiaSemana.setText(String.valueOf(horarios.get(position).getDiaDaSemanaFormatado()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
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
            cardView = itemView.findViewById(R.id.cardview_horarios);
        }
    }
}