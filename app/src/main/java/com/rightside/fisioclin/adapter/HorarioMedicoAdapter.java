package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.controller.DeletarHorarioController;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.ArrayList;
import java.util.List;

public class HorarioMedicoAdapter extends RecyclerView.Adapter<HorarioMedicoAdapter.ViewHolder> {
    private List<Horario> horarios = new ArrayList<>();
    private Context context;
    private FragmentActivity fragmentActivity;

    public HorarioMedicoAdapter(Context context, FragmentActivity activity) {
        this.context = context;
        this.fragmentActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_horario, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Horario horario = horarios.get(position);
        if (horario.isMarcado()) {
            holder.cardView.setBackgroundColor(Color.RED);
            holder.imageViewCalendario.setImageResource(R.drawable.ic_date_range_white);
            holder.imageViewHorario.setImageResource(R.drawable.ic_access_time_white);
            holder.textViewData.setTextColor(Color.WHITE);
            holder.textViewDiaSemana.setTextColor(Color.WHITE);
            holder.textViewHora.setTextColor(Color.WHITE);
            holder.textViewDisponibilidade.setTextColor(Color.WHITE);
            holder.textViewDisponibilidade.setText("Horário indisponível");
            holder.textViewDomiciliar.setTextColor(Color.WHITE);

        } else {
            holder.cardView.setBackgroundColor(Color.WHITE);
            holder.textViewData.setTextColor(Color.BLACK);
            holder.textViewDiaSemana.setTextColor(Color.BLACK);
            holder.textViewHora.setTextColor(Color.BLACK);
            holder.textViewDisponibilidade.setTextColor(Color.BLACK);
            holder.textViewDisponibilidade.setText("");
            holder.imageViewHorario.setImageResource(R.drawable.ic_access_time_black_24dp);
            holder.imageViewCalendario.setImageResource(R.drawable.ic_date_range_black_24dp);
            holder.textViewDomiciliar.setTextColor(Color.RED);
        }
        holder.textViewHora.setText(horario.getHoraFormatada());
        holder.textViewData.setText(horario.getDataFormatada());
        holder.textViewDomiciliar.setText(GeralUtils.domiciliar(horario.isDomiciliar()) + " " + horario.getEndereco().getCidade() );
        holder.textViewDiaSemana.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));
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

    public void update(List<Horario> horarios) {
        this.horarios = horarios;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewData, textViewHora, textViewDiaSemana, textViewDisponibilidade,textViewDomiciliar;
        private CardView cardView;
        private ImageView imageViewCalendario, imageViewHorario;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewData = itemView.findViewById(R.id.horario_data);
            textViewDiaSemana = itemView.findViewById(R.id.horario_dia_semana);
            textViewHora = itemView.findViewById(R.id.horario_hora);
            cardView = itemView.findViewById(R.id.card_view_doctor_horarios);
            textViewDomiciliar = itemView.findViewById(R.id.textView_presencial);
            imageViewHorario = itemView.findViewById(R.id.imageViewRelogio);
            textViewDisponibilidade = itemView.findViewById(R.id.textView_horario_disponibilidade);
            imageViewCalendario = itemView.findViewById(R.id.imageViewCalendario);
        }
    }
}