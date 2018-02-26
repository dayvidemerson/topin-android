package br.com.topin.topin.activities;

import android.os.Bundle;

import br.com.topin.topin.R;
import br.com.topin.topin.fragments.CitySelectFragment;
import br.com.topin.topin.fragments.LineListFragment;
import br.com.topin.topin.fragments.StateSelectFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFragment(new StateSelectFragment());
    }
}
