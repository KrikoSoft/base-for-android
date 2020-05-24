package com.example.receptar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.receptar.R;
import com.example.receptar.java.LoginData;
import com.example.receptar.java.User;
import com.example.receptar.viewmodel.MainViewModel;

public class MainActivity extends BasicActivity<MainViewModel> {

    public static final String EXTRA_USER_NAME = "EXTRA_USER_NAME";
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        usernameEditText = findViewById(R.id.main_text_edit_username);
        passwordEditText = findViewById(R.id.main_textedit_password);
        Button loginButton = findViewById(R.id.main_button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        Button registerButton = findViewById(R.id.main_button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void login() {
        User user = viewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        if (user == null) {
            Toast.makeText(getApplicationContext(), "Problém s prihlásením", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Prihlásenie úspešné", Toast.LENGTH_LONG).show();
            LoginData.setLoggedUser(user);

            startActivity(new Intent(getApplicationContext(), InsideActivity.class));
        }
    }

    private void register() {
        User user = viewModel.register(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        if (user == null) {
            Toast.makeText(getApplicationContext(), "Problém s registráciou", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Registrácia úspešná!", Toast.LENGTH_LONG).show();
            LoginData.setLoggedUser(user);
            startActivity(new Intent(getApplicationContext(), InsideActivity.class));
        }
    }
}
