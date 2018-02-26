package br.com.topin.topin.fragments;

import android.content.Context;
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
import br.com.topin.topin.adapters.recycler.StateAdapter;
import br.com.topin.topin.models.State;
import br.com.topin.topin.services.StateService;
import br.com.topin.topin.util.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateSelectFragment extends BaseFragment {
    private List<State> mStates;
    private StateAdapter mStateAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String city = sharedPreferences.getString(getString(R.string.city), null);
        String state = sharedPreferences.getString(getString(R.string.state), null);

        if (city != null) {
            startFragment(new LineListFragment());
        } else if (state != null) {
            startFragment(new CitySelectFragment());
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
        loadStates();
    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_region);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mStateAdapter = new StateAdapter(mStates);
        recyclerView.setAdapter(mStateAdapter);
    }

    private void loadStates() {
        StateService service = Api.getRetrofit().create(StateService.class);
        openProgress();
        service.all().enqueue(callbackStates);
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
                State state = mStateAdapter.getItemSelected();

                if (state != null) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.state), state.getSlug());
                    editor.apply();
                    startFragment(new CitySelectFragment());
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeProgress();
    }

    private Callback<List<State>> callbackStates = new Callback<List<State>>() {

        @Override
        public void onResponse(@NonNull Call<List<State>> call, @NonNull Response<List<State>> response) {
            if (response.isSuccessful()) {
                mStates = response.body();
                mStateAdapter.setStates(mStates);
            }
            closeProgress();
        }

        @Override
        public void onFailure(@NonNull Call<List<State>> call, @NonNull Throwable t) {
            closeProgress();
        }
    };
}
