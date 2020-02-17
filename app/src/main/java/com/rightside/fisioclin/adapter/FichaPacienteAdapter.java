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
import com.rightside.fisioclin.fragment.FichaPacienteFragment;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.DiagnosticoMedico;
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.Paciente;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_consultas_realizadas,parent,false);
        return new FichaPacienteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Consulta consulta = consultaList.get(position);
        Horario horario = consulta.getHorario();
        DiagnosticoMedico diagnosticoMedico = consulta.getPaciente().getDiagnosticoMedico();

        holder.textViewConsultaRealizaDiaSemana.setText(horario.getDiaDaSemanaFormatado());
        holder.textViewConsultaRealizadaData.setText(horario.getDataFormatada());
        holder.textViewConsultaRealizaHora.setText(horario.getHoraFormatada());
        holder.textViewConsultaRealizaDiaSemana.setText(horario.getDiaDaSemanaFormatado());
        holder.textViewPosConsulta.setText(consulta.getComentarioPosConsulta());
        holder.textViewDiagnosticoMedico.setText(diagnosticoMedico.getDescricaoMedica());
        holder.textViewQueixaFicha.setText(diagnosticoMedico.getQueixa());


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
        textViewQueixaFicha, textViewDiagnosticoMedico, textViewPosConsulta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewConsultaRealizadaData = itemView.findViewById(R.id.textView_consulta_realizada_data);
            textViewConsultaRealizaHora = itemView.findViewById(R.id.textView_consulta_realizada_hora);
            textViewConsultaRealizaDiaSemana = itemView.findViewById(R.id.textView_consultas_realizada_dia_semana);
            textViewQueixaFicha = itemView.findViewById(R.id.textViewQueixaFicha);
            textViewDiagnosticoMedico = itemView.findViewById(R.id.textViewDiagonosticoMedicaFicha);
            textViewPosConsulta = itemView.findViewById(R.id.textViewRecomendacaoConsulta);


        }
    }
}
