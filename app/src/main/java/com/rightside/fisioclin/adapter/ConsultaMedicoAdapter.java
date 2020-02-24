package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.util.Log;
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
import com.rightside.fisioclin.controller.ConcluirConsultaController;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.GeralUtils;
import com.rightside.fisioclin.viewmodel.ViewModelFichas;

import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicoAdapter extends RecyclerView.Adapter<ConsultaMedicoAdapter.ViewHolder>  {
    private List<Consulta> consultas = new ArrayList<>();
    private Context context;
    private FragmentActivity fragmentActivity;

    public ConsultaMedicoAdapter(Context context, FragmentActivity fragmentActivity) {
        this.context = context;
        this.fragmentActivity = fragmentActivity;
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
        User paciente = consultas.get(position).getPaciente();

        Log.d("horarioid", horario.getId().toString());

        holder.textViewData.setText(horario.getDataFormatada());
        holder.textViewDiaSemana.setText(GeralUtils.capitalize(horario.getDiaDaSemanaFormatado()));
        holder.textViewNomePacient.setText(GeralUtils.capitalize(paciente.getName()));
        holder.textViewHora.setText(horario.getHoraFormatada());
        holder.textViewTelefonePacient.setText(paciente.getPhoneNumber());
        holder.textViewDataNascPacient.setText(paciente.getDataNascimento());
        holder.textViewProfissaoPacient.setText(paciente.getProfissao());
        GeralUtils.mostraImagemCircular(context, holder.imageViewFotoPaciente,paciente.getProfilePictureUrl());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConcluirConsultaController.alertaConcluirConsulta(fragmentActivity, consultas.get(position));
            }
        });

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
        private TextView textViewProfissaoPacient;
        private TextView textViewDataNascPacient;
        private ImageView imageViewFotoPaciente;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewData = itemView.findViewById(R.id.consulta_data_medico);
            textViewDiaSemana = itemView.findViewById(R.id.consulta_dia_semana_medico);
            textViewHora = itemView.findViewById(R.id.consulta_hora_medico);
            textViewNomePacient = itemView.findViewById(R.id.consulta_nome_paciente_medico);
            textViewTelefonePacient = itemView.findViewById(R.id.consulta_telefone_paciente_medico);
            textViewProfissaoPacient = itemView.findViewById(R.id.consulta_profissao_paciente_medico);
            textViewDataNascPacient = itemView.findViewById(R.id.data_nascimento_paciente_medico);
            imageViewFotoPaciente = itemView.findViewById(R.id.image_view_foto_paciente_medico);
            cardView = itemView.findViewById(R.id.card_view_consultas);

        }
    }




}
