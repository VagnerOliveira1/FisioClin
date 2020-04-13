package com.rightside.fisioclin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;
import com.rightside.fisioclin.controller.ComprarProdutoController;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Produto;

import java.util.List;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ViewHolderProdutos> {
    private List<Produto> produtosList;
    private Medico medico;
    private Context context;
    private FragmentActivity fragmentActivity;

    public ProdutosAdapter(List<Produto> produtosList, Context context, FragmentActivity fragmentActivity) {
        this.produtosList = produtosList;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public ViewHolderProdutos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_produtos, parent, false);
        return new ViewHolderProdutos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProdutos holder, int position) {


        holder.textViewProduto.setText(produtosList.get(position).getNome());
        holder.textViewProdutoPreco.setText(String.valueOf(produtosList.get(position).getPreco()) + " FisioPoints");
        holder.textViewProdutoPreco.setTextColor(Color.RED);

        if(produtosList.get(position).getTipo() == 1) {
            holder.imageViewProdutoItem.setImageResource(R.drawable.ic_notifications);
        } else {
            holder.imageViewProdutoItem.setImageResource(R.drawable.relatorio);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(medico.getFisioPoints().getPoints() >= produtosList.get(position).getPreco()) {
                    ComprarProdutoController.alertaComprarProduto(fragmentActivity, medico, produtosList.get(position));
                } else {
                    Toast.makeText(context, "Não possui grana", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtosList.size();
    }

    public void updateMedico(Medico medico) {
        this.medico = medico;
    }
    class ViewHolderProdutos extends RecyclerView.ViewHolder {

        private TextView textViewProduto, textViewProdutoPreco;
        private ImageView imageViewProdutoItem;

        public ViewHolderProdutos(@NonNull View itemView) {
            super(itemView);
            textViewProduto = itemView.findViewById(R.id.textViewProduto);
            textViewProdutoPreco = itemView.findViewById(R.id.textViewpreco);
            imageViewProdutoItem = itemView.findViewById(R.id.imageViewProdutoItem);
        }
    }
}
