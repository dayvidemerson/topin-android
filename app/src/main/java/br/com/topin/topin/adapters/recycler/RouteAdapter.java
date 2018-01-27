package br.com.topin.topin.adapters.recycler;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.activities.MapActivity;
import br.com.topin.topin.models.Line;
import br.com.topin.topin.models.Route;
import br.com.topin.topin.services.LineService;
import br.com.topin.topin.util.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {
    private List<Route> mRoutes;
    private int lastSelectedPosition = -1;
    private MapActivity mMapActivity;

    public RouteAdapter(Activity activity, List<Route> routes) {
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

    public void setRoutes(List<Route> mRoutes) {
        this.mRoutes = mRoutes;
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
            Route route = mRoutes.get(getAdapterPosition());

            LineService service = Api.getRetrofit().create(LineService.class);

            service.get(route.getFront()).enqueue(callbackLine);
        }

        private Callback<Line> callbackLine = new Callback<Line>() {
            @Override
            public void onResponse(Call<Line> call, Response<Line> response) {
                mMapActivity.setLine(response.body());
            }

            @Override
            public void onFailure(Call<Line> call, Throwable t) {
                t.printStackTrace();
            }
        };
    }
}
