package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.DiagnosticoMedico;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.utils.GeralUtils;

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
        Consulta consulta  = consultaRealizadaList.get(position);
        Horario horario = consulta.getHorario();
        Medico medico = horario.getMedico();
        Paciente paciente = consulta.getPaciente();
        DiagnosticoMedico diagnosticoMedico = paciente.getDiagnosticoMedico();


        holder.textViewRecomendacao.setText("Evolução: " + consulta.getComentarioPosConsulta());
        holder.textViewDiagnostico.setText("Diagnóstico Médico: " + diagnosticoMedico.getDescricaoMedica());
        holder.textViewHora.setText(horario.getHoraFormatada());
        holder.textViewDia.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));
        holder.textViewData.setText(horario.getDataFormatada());
        holder.textViewQueixa.setText("Queixa: " + diagnosticoMedico.getQueixa());


    }

    @Override
    public int getItemCount() {
        return consultaRealizadaList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewData, textViewHora, textViewDia, textViewQueixa, textViewRecomendacao, textViewDiagnostico;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRecomendacao = itemView.findViewById(R.id.textViewRecomendacaoConsulta);
            textViewDiagnostico = itemView.findViewById(R.id.textViewDiagMedico);
            textViewData = itemView.findViewById(R.id.textView_consulta_realizada_data);
            textViewDia = itemView.findViewById(R.id.textView_consultas_realizada_dia_semana);
            textViewHora = itemView.findViewById(R.id.textView_consulta_realizada_hora);
            textViewQueixa = itemView.findViewById(R.id.textViewQueixaFicha);
        }
    }
}
