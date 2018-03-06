package br.com.topin.topin.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.topin.topin.R;
import br.com.topin.topin.adapters.recycler.ScheduleAdapter;
import br.com.topin.topin.models.Schedule;
import br.com.topin.topin.services.ScheduleService;
import br.com.topin.topin.util.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleListFragment extends BaseFragment {
    private List<Schedule> mSchedules;
    private ScheduleAdapter mScheduleAdapter;
    private String mLine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        mLine = sharedPreferences.getString(getString(R.string.line), null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        TabLayout tabLayout = v.findViewById(R.id.tab_week);
        tabLayout.addOnTabSelectedListener(tabWeek);
        setupRecycler(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadLines("sunday");
    }

    private void setupRecycler(View view) {
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_schedule);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mScheduleAdapter = new ScheduleAdapter(mSchedules);
        recyclerView.setAdapter(mScheduleAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void loadLines(String weekday) {
        ScheduleService service = Api.getRetrofit().create(ScheduleService.class);
        openProgress();
        weekday = "{" + weekday + "}";
        service.filter(mLine, weekday).enqueue(callbackSchedules);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeProgress();
    }

    private Callback<List<Schedule>> callbackSchedules = new Callback<List<Schedule>>() {

        @Override
        public void onResponse(@NonNull Call<List<Schedule>> call, @NonNull Response<List<Schedule>> response) {
            if (response.isSuccessful()) {
                mSchedules = response.body();
                mScheduleAdapter.setSchedules(mSchedules);
            }
            closeProgress();
        }

        @Override
        public void onFailure(@NonNull Call<List<Schedule>> call, @NonNull Throwable t) {
            closeProgress();
        }
    };

    private TabLayout.OnTabSelectedListener tabWeek = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    loadLines("sunday");
                    break;
                case 1:
                    loadLines("monday");
                    break;
                case 2:
                    loadLines("tuesday");
                    break;
                case 3:
                    loadLines("wednesday");
                    break;
                case 4:
                    loadLines("thursday");
                    break;
                case 5:
                    loadLines("friday");
                    break;
                case 6:
                    loadLines("saturday");
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}
