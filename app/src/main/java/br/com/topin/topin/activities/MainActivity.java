package br.com.topin.topin.activities;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ProgressBar;

import br.com.topin.topin.R;
import br.com.topin.topin.fragments.StateSelectFragment;

public class MainActivity extends AppCompatActivity {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFragment(new StateSelectFragment());
    }

    public void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameMain, fragment);
        fragmentTransaction.commit();
    }

    public void openProgress() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        final ProgressBar progressBar = ProgressBar.class.cast(dialog.findViewById(R.id.progressBar));
        dialog.show();
    }

    public void closeProgress() {
        dialog.dismiss();
    }
}
