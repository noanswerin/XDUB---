package com.example.myweather.ui.adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myweather.databinding.ItemHourlyRvBinding;
import com.example.myweather.db.bean.HourlyResponse;
import com.example.myweather.utils.EasyDate;
import com.example.myweather.utils.WeatherUtil;

import java.util.List;
public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {

    private final List<HourlyResponse.HourlyBean> hourlyBeans;

    public HourlyAdapter(List<HourlyResponse.HourlyBean> dailyBeans) {
        this.hourlyBeans = dailyBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHourlyRvBinding binding = ItemHourlyRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HourlyResponse.HourlyBean hourlyBean = hourlyBeans.get(position);
        String time = EasyDate.updateTime(hourlyBean.getFxTime());
        holder.binding.tvTime.setText(String.format("%s%s", EasyDate.showTimeInfo(time), time));
        WeatherUtil.changeIcon(holder.binding.ivStatus, Integer.parseInt(hourlyBean.getIcon()));
        holder.binding.tvTemperature.setText(String.format("%s℃", hourlyBean.getTemp()));
    }

    @Override
    public int getItemCount() {
        return hourlyBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemHourlyRvBinding binding;

        public ViewHolder(@NonNull ItemHourlyRvBinding itemHourlyRvBinding) {
            super(itemHourlyRvBinding.getRoot());
            binding = itemHourlyRvBinding;
        }
    }
}

