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
import com.rightside.fisioclin.controller.MarcarConsultaController;
import com.rightside.fisioclin.models.Consulta;
import com.rightside.fisioclin.models.Horario;
import com.rightside.fisioclin.models.User;

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
                    holder.textViewHora.setText(horario.getHoraFormatada());
                    holder.textViewData.setText(horario.getDataFormatada());
                    holder.textViewDiaSemana.setText(horario.getDiaDaSemanaFormatado());
                    holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            if (horarios.get(position).isMarcado() || consulta != null) {
                                Toast.makeText(context, "Não foi possivel marcar a consulta!", Toast.LENGTH_SHORT).show();
                            } else {
                                MarcarConsultaController.marcarConsulta(horario, usuario,fragmentActivity);
                                return true;
                            }
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

    public void setConsulta(Consulta consultaUsuario) {
        this.consulta = consultaUsuario;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewData, textViewHora, textViewDiaSemana;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewData = itemView.findViewById(R.id.horario_data);
            textViewDiaSemana = itemView.findViewById(R.id.horario_dia_semana);
            textViewHora = itemView.findViewById(R.id.horario_hora);
            cardView = itemView.findViewById(R.id.card_view_doctor_horarios);
        }
    }

}