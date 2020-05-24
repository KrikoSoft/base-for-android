package com.example.receptar.activity;

import androidx.fragment.app.Fragment;

import com.example.receptar.viewmodel.BasicViewModel;

public abstract class BasicFragment<BVM extends BasicViewModel> extends Fragment {

    protected BVM viewModel;

}
