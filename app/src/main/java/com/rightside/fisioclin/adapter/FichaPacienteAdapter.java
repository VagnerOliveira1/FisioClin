package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.DiagnosticoMedico;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.List;

public class FichaPacienteAdapter extends RecyclerView.Adapter<FichaPacienteAdapter.ViewHolder> {
    private List<Consulta> consultaList;
    private Context context;

    public FichaPacienteAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consultas_realizadas_paciente,parent,false);
        return new FichaPacienteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Consulta consulta = consultaList.get(position);
        Horario horario = consulta.getHorario();
        Medico medico = horario.getMedico();
        DiagnosticoMedico diagnosticoMedico = consulta.getPaciente().getDiagnosticoMedico();

        holder.textViewConsultaRealizaDiaSemana.setText(horario.getDiaDaSemanaFormatado());
        holder.textViewConsultaRealizadaData.setText(horario.getDataFormatada());
        holder.textViewConsultaRealizaHora.setText(horario.getHoraFormatada());
        holder.textViewConsultaRealizaDiaSemana.setText(horario.getDiaDaSemanaFormatado());
        holder.textViewQueixaFicha.setText(diagnosticoMedico.getQueixa());
        holder.textViewNomeMedico.setText(medico.getName());
        GeralUtils.mostraImagemCircular(context, holder.fotoMedico, medico.getProfilePictureUrl());


    }

    @Override
    public int getItemCount() {
        if (consultaList == null){
            return 0 ;
        }
        return consultaList.size();
    }

    public void update(List<Consulta> consultaList) {
        this.consultaList = consultaList;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView textViewConsultaRealizaHora,textViewConsultaRealizadaData, textViewConsultaRealizaDiaSemana,
        textViewQueixaFicha, textViewNomeMedico;
        private ImageView fotoMedico;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomeMedico = itemView.findViewById(R.id.textViewMedicoNomeConsultaRealizada);
            fotoMedico = itemView.findViewById(R.id.imageViewFotoMedicoFichaPaciente);
            textViewConsultaRealizadaData = itemView.findViewById(R.id.textView_consulta_realizada_data_paciente);
            textViewConsultaRealizaHora = itemView.findViewById(R.id.textView_consulta_realizada_hora_paciente);
            textViewConsultaRealizaDiaSemana = itemView.findViewById(R.id.textView_consultas_realizada_dia_semana_paciente);
            textViewQueixaFicha = itemView.findViewById(R.id.textViewQueixaFicha_paciente);


        }
    }
}
