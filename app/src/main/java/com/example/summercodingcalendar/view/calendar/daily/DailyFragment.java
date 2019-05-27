package com.example.summercodingcalendar.view.calendar.daily;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.FragmentDailyBinding;
import com.example.summercodingcalendar.util.Converter;
import com.example.summercodingcalendar.view.calendar.adapter.DailyScheduleListAdapter;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DailyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyFragment extends Fragment implements DailyContract.View {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private DailyPresenter mPresenter;
    private FragmentDailyBinding mBinding;
    private String title;
    private long selectedDate;

    public DailyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DailyFragment.
     */
    public static DailyFragment newInstance(String title, Date date) {
        DailyFragment fragment = new DailyFragment();
        Bundle args = new Bundle();
        long time = date.getTime();
        args.putString("title", title);
        args.putLong("selectedDate", time);
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
        mPresenter = new DailyPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily, container, false);
        mBinding.setAdapter(new DailyScheduleListAdapter());
        mBinding.recyclerView.setAdapter(mBinding.getAdapter());
        mPresenter.setDailyScheduleAdapterModel(mBinding.getAdapter());
        mPresenter.setDailyScheduleAdapterView(mBinding.getAdapter());
        mPresenter.loadDailySchedule(Converter.longToDate(selectedDate));
        return mBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDateChanged(Date date) {
        selectedDate = date.getTime();
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

}
