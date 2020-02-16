package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.DiagnosticoMedico;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;

import java.util.List;

public class ConsultasRealizadasPacienteAdapter extends RecyclerView.Adapter<ConsultasRealizadasPacienteAdapter.ViewHolder> {

    private List<Consulta> consultaRealizadaList;
    private Context context;

    public ConsultasRealizadasPacienteAdapter(Context context, List<Consulta> consultaRealizadaList) {
        this.consultaRealizadaList = consultaRealizadaList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consultas_realizadas, parent, false);
        return new ConsultasRealizadasPacienteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Horario horario = consultaRealizadaList.get(position).getHorario();
        Paciente paciente = consultaRealizadaList.get(position).getPaciente();
        DiagnosticoMedico diagnosticoMedico = paciente.getDiagnosticoMedico();

        holder.textViewHora.setText(horario.getHoraFormatada());
        holder.textViewDia.setText(horario.getDiaDaSemanaFormatado());
        holder.textViewData.setText(horario.getDataFormatada());
        holder.textViewDiagnosticoMedico.setText(diagnosticoMedico.getDescricaoMedica());
        holder.textViewQueixa.setText(diagnosticoMedico.getQueixa());
    }

    @Override
    public int getItemCount() {
        return consultaRealizadaList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewData, textViewHora, textViewDia, textViewQueixa, textViewDiagnosticoMedico;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewData = itemView.findViewById(R.id.textView_consulta_realizada_data);
            textViewDia = itemView.findViewById(R.id.textView_consultas_realizada_dia_semana);
            textViewHora = itemView.findViewById(R.id.textView_consulta_realizada_hora);
            textViewQueixa = itemView.findViewById(R.id.textViewQueixaFicha);
            textViewDiagnosticoMedico = itemView.findViewById(R.id.textViewDiagonosticoMedicaFicha);
        }
    }
}
