package com.appwood.mylibrarys;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_app);
    }

    public void Update(View view) {

        if (MyHelpers.Entery_UpdateApps == 1) {
            MyHelpers.LinkopenChromeCustomTabUrl(this, "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());

        } else if (MyHelpers.Entery_UpdateApps == 2) {
            MyHelpers.LinkopenChromeCustomTabUrl(this, MyHelpers.getOtherAppsShowLink());
        }
    }

    @Override
    public void onBackPressed() {

    }
}