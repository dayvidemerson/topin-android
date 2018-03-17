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
import br.com.topin.topin.adapters.recycler.MarkerAdapter;
import br.com.topin.topin.models.Marker;
import br.com.topin.topin.services.MarkerService;
import br.com.topin.topin.util.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkerListFragment extends BaseFragment {
    private List<Marker> mMarkers;
    private MarkerAdapter mMarkerAdapter;
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
        View v = inflater.inflate(R.layout.fragment_bottom_marker, container, false);
        setupRecycler(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadMarkers();
    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_marker);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mMarkerAdapter = new MarkerAdapter(mMarkers);
        recyclerView.setAdapter(mMarkerAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void loadMarkers() {
        MarkerService service = Api.getRetrofit().create(MarkerService.class);
        service.filter(mCity).enqueue(callbackMarkers);
    }

    private Callback<List<Marker>> callbackMarkers= new Callback<List<Marker>>() {

        @Override
        public void onResponse(@NonNull Call<List<Marker>> call, @NonNull Response<List<Marker>> response) {
            if (response.isSuccessful()) {
                mMarkers = response.body();
                mMarkerAdapter.setMarkers(mMarkers);
                mMapActivity.setMarkers(mMarkers);
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Marker>> call, @NonNull Throwable t) { }
    };
}
