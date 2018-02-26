package br.com.topin.topin.fragments;

import android.app.Fragment;

import br.com.topin.topin.activities.BaseActivity;

public class BaseFragment extends Fragment {

    public void startFragment(BaseFragment fragment) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).startFragment(fragment);
        }
    }

    public void openProgress() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).openProgress();
        }
    }

    public void closeProgress() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).closeProgress();
        }
    }
}
