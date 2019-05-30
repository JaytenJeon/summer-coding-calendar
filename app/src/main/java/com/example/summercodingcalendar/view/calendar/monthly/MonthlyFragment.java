package com.example.summercodingcalendar.view.calendar.monthly;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.FragmentMonthlyBinding;
import com.example.summercodingcalendar.util.CalendarHelper;
import com.example.summercodingcalendar.util.Converter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCalendarViewDateSelectedListener} interface
 * to handle interaction events.
 * Use the {@link MonthlyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthlyFragment extends Fragment implements MonthlyContract.View{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private CalendarHelper mCalendarHelper = CalendarHelper.getInstance();
    private OnCalendarViewDateSelectedListener mListener;
    private MonthlyPresenter mPresenter;
    private FragmentMonthlyBinding mBinding;
    private String title;
    public MonthlyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MonthlyFragment.
     */
    public static MonthlyFragment newInstance(String title) {
        MonthlyFragment fragment = new MonthlyFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.title = getArguments().getString("title");
        }
        Log.d("MONTHLY", "onCreate");

        mPresenter = new MonthlyPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("MONTHLY", "onCreateView");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_monthly, container, false);
        setView();
        mBinding.getRoot().refreshDrawableState();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MONTHLY", "onResume");
        setView();
    }


    @Override
    public void onAttach(Context context) {
        Log.d("MONTHLY", "onAttach");

        super.onAttach(context);
        if (context instanceof OnCalendarViewDateSelectedListener) {
            mListener = (OnCalendarViewDateSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCalendarViewDateSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("MONTHLY", "onDetach");

        mListener = null;
    }

    @Override
    public void setView() {
        mBinding.setPresenter(new MonthlyPresenter(this));
        mBinding.getPresenter().onCreate();
    }

    @Override
    public void changeDay(Date date) {
        if (mListener != null) {
            mListener.onDateSelected(date);
        }
    }

    @Override
    public void setMaterialCalendar(List<CalendarDay> events) {
        mBinding.materialCalendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay calendarDay) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy년  LLL", new Locale("ko"));
                return calendarDay.getDate().format(dateFormat);
            }
        });
        mBinding.materialCalendarView.setCurrentDate(mCalendarHelper.getCurrentCalendarDay());
        mBinding.materialCalendarView.setSelectedDate(mCalendarHelper.getCurrentCalendarDay());
        mBinding.materialCalendarView.requestLayout();
        Log.d("MONTHLY", " - selectedDate: " + mBinding.materialCalendarView.getSelectedDate() );
        Log.d("MONTHLY", " - events Size:" + events.size());
        mBinding.materialCalendarView.removeDecorators();
        mBinding.materialCalendarView.addDecorator(new EventDecorator(Color.RED, events));
        mBinding.materialCalendarView.setOnDateChangedListener(mBinding.getPresenter());


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCalendarViewDateSelectedListener {
        // TODO: Update argument type and name
        void onDateSelected(Date date);
    }
}
