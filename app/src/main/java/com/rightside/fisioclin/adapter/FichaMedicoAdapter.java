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
import com.rightside.fisioclin.models.Ficha;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.utils.GeralUtils;

import java.util.List;
import java.util.zip.Inflater;

public class FichaMedicoAdapter extends RecyclerView.Adapter<FichaMedicoAdapter.ViewHolder> {
    private List<Ficha> fichaList;
    private Context context;

    public FichaMedicoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ficha_medico, parent, false);
       return new FichaMedicoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Paciente paciente = fichaList.get(position).getPaciente();
        fichaList.get(position).getComentarioPosConsulta();
        holder.textViewFichaPacienteNome.setText(fichaList.get(position).getComentarioPosConsulta().toString());
        holder.textViewFichaPacienteTelefone.setText(paciente.getPhoneNumber());
        GeralUtils.mostraImagemCircular(context, holder.imageViewFichaPacienteFoto, paciente.getProfilePictureUrl());

    }

    @Override
    public int getItemCount() {
       if (fichaList == null) {
            return 0;
        }

        return fichaList.size();
    }

    public void update(List<Ficha> fichaList) {
        this.fichaList = fichaList;
        notifyDataSetChanged();
    }


     class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFichaPacienteNome, textViewFichaPacienteTelefone;
        private ImageView imageViewFichaPacienteFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewFichaPacienteNome = itemView.findViewById(R.id.textVIew_paciente_ficha_nome);
            textViewFichaPacienteTelefone = itemView.findViewById(R.id.textView_ficha_paciente_telefone);
            imageViewFichaPacienteFoto = itemView.findViewById(R.id.imageView_ficha_paciente_foto);

        }
    }
}
