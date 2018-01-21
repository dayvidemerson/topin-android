package br.com.topin.topin.adapters.recycler;

import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.models.State;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    private List<State> states;
    private int lastSelectedPosition = -1;

    public StateAdapter(List<State> states) {
        this.states = states;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.region_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTxtName().setText(states.get(position).getName());
        holder.getRadioRegion().setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return states != null ? states.size() : 0;
    }

    public State getItemSelected() {
        return lastSelectedPosition == -1 ? null : states.get(lastSelectedPosition);
    }

    public void setStates(List<State> states) {
        this.states = states;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtName;
        private AppCompatRadioButton radioRegion;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txtName = itemView.findViewById(R.id.txtRegionLineName);
            radioRegion = itemView.findViewById(R.id.radioRegion);

            radioRegion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }

            });
        }

        TextView getTxtName() {
            return txtName;
        }

        AppCompatRadioButton getRadioRegion() {
            return radioRegion;
        }

        @Override
        public void onClick(View view) {
            lastSelectedPosition = getAdapterPosition();
            notifyDataSetChanged();
        }
    }
}
