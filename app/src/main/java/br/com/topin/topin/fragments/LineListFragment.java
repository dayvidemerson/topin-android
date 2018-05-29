package br.com.topin.topin.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.activities.MapActivity;
import br.com.topin.topin.adapters.recycler.LineAdapter;
import br.com.topin.topin.models.Line;
import br.com.topin.topin.services.LineService;
import br.com.topin.topin.util.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LineListFragment extends BaseFragment {
    private List<Line> mLines;
    private LineAdapter mLineAdapter;
    private String mCity;
    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        mCity = mSharedPreferences.getString(getString(R.string.city), null);
        String mState = mSharedPreferences.getString(getString(R.string.state), null);
        getActivity().setTitle("Linhas de Ônibus de "+ capitalize(mCity));
    }


    private String capitalize(String string){
        String cap = string.substring(0, 1).toUpperCase() + string.substring(1);
        return cap;
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
        mLineAdapter = new LineAdapter(this, mLines);
        recyclerView.setAdapter(mLineAdapter);
    }

    private void loadLines() {
        LineService service = Api.getRetrofit().create(LineService.class);
        openProgress();
        service.filter(mCity).enqueue(callbackLines);
    }

    public void onItemClicked(int position) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(getString(R.string.line), mLines.get(position).getSlug());
        editor.apply();
        Intent intent = new Intent(getActivity(), MapActivity.class);
        intent.putExtra(getString(R.string.line_full), mLines.get(position));
        startActivity(intent);

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
                mLineAdapter.setLines(mLines);
            }
            closeProgress();
        }

        @Override
        public void onFailure(@NonNull Call<List<Line>> call, @NonNull Throwable t) {
            closeProgress();
        }
    };
}
