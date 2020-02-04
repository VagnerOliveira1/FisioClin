package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;

import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicoAdapter extends RecyclerView.Adapter<ConsultaMedicoAdapter.ViewHolder>  {
    private List<Consulta> consultas = new ArrayList<>();
    private Context context;

    public ConsultaMedicoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ConsultaMedicoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_consulta_medico, parent, false);
        return new ConsultaMedicoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaMedicoAdapter.ViewHolder holder, int position) {

        Horario horario = consultas.get(position).getHorario();
        Paciente paciente = consultas.get(position).getPaciente();

        holder.textViewData.setText(horario.getDataFormatada());
        holder.textViewDiaSemana.setText(horario.getDiaDaSemanaFormatado());
        holder.textViewNomePacient.setText(paciente.getName());
        holder.textViewHora.setText(horario.getHoraFormatada());
        holder.textViewTelefonePacient.setText(paciente.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return consultas.size();
    }
    public void update(List<Consulta> consultas) {
        this.consultas = consultas;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewData;
        private TextView textViewHora;
        private TextView textViewDiaSemana;
        private TextView textViewNomePacient;
        private TextView textViewTelefonePacient;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewData = itemView.findViewById(R.id.consulta_data_medico);
            textViewDiaSemana = itemView.findViewById(R.id.consulta_dia_semana_medico);
            textViewHora = itemView.findViewById(R.id.consulta_hora_medico);
            textViewNomePacient = itemView.findViewById(R.id.consulta_nome_paciente_medico);
            textViewTelefonePacient = itemView.findViewById(R.id.consulta_telefone_paciente_medico);
            cardView = itemView.findViewById(R.id.card_view_consultas);

        }
    }

}
