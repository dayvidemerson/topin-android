package br.com.topin.topin.adapters.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.activities.MapActivity;
import br.com.topin.topin.models.Line;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {
    private List<Line> mRoutes;
    private MapActivity mMapActivity;

    public RouteAdapter(Activity activity, List<Line> routes) {
        this.mMapActivity = (MapActivity) activity;
        this.mRoutes = routes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTxtName().setText(mRoutes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mRoutes != null ? mRoutes.size() : 0;
    }

    public void setLines(List<Line> routes) {
        this.mRoutes = routes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtName;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_route_name);
            itemView.setOnClickListener(this);
        }

        TextView getTxtName() {
            return txtName;
        }

        @Override
        public void onClick(View view) {
            Line route = mRoutes.get(getAdapterPosition());
            mMapActivity.setLine(route);

        }


    }
}
