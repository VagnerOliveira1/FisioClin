package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.HorarioPacienteActivity;
import com.rightside.fisioclin.R;
import com.rightside.fisioclin.fragment.ComoChegarFragment;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.utils.GeralUtils;

import org.w3c.dom.Text;

import java.util.List;

public class MedicosAdapter extends RecyclerView.Adapter<MedicosAdapter.ViewHolderMedicos> {
    private  Context context;
    private List<Medico> medicoList;
    private User user;
    private FragmentActivity fragmentActivity;

    public MedicosAdapter(Context context, User user, FragmentActivity fragmentActivity) {
        this.context = context;
        this.user = user;
        this.fragmentActivity = fragmentActivity;
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
        holder.textViewLocaldeAtendimento.setText(medico.getClinica().getEndereco().getCidade() + " " + medico.getClinica().getEndereco().getEstado());
        holder.textViewNomeMedico.setText(medico.getName());
        holder.textViewNomeClinica.setText(medico.getClinica().getNome());
        GeralUtils.mostraImagemCircular(context, holder.imageViewFotoMedico, medico.getProfilePictureUrl());
        holder.buttonComoChegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComoChegarFragment.comoChegarFragment(medico.getClinica().getEndereco()).setContext(context).show(fragmentActivity.getSupportFragmentManager(), "comochegar");

            }
        });
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, HorarioPacienteActivity.class);
            intent.putExtra("usuario", user);
            intent.putExtra("medico", medico);
            context.startActivity(intent);
        });
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

        private TextView textViewNomeMedico, textViewLocaldeAtendimento, textViewNomeClinica;
        private Button buttonComoChegar;
        private ImageView imageViewFotoMedico;
        public ViewHolderMedicos(@NonNull View itemView) {
            super(itemView);
            buttonComoChegar = itemView.findViewById(R.id.buttonComoChegar);
            textViewNomeMedico = itemView.findViewById(R.id.textView_medico_lista_nome);
            textViewLocaldeAtendimento = itemView.findViewById(R.id.textView_cidade_atendimento);
            textViewNomeClinica = itemView.findViewById(R.id.textView_nome_clinica);
            imageViewFotoMedico = itemView.findViewById(R.id.imageView_foto_medico);
        }
    }
}
