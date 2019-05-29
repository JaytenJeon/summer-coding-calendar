package com.example.summercodingcalendar.view.register;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.ActivityRegisterBinding;
import com.example.summercodingcalendar.util.CalendarHelper;
import com.example.summercodingcalendar.util.Converter;
import com.example.summercodingcalendar.view.calendar.CalendarActivity;

import org.threeten.bp.LocalDate;

import java.sql.Date;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View{
    private ActivityRegisterBinding mBinding;
    private Calendar mCalendar = Calendar.getInstance();
    private CalendarHelper mCalendarHelper = CalendarHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mBinding.setPresenter(new RegisterPresenter(this));
        mBinding.setDate(mCalendarHelper.getCurrentDate());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean validateData() {
        return validateDate() && validateTitle();
    }

    @Override
    public boolean validateDate() {
        if(mBinding.getDate() == null || mBinding.getDate().before(Date.valueOf(LocalDate.now()+""))){
            mBinding.textInputLayoutDate.setError(getString(R.string.error_date_wrong));
            return  false;
        }
        mBinding.textInputLayoutDate.setErrorEnabled(false);
        return  true;
    }

    @Override
    public boolean validateTitle() {
        if(mBinding.getTitle()==null || mBinding.getTitle().trim().isEmpty()) {
            mBinding.textInputLayoutTitle.setError(getString(R.string.error_title));
            return false;
        }
        mBinding.textInputLayoutTitle.setErrorEnabled(false);
        return  true;

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
    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToDaily(java.util.Date date) {
        mCalendarHelper.setDate(date);
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        intent.putExtra("selectedTab",2);
        startActivity(intent);
        finish();
    }

}
