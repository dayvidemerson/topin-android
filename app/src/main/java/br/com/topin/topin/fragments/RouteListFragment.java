package br.com.topin.topin.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.activities.MapActivity;
import br.com.topin.topin.adapters.recycler.RouteAdapter;
import br.com.topin.topin.models.Line;
import br.com.topin.topin.services.LineService;
import br.com.topin.topin.util.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteListFragment extends BaseFragment {
    private List<Line> mLines;
    private RouteAdapter mRouteAdapter;
    private String mCity;

    private MapActivity mMapActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        mCity = sharedPreferences.getString(getString(R.string.city), null);
        mMapActivity = (MapActivity) getActivity();
        
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_route, container, false);
        setupRecycler(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadLines();
    }

    private void setupRecycler(View view) {
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_route);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mRouteAdapter = new RouteAdapter(mMapActivity, mLines);
        recyclerView.setAdapter(mRouteAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }
    private void loadLines() {
        LineService service = Api.getRetrofit().create(LineService.class);
        openProgress();
        service.filter(mCity).enqueue(callbackLines);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        closeProgress();
    }

    private Callback<List<Line>> callbackLines = new Callback<List<Line>>() {

        @Override
        public void onResponse(@NonNull Call<List<Line>> call, @NonNull Response<List<Line>> response) {
            if (response.isSuccessful()) {
                mLines = response.body();
                mRouteAdapter.setLines(mLines);
            }
            closeProgress();
        }

        @Override
        public void onFailure(@NonNull Call<List<Line>> call, @NonNull Throwable t) {
            closeProgress();
        }
    };
}
