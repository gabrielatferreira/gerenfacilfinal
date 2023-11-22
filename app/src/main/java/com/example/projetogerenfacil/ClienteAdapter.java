package com.example.projetogerenfacil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.util.Log;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private List<Cliente> listaDeClientes;

    public ClienteAdapter(List<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_clientes, parent, false);
        return new ClienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = listaDeClientes.get(position);
        holder.nomeTextView.setText(cliente.getNome());
        holder.cpfTextView.setText(cliente.getCpf());
        holder.enderecoTextView.setText(cliente.getEndereco());
        holder.complementoTextView.setText(cliente.getComplemento());
    }

    @Override
    public int getItemCount() {
        return listaDeClientes.size();
    }

    class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView, cpfTextView, enderecoTextView, complementoTextView;

        ClienteViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            cpfTextView = itemView.findViewById(R.id.cpfTextView);
            enderecoTextView = itemView.findViewById(R.id.enderecoTextView);
            complementoTextView = itemView.findViewById(R.id.complementoTextView);
        }
    }
}
