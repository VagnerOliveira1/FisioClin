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
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.List;

public class FichaPacienteAdapter extends RecyclerView.Adapter<FichaPacienteAdapter.ViewHolder> {
    private Ficha fichaPaciente;
    private Context context;

    public FichaPacienteAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ficha_paciente,parent,false);


        return new FichaPacienteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Paciente paciente = fichaPaciente.getPaciente();
        holder.textViewFichaDoPacienteNome.setText(fichaPaciente.getPaciente().getName());
        holder.textViewFichaDoPacienteTelefone.setText(paciente.getPhoneNumber());
        GeralUtils.mostraImagemCircular(context, holder.imageViewFichaDoPacienteFoto, paciente.getProfilePictureUrl());


    }

    @Override
    public int getItemCount() {
        if (fichaPaciente == null){
            return 0 ;
        }
        return 1;
    }

    public void update(Ficha fichaPaciente) {
        this.fichaPaciente = fichaPaciente;
        notifyDataSetChanged();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFichaDoPacienteNome;
        private TextView textViewFichaDoPacienteTelefone;
        private ImageView imageViewFichaDoPacienteFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFichaDoPacienteNome = itemView.findViewById(R.id.textVIew_ficha_do_paciente_nome);
            textViewFichaDoPacienteTelefone = itemView.findViewById(R.id.textView_ficha_do_paciente_telefone);
            imageViewFichaDoPacienteFoto = itemView.findViewById(R.id.imageView_ficha_do_paciente_foto);

        }
    }
}
