package com.example.summercodingcalendar.view.calendar.monthly;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.FragmentMonthlyBinding;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
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

    private OnCalendarViewDateSelectedListener mListener;
    private MonthlyPresenter mPresenter;
    private FragmentMonthlyBinding mBinding;
    private String title;
    private long selectedDate;
    public MonthlyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MonthlyFragment.
     */
    public static MonthlyFragment newInstance(String title, Date date) {
        MonthlyFragment fragment = new MonthlyFragment();
        Bundle args = new Bundle();
        long selectedDate = date.getTime();
        args.putString("title", title);
        args.putLong("selectedDate", selectedDate);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.title = getArguments().getString("title");
            this.selectedDate = getArguments().getLong("selectedDate");
        }
        mPresenter = new MonthlyPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_monthly, container, false);
        setView();
        return mBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
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
        mListener = null;
    }

    @Override
    public void setView() {
        mBinding.setPresenter(new MonthlyPresenter(this));

        mBinding.materialCalendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay calendarDay) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy년  LLL", new Locale("ko"));
                return calendarDay.getDate().format(dateFormat);
            }
        });
        mBinding.materialCalendarView.setDateSelected(CalendarDay.from(Instant.ofEpochMilli(selectedDate).atZone(ZoneId.systemDefault()).toLocalDate()), true);
        ArrayList<CalendarDay> days = new ArrayList<>();
        days.add(mBinding.materialCalendarView.getCurrentDate());
        mBinding.materialCalendarView.addDecorator(new EventDecorator(Color.RED, days));
        mBinding.materialCalendarView.setOnDateChangedListener(mBinding.getPresenter());
    }

    @Override
    public void changeDay(Date date) {
        if (mListener != null) {
            mListener.onDateSelected(date);
        }
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
