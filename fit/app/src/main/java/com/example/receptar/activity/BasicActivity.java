package com.example.receptar.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.receptar.R;
import com.example.receptar.viewmodel.BasicViewModel;

public abstract class BasicActivity<BVM extends BasicViewModel> extends AppCompatActivity {

    protected BVM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
    }

}
