package com.example.fitappmobile.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitappmobile.adapter.ClientAdapter;
import com.example.fitappmobile.R;
import com.example.fitappmobile.java.Client;
import com.example.fitappmobile.viewmodel.ClientViewModel;

import java.util.List;

public class ClientsActivity extends AppCompatActivity {

    private ClientViewModel clientViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        RecyclerView recyclerView = findViewById(R.id.clients_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ClientAdapter adapter = new ClientAdapter();
        recyclerView.setAdapter(adapter);

        clientViewModel = ViewModelProviders.of(this).get(ClientViewModel.class);
        clientViewModel.getClients().observe(this, new Observer<List<Client>>() {
            @Override
            public void onChanged(List<Client> clients) {
                adapter.setClients(clients);
            }
        });
    }

}
