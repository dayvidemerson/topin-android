package br.com.topin.topin.adapters.recycler;

import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.models.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List<City> mCities;
    private int mLastSelectedPosition = -1;

    public CityAdapter(List<City> cities) {
        this.mCities = cities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_region, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTxtName().setText(mCities.get(position).getName());
        holder.getRadioRegion().setChecked(mLastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return mCities != null ? mCities.size() : 0;
    }

    public City getItemSelected() {
        return mLastSelectedPosition == -1 ? null : mCities.get(mLastSelectedPosition);
    }

    public void setCities(List<City> mCities) {
        this.mCities = mCities;
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
                    mLastSelectedPosition = getAdapterPosition();
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
            mLastSelectedPosition = getAdapterPosition();
            notifyDataSetChanged();
        }
    }
}
