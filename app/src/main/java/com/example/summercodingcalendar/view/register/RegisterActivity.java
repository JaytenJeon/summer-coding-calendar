package com.example.summercodingcalendar.view.register;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{
    ActivityRegisterBinding mBinding;
    RegisterPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mPresenter = new RegisterPresenter(this);
    }
}
