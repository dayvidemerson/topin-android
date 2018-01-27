package br.com.topin.topin.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.activities.MainActivity;
import br.com.topin.topin.activities.MapActivity;
import br.com.topin.topin.adapters.recycler.CityAdapter;
import br.com.topin.topin.models.City;
import br.com.topin.topin.services.CityService;
import br.com.topin.topin.util.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitySelectFragment extends Fragment {
    private List<City> mCities;
    private CityAdapter mCityAdapter;
    private String mState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        mState = sharedPreferences.getString(getString(R.string.state), null);
        String city = sharedPreferences.getString(getString(R.string.city), null);

        if (city != null) {
            Intent intent = new Intent(getActivity(), MapActivity.class);
            startActivity(intent);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_region_select, container, false);
        setupRecycler(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCities();
    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_region);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mCityAdapter = new CityAdapter(mCities);
        recyclerView.setAdapter(mCityAdapter);
    }

    private void loadCities() {
        CityService service = Api.getRetrofit().create(CityService.class);
        ((MainActivity) getActivity()).openProgress();
        service.filter(mState).enqueue(callbackCities);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                City city = mCityAdapter.getItemSelected();

                if (city != null) {
                    SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.city), city.getName());
                    editor.apply();
                    Intent intent = new Intent(getActivity(), MapActivity.class);
                    startActivity(intent);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Callback<List<City>> callbackCities = new Callback<List<City>>() {

        @Override
        public void onResponse(@NonNull Call<List<City>> call, @NonNull Response<List<City>> response) {
            if (response.isSuccessful()) {
                mCities = response.body();
                mCityAdapter.setCities(mCities);
                ((MainActivity) getActivity()).closeProgress();
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<City>> call, @NonNull Throwable t) { }
    };
}
