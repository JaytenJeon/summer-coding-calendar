package com.example.summercodingcalendar.view.calendar.weekly;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.databinding.FragmentWeeklyBinding;
import com.example.summercodingcalendar.util.CalendarHelper;
import com.example.summercodingcalendar.view.calendar.adapter.DailyScheduleListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeeklyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeeklyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeeklyFragment extends Fragment implements WeeklyContract.View{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private CalendarHelper mCalendarHelper = CalendarHelper.getInstance();
    private OnFragmentInteractionListener mListener;
    private FragmentWeeklyBinding mBinding;
    private String title;

    public WeeklyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WeeklyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeeklyFragment newInstance(String title) {
        WeeklyFragment fragment = new WeeklyFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weekly, container, false);
        setView();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setView();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDateChanged(Date date) {

        mCalendarHelper.setDate(date);
    }

    @Override
    public void setView() {
        mBinding.setDate(mCalendarHelper.getCurrentDate());
        mBinding.setPresenter(new WeeklyPresenter(this));
        mBinding.setAdapter(new DailyScheduleListAdapter());
        mBinding.recyclerView.setAdapter(mBinding.getAdapter());
        mBinding.getPresenter().setWeeklyScheduleAdapterModel(mBinding.getAdapter());
        mBinding.getPresenter().setWeeklyScheduleAdapterView(mBinding.getAdapter());
        mBinding.getPresenter().loadWeeklySchedule();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
