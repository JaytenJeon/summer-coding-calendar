package com.example.summercodingcalendar.view.register;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.ActivityRegisterBinding;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{
    private ActivityRegisterBinding mBinding;
    private Calendar mCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mBinding.setPresenter(new RegisterPresenter(this));
        mBinding.setDate(Calendar.getInstance().getTime());
    }

    @Override
    public boolean validateData() {
        boolean isNotError = true;
        if(mBinding.getDate() == null){
            mBinding.textInputLayoutDate.setError(getString(R.string.error_date));
            isNotError &= false;
        }else{
            mBinding.textInputLayoutDate.setErrorEnabled(false);
            isNotError &= true;
        }
        if(mBinding.getTitle()==null || mBinding.getTitle().trim().isEmpty()){
            mBinding.textInputLayoutTitle.setError(getString(R.string.error_title));
            isNotError &= false;
        }else{
            mBinding.textInputLayoutTitle.setErrorEnabled(false);
            isNotError &= true;

        }

        return isNotError;
    }

    @Override
    public void showDatePickerDialog() {
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,R.style.AppTheme_Calendar,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(year, month, dayOfMonth);
                        mBinding.setDate(mCalendar.getTime());
                    }
                },year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void showTempToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

}
