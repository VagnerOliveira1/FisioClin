package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.controller.DeletarHorarioController;
import com.rightside.fisioclin.controller.MarcarConsultaController;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ViewHolder>  {
    private List<Consulta> consultas = new ArrayList<>();
    private Context context;

    public ConsultaAdapter(  Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ConsultaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_consulta, parent, false);
        return new ConsultaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaAdapter.ViewHolder holder, int position) {
        holder.textViewHora.setText(String.valueOf(consultas.get(position).getHorario().getHoraFormatada()));
        holder.textViewData.setText(String.valueOf(consultas.get(position).getHorario().getDataFormatada()));
        holder.textViewDiaSemana.setText(String.valueOf(consultas.get(position).getHorario().getDiaDaSemanaFormatado()));
        holder.textViewNomePacient.setText(consultas.get(position).getPaciente().getName());
        holder.textViewTelefonePacient.setText(consultas.get(position).getPaciente().getPhoneNumber());
        holder.cardView.getCardBackgroundColor();

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

            textViewData = itemView.findViewById(R.id.consulta_data);
            textViewDiaSemana = itemView.findViewById(R.id.consulta_dia_semana);
            textViewHora = itemView.findViewById(R.id.consulta_hora);
            textViewNomePacient = itemView.findViewById(R.id.consulta_nome_paciente);
            textViewTelefonePacient = itemView.findViewById(R.id.consulta_telefone_paciente);
            cardView = itemView.findViewById(R.id.card_view_consultas);

        }
    }

}
