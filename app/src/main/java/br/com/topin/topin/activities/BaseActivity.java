package br.com.topin.topin.activities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import br.com.topin.topin.R;

public class BaseActivity extends AppCompatActivity {
    private Dialog mDialog;

    public void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment);
        fragmentTransaction.commit();
    }

    public void openProgress() {
        mDialog = new Dialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_progress);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void closeProgress() {
        mDialog.dismiss();
    }

}
