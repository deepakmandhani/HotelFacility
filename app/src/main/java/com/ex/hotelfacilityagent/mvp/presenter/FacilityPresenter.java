package com.ex.hotelfacilityagent.mvp.presenter;

import android.content.Intent;
import android.util.Log;

import com.ex.hotelfacilityagent.db.DatabaseService;
import com.ex.hotelfacilityagent.model.Exclusion;
import com.ex.hotelfacilityagent.model.Facility;
import com.ex.hotelfacilityagent.model.FacilityApiResponse;
import com.ex.hotelfacilityagent.model.FacilityApiResponse;
import com.ex.hotelfacilityagent.model.Option;
import com.ex.hotelfacilityagent.mvp.view.IFacilityView;
import com.ex.hotelfacilityagent.network.FacilityApiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.schedulers.Schedulers;

public class FacilityPresenter {

    private FacilityApiService facilityApiService;
    private IFacilityView iFacilityView;
    private DatabaseService databaseService;
    private List<Exclusion> selectedFacilityList = new ArrayList<>();
    private Map<String, String> selectedFacilityMap = new HashMap<>();
    private FacilityApiResponse facilityApiResponse;

    @Inject
    public FacilityPresenter(FacilityApiService facilityApiService) {
        this.facilityApiService = facilityApiService;
        databaseService = DatabaseService.getInstance();
    }

    public void setiFacilityView(IFacilityView iFacilityView) {
        this.iFacilityView = iFacilityView;
    }

    public void setFacilityApiResponse(FacilityApiResponse facilityApiResponse) {
        this.facilityApiResponse = facilityApiResponse;
    }

    public void getFacilityData() {
        iFacilityView.showLoading();
        facilityApiService.getFacilityResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FacilityApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(FacilityApiResponse response) {
                        if (response != null && response.getFacilities() != null
                                && response.getFacilities().size() > 0) {
                            iFacilityView.showSoundDataItems(response);
                            saveResponseToDB(response);
                            return;
                        }
                        iFacilityView.showDataUnavailableView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iFacilityView.showErrorView();
                    }

                    @Override
                    public void onComplete() {
                        iFacilityView.hideLoading();
                    }
                });
    }

    private void saveResponseToDB(FacilityApiResponse facilityApiResponse) {
        databaseService.writeSoundData(facilityApiResponse);
    }

    public void fetchResponseFromDB() {
        iFacilityView.showLoading();
        Observable.just(databaseService.readSoundData())
                .subscribe(new Observer<FacilityApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(FacilityApiResponse value) {
                        if (value != null && !value.getFacilities().isEmpty()) {
                            iFacilityView.showSoundDataItems(value);
                            return;
                        }
                        iFacilityView.showDataUnavailableView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iFacilityView.showErrorView();
                    }

                    @Override
                    public void onComplete() {
                        iFacilityView.hideLoading();
                    }
                });
    }

    public void addOrUpdateSelectedFacilityToList(String facilityId, String optionId) {
        selectedFacilityMap.put(facilityId, optionId);
    }

    public boolean isValidExclusionCombinations() {
/*        Predicate<Exclusion> checkIfPresent = el -> selectedFacilityMap.containsKey(el.getFacilityId())
                    && selectedFacilityMap.get(el.getFacilityId()).equals(el.getOptionsId());
        return facilityApiResponse.getExclusions().stream()
                .filter(el -> el.stream()
                        .allMatch(_el -> checkIfPresent.test(_el))).findFirst().isPresent();*/
        boolean isAnExclusion = false;
        for (List<Exclusion> exclusions : facilityApiResponse.getExclusions()) {
            isAnExclusion = true;
            for (Exclusion ex : exclusions) {
                if (selectedFacilityMap.containsKey(ex.getFacilityId()) &&
                        selectedFacilityMap.get(ex.getFacilityId()).equals(ex.getOptionsId())) {
                } else {
                    isAnExclusion = false;
                    break;
                }
            }
            if (isAnExclusion)
                break;
        }
        return !isAnExclusion;
    }

    public ArrayList getSelectedFacilityOptions() {
        ArrayList<String> strings = new ArrayList<>();
        for (Facility facility : facilityApiResponse.getFacilities()) {
            String optionId = selectedFacilityMap.get(facility.getFacilityId());

            for (Option option : facility.getOptions()) {
                if (option.getId().equals(optionId)) {
                    strings.add(facility.getName() + " - " + option.getName());
                }
            }
        }
        return strings;
    }

}
