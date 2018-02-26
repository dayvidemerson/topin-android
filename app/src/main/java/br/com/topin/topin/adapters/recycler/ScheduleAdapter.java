package br.com.topin.topin.adapters.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.models.Schedule;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private List<Schedule> mSchedules;

    public ScheduleAdapter(List<Schedule> schedules) {
        this.mSchedules = schedules;
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        holder.getTxtTime().setText(mSchedules.get(position).getHour());
    }

    @Override
    public int getItemCount() {
        return mSchedules != null ? mSchedules.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTime;

        ViewHolder(View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.txt_time);
        }

        TextView getTxtTime() {
            return txtTime;
        }
    }

    public void setSchedules(List<Schedule> schedules) {
        this.mSchedules = schedules;
        notifyDataSetChanged();
    }
}
