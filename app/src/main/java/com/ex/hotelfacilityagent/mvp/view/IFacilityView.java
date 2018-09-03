package com.ex.hotelfacilityagent.mvp.view;

import com.ex.hotelfacilityagent.model.FacilityApiResponse;

public interface IFacilityView {

    void showLoading();

    void hideLoading();

    void showSoundDataItems(FacilityApiResponse dataList);

    void showErrorView();

    void showDataUnavailableView();
}
