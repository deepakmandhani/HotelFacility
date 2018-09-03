package com.ex.hotelfacilityagent.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ex.hotelfacilityagent.MyApplication;

import org.w3c.dom.Text;

import java.util.ArrayList;

import agent.ex.com.hotelfacilityagent.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.facility_selection_text)
    TextView facilityDetails;

    @BindView(R.id.welcome_text)
    TextView welcomeText;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initDI();
        getIntentDataAndDisplayUI();
    }

    private void initDI() {
        ((MyApplication) getApplication()).getAppComponent().inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void getIntentDataAndDisplayUI() {
        if (getIntent() != null) {
            ArrayList<String> arrayList = getIntent().getStringArrayListExtra(FacilityActivity.FACILITY_OPTIONS_SELECTED);
            String facilityAll = "";
            if (arrayList == null || arrayList.size() == 0) {
                facilityAll = "You haven't choosen any of the facility options.";
                welcomeText.setVisibility(View.GONE);
            } else {
                for (String facility : arrayList
                        ) {
                    facilityAll += facility + "\n";
                }
            }
            facilityDetails.setText(facilityAll);
        }
    }
}
