package com.example.fitappmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitappmobile.R;
import com.example.fitappmobile.java.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientHolder> {

    private List<Client> clients = new ArrayList<>();

    @NonNull
    @Override
    public ClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        return new ClientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHolder holder, int position) {
        Client current = clients.get(position);
        holder.textViewClientName.setText(current.toString());
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
        notifyDataSetChanged();
    }

    static class ClientHolder extends RecyclerView.ViewHolder {
        private TextView textViewClientName;

        ClientHolder(@NonNull View itemView) {
            super(itemView);
            textViewClientName = itemView.findViewById(R.id.text_view_client_name);
        }
    }

}
