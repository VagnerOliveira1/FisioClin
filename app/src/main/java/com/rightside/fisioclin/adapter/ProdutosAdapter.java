package com.rightside.fisioclin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightside.fisioclin.R;

import java.util.List;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ViewHolderProdutos> {
    private List<String> produtosList;
    public ProdutosAdapter(List<String> produtosList) {
        this.produtosList = produtosList;
    }

    @NonNull
    @Override
    public ViewHolderProdutos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_produtos, parent, false);
        return new ViewHolderProdutos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProdutos holder, int position) {
        holder.textViewProduto.setText(produtosList.get(position));
    }

    @Override
    public int getItemCount() {
        return produtosList.size();
    }

    class ViewHolderProdutos extends RecyclerView.ViewHolder {

        private TextView textViewProduto;

        public ViewHolderProdutos(@NonNull View itemView) {
            super(itemView);
            textViewProduto = itemView.findViewById(R.id.textViewProduto);
        }
    }
}
