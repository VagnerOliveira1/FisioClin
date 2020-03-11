package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.utils.GeralUtils;

import org.w3c.dom.Text;

import java.util.List;

public class MedicosAdapter extends RecyclerView.Adapter<MedicosAdapter.ViewHolderMedicos> {
    private  Context context;
    private List<Medico> medicoList;

    public MedicosAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderMedicos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_medicos, parent, false );
        return new ViewHolderMedicos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMedicos holder, int position) {
        Medico medico = medicoList.get(position);
        holder.textViewLocaldeAtendimento.setText(medico.getEndereco().getCidade());
        holder.textViewNomeMedico.setText(medico.getName());
        GeralUtils.mostraImagemCircular(context, holder.imageViewFotoMedico, medico.getProfilePictureUrl());
    }

    @Override
    public int getItemCount() {

        if(medicoList == null) {
            return 0;
        }
        return medicoList.size();
    }

    public void update(List<Medico> medicoList) {
        this.medicoList = medicoList;
        notifyDataSetChanged();
    }

    class ViewHolderMedicos extends RecyclerView.ViewHolder {

        private TextView textViewNomeMedico, textViewLocaldeAtendimento;
        private ImageView imageViewFotoMedico;
        public ViewHolderMedicos(@NonNull View itemView) {
            super(itemView);
            textViewNomeMedico = itemView.findViewById(R.id.textView_medico_lista_nome);
            textViewLocaldeAtendimento = itemView.findViewById(R.id.textView_cidade_atendimento);
            imageViewFotoMedico = itemView.findViewById(R.id.imageView_foto_medico);
        }
    }
}
