package br.com.topin.topin.adapters.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.models.Marker;

public class MarkerAdapter extends RecyclerView.Adapter<MarkerAdapter.ViewHolder> {
    private List<Marker> mMarkers;

    public MarkerAdapter(List<Marker> markers) {
        this.mMarkers = markers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marker, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTxtName().setText(mMarkers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mMarkers != null ? mMarkers.size() : 0;
    }

    public void setMarkers(List<Marker> mMarkers) {
        this.mMarkers = mMarkers;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_marker_name);
        }

        TextView getTxtName() {
            return txtName;
        }
    }
}
