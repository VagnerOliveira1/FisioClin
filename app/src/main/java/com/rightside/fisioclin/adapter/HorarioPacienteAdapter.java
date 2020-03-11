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
import com.rightside.fisioclin.controller.MarcarConsultaController;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.ConstantUtils;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.ArrayList;
import java.util.List;

public class HorarioPacienteAdapter extends RecyclerView.Adapter<HorarioPacienteAdapter.ViewHolder> {
    private List<Horario> horarios = new ArrayList<>();
    private Context context;
    private FragmentActivity fragmentActivity;
    private User usuario;
    private Consulta consulta;


    public HorarioPacienteAdapter(Context context, FragmentActivity activity, User usuario) {
        this.context = context;
        this.fragmentActivity = activity;
        this.usuario = usuario;
    }

    @NonNull
    @Override
    public HorarioPacienteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_horario, parent, false);
        return new HorarioPacienteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorarioPacienteAdapter.ViewHolder holder, int position) {

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
            holder.textViewNomeDoMedicoHorario.setTextColor(Color.WHITE);
            holder.textViewFisioterapeuta.setTextColor(Color.WHITE);
            holder.textViewTelefoneMedicoHorario.setTextColor(Color.WHITE);
            holder.textViewCrefito.setTextColor(Color.WHITE);
            holder.textViewDomiciliar.setTextColor(Color.WHITE);
            holder.imageViewPhone.setImageResource(R.drawable.ic_phone_android_white_24dp);
        } else {
            holder.cardView.setBackgroundColor(Color.WHITE);
            holder.textViewData.setTextColor(Color.BLACK);
            holder.textViewCrefito.setTextColor(Color.BLACK);
            holder.textViewFisioterapeuta.setTextColor(Color.BLACK);
            holder.textViewTelefoneMedicoHorario.setTextColor(Color.BLACK);
            holder.textViewDiaSemana.setTextColor(Color.BLACK);
            holder.imageViewPhone.setImageResource(R.drawable.ic_phone_android_black_24dp);
            holder.textViewHora.setTextColor(Color.BLACK);
            holder.textViewDisponibilidade.setTextColor(Color.BLACK);
            holder.textViewNomeDoMedicoHorario.setText(horario.getMedico().getName());
            holder.textViewDisponibilidade.setText("");
            holder.imageViewHorario.setImageResource(R.drawable.ic_access_time_black_24dp);
            holder.imageViewCalendario.setImageResource(R.drawable.ic_date_range_black_24dp);
            holder.textViewNomeDoMedicoHorario.setTextColor(Color.BLACK);
            holder.textViewDomiciliar.setTextColor(Color.RED);
        }
        holder.textViewDomiciliar.setText(GeralUtils.domiciliar(horario.isDomiciliar()) + " " + horario.getEndereco().getCidade() );
        holder.textViewTelefoneMedicoHorario.setText(horario.getMedico().getPhoneNumber());
        holder.textViewCrefito.setText("Crefito: "+ horario.getMedico().getCrefito());
        GeralUtils.mostraImagemCircular(context, holder.imageViewFotoDoMedicoHorario, horario.getMedico().getProfilePictureUrl());
        holder.textViewHora.setText(horario.getHoraFormatada());
        holder.textViewData.setText(horario.getDataFormatada());
        holder.textViewDiaSemana.setText(GeralUtils.retornaDiaSemana(horario.getDiaDaSemanaFormatado()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (consulta != null) {
                    GeralUtils.mostraAlerta("Não foi possivel marcar consulta", ConstantUtils.VOCE_POSSUI_CONSULTA_MARCADA, context);
                } else if (horarios.get(position).isMarcado() && consulta == null){
                    GeralUtils.mostraAlerta("Não foi possivel marcar consulta", ConstantUtils.ESCOLHA_UM_HORARIO_DISPONIVEL, context);
                }
                else {
                    MarcarConsultaController.marcarConsulta(horario, usuario, fragmentActivity);
                }

            }
        });

    }


    @Override
    public int getItemCount() {

        if (horarios == null) {
            return 0;
        }

        return horarios.size();
    }

    public void update(List<Horario> horarios) {
        this.horarios = horarios;
        notifyDataSetChanged();
    }

    public void setConsulta(Consulta consultaUsuario) {
        this.consulta = consultaUsuario;
    }

    public void clear() {
        this.horarios.clear();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewData, textViewFisioterapeuta, textViewTelefoneMedicoHorario, textViewHora, textViewDiaSemana, textViewDisponibilidade,  textViewNomeDoMedicoHorario, textViewCrefito, textViewDomiciliar;
        private CardView cardView;
        private ImageView imageViewCalendario, imageViewHorario, imageViewFotoDoMedicoHorario, imageViewPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewData = itemView.findViewById(R.id.horario_data);
            textViewTelefoneMedicoHorario = itemView.findViewById(R.id.textViewtelefoneMedicoHorario);
            textViewDiaSemana = itemView.findViewById(R.id.horario_dia_semana);
            textViewHora = itemView.findViewById(R.id.horario_hora);
            textViewDomiciliar = itemView.findViewById(R.id.textView_presencial);
            textViewCrefito = itemView.findViewById(R.id.textViewCrefitoMedicoHorario);
            cardView = itemView.findViewById(R.id.card_view_doctor_horarios);
            textViewFisioterapeuta = itemView.findViewById(R.id.textViewFisioterapeuta);
            imageViewHorario = itemView.findViewById(R.id.imageViewRelogio);
            textViewDisponibilidade = itemView.findViewById(R.id.textView_horario_disponibilidade);
            imageViewCalendario = itemView.findViewById(R.id.imageViewCalendario);
            textViewNomeDoMedicoHorario = itemView.findViewById(R.id.textView_horario_medico_nome);
            imageViewFotoDoMedicoHorario = itemView.findViewById(R.id.imageView_foto_do_medico_horario);
            imageViewPhone = itemView.findViewById(R.id.imageViewPhone);
        }
    }

}