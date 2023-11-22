package com.example.projetogerenfacil;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    private List<Produto> listaDeProdutos;

    public ProdutoAdapter(List<Produto> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = listaDeProdutos.get(position);
        holder.nomeTextView.setText(produto.getNome());
        holder.categoriaTextView.setText(produto.getCategoria());
        holder.precoTextView.setText("R$" + produto.getPreco()); // Adiciona o cifrão "R$" ao preço
        holder.estoqueTextView.setText(String.valueOf(produto.getEstoque()));
        holder.promocoesTextView.setText(produto.getPromocoes());
        holder.descricaoTextView.setText(produto.getDescricao());
    }

    @Override
    public int getItemCount() {
        return listaDeProdutos.size();
    }

    class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView, categoriaTextView, precoTextView, estoqueTextView, promocoesTextView, descricaoTextView;

        ProdutoViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            categoriaTextView = itemView.findViewById(R.id.categoriaTextView);
            precoTextView = itemView.findViewById(R.id.precoTextView);
            estoqueTextView = itemView.findViewById(R.id.estoqueTextView);
            promocoesTextView = itemView.findViewById(R.id.promocoesTextView);
            descricaoTextView = itemView.findViewById(R.id.descricaoTextView);
        }
    }
}
