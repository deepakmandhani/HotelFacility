package com.ex.hotelfacilityagent.activity;

import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ex.hotelfacilityagent.MyApplication;
import com.ex.hotelfacilityagent.adapter.FacilityListRecyclerViewAdapter;
import com.ex.hotelfacilityagent.model.Exclusion;
import com.ex.hotelfacilityagent.model.FacilityApiResponse;
import com.ex.hotelfacilityagent.mvp.presenter.FacilityPresenter;
import com.ex.hotelfacilityagent.mvp.view.IFacilityView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import agent.ex.com.hotelfacilityagent.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FacilityActivity extends AppCompatActivity implements IFacilityView {

    public static final String FACILITY_OPTIONS_SELECTED = "FACILITY_OPTIONS_SELECTED";
    @Inject
    FacilityPresenter facilityPresenter;

    @BindView(R.id.continue_button)
    Button continueButton;

    @BindView(R.id.other_view)
    ImageView defaultView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.facility_recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private FacilityListRecyclerViewAdapter facilityListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        unbinder = ButterKnife.bind(this);
        initDI();
        facilityPresenter.setiFacilityView(this);
        if (isNetworkConeected()) {
            facilityPresenter.getFacilityData();
        } else {
            facilityPresenter.fetchResponseFromDB();
        }
    }

    private void initDI() {
        ((MyApplication) getApplication()).getAppComponent().inject(this);
    }

    private boolean isNetworkConeected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSoundDataItems(FacilityApiResponse dataList) {
        continueButton.setEnabled(true);
        continueButton.setVisibility(View.VISIBLE);
        defaultView.setVisibility(View.GONE);
        facilityListRecyclerViewAdapter = new FacilityListRecyclerViewAdapter(this, dataList.getFacilities(), facilityPresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(facilityListRecyclerViewAdapter);
        facilityPresenter.setFacilityApiResponse(dataList);

    }

    @Override
    public void showErrorView() {
        continueButton.setVisibility(View.GONE);
        defaultView.setVisibility(View.VISIBLE);
        defaultView.setImageResource(R.drawable.ic_error);
    }

    @Override
    public void showDataUnavailableView() {
        continueButton.setVisibility(View.GONE);
        defaultView.setVisibility(View.VISIBLE);
        defaultView.setImageResource(R.drawable.no_data);
    }

    @OnClick(R.id.continue_button)
    public void onContinueButtonClicked() {
        if (facilityPresenter.isValidExclusionCombinations()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putStringArrayListExtra(FACILITY_OPTIONS_SELECTED, facilityPresenter.getSelectedFacilityOptions());
            startActivity(intent);
        } else {
            Toast.makeText(this, "One of the facility selection is not compatible, Plz modify your selection!!", Toast.LENGTH_SHORT).show();
        }
    }
}
