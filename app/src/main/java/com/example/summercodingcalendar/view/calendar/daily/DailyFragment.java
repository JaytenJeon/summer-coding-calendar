package com.example.summercodingcalendar.view.calendar.daily;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.FragmentDailyBinding;
import com.example.summercodingcalendar.util.CalendarHelper;
import com.example.summercodingcalendar.util.Converter;
import com.example.summercodingcalendar.view.calendar.adapter.DailyScheduleListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DailyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyFragment extends Fragment implements DailyContract.View{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private FragmentDailyBinding mBinding;
    private String title;
    private CalendarHelper mCalendarHelper = CalendarHelper.getInstance();
    public DailyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DailyFragment.
     */
    public static DailyFragment newInstance(String title) {
        DailyFragment fragment = new DailyFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DAILY", "onCreate");

        if (getArguments() != null) {
            this.title = getArguments().getString("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("DAILY", "onCreateView");

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily, container, false);
        setView();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("DAILY", "onResume");

        setView();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("DAILY", "onAttach");


    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("DAILY", "onDetach");

    }

    @Override
    public void setView() {
        Log.d("DAILY", " - currentDate:" + mCalendarHelper.getCurrentDate());

        mBinding.setDate(mCalendarHelper.getCurrentDate());
        mBinding.setAdapter(new DailyScheduleListAdapter());
        mBinding.recyclerView.setAdapter(mBinding.getAdapter());
        mBinding.setPresenter(new DailyPresenter(this));
        mBinding.getPresenter().setDailyScheduleAdapterModel(mBinding.getAdapter());
        mBinding.getPresenter().setDailyScheduleAdapterView(mBinding.getAdapter());
        mBinding.getPresenter().loadDailySchedule(mBinding.getDate());
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mBinding.getPresenter().removeItem(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(mBinding.recyclerView);
    }

    @Override
    public void onDateChanged(Date date) {
        mCalendarHelper.setDate(date);
    }

    @Override
    public void showUndoSnackbar() {
        Snackbar.make(mBinding.getRoot(), "아이템 제거", Snackbar.LENGTH_LONG )
                .setAction("취소", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBinding.getPresenter().undoRemoveItem();
                    }
                })
                .show();
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
