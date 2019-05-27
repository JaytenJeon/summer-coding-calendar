package com.example.summercodingcalendar.view.calendar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.summercodingcalendar.R;
import com.example.summercodingcalendar.data.Schedule;
import com.example.summercodingcalendar.databinding.ItemDailyScheduleBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DailyScheduleListAdapter extends RecyclerView.Adapter<DailyScheduleListAdapter.DailyScheduleViewHolder> implements ScheduleAdapterContract.Model, ScheduleAdapterContract.View {
    private List<Schedule> mScheduleList = new ArrayList<>();

    @NonNull
    @Override
    public DailyScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDailyScheduleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_daily_schedule, parent, false);
        return new DailyScheduleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyScheduleViewHolder holder, int position) {
        holder.onBind(mScheduleList.get(position));
    }

    @Override
    public int getItemCount() {
        return mScheduleList.size();
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void addItems(List<Schedule> schedules) {
        mScheduleList.addAll(schedules);
    }


    public class DailyScheduleViewHolder extends RecyclerView.ViewHolder {
        ItemDailyScheduleBinding mBinding;
        public DailyScheduleViewHolder(ItemDailyScheduleBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
        void onBind(Schedule schedule){
            mBinding.setSchedule(schedule);
        }
    }
}
