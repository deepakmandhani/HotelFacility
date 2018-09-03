package com.ex.hotelfacilityagent.db;

import com.ex.hotelfacilityagent.db.model.RealmExclusion;
import com.ex.hotelfacilityagent.db.model.RealmExclusionList;
import com.ex.hotelfacilityagent.db.model.RealmFacility;
import com.ex.hotelfacilityagent.db.model.RealmFacilityApiResponse;
import com.ex.hotelfacilityagent.db.model.RealmOption;
import com.ex.hotelfacilityagent.model.Exclusion;
import com.ex.hotelfacilityagent.model.Facility;
import com.ex.hotelfacilityagent.model.FacilityApiResponse;
import com.ex.hotelfacilityagent.model.Option;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ModelFactory {

    public static RealmFacilityApiResponse getRealmFacilityApiResponse(FacilityApiResponse facilityApiResponse) {

        RealmFacilityApiResponse realmFacilityApiResponse = new RealmFacilityApiResponse();

        realmFacilityApiResponse.setExclusions(getExclusionRealmList(facilityApiResponse.getExclusions()));
        realmFacilityApiResponse.setFacilities(getFacilityRealmList(facilityApiResponse.getFacilities()));

        return realmFacilityApiResponse;
    }

    public static RealmList<RealmFacility> getFacilityRealmList(List<Facility> facilities){
        RealmList<RealmFacility> realmFacilities = new RealmList<>();

        for(Facility facility: facilities) {
            RealmFacility realmFacility = new RealmFacility();
            realmFacility.setFacilityId(facility.getFacilityId());
            realmFacility.setName(facility.getName());

            RealmList<RealmOption> optionRealmList = new RealmList<>();
            for(Option option: facility.getOptions()) {
                RealmOption realmOption = new RealmOption();
                realmOption.setIcon(option.getIcon());
                realmOption.setId(option.getId());
                realmOption.setName(option.getName());
                optionRealmList.add(realmOption);
            }
            realmFacility.setOptions(optionRealmList);
            realmFacilities.add(realmFacility);
        }
        return realmFacilities;
    }

    public static RealmList<RealmExclusionList> getExclusionRealmList(List<List<Exclusion>> exclusionList) {
        RealmList<RealmExclusionList> realmExclusions = new RealmList<RealmExclusionList>();

        for(List<Exclusion> exclusionList1 : exclusionList) {
            RealmExclusionList exclusionRealmList = new RealmExclusionList();
            RealmList<RealmExclusion> exclusionRealmList1 = new RealmList<>();
            for (Exclusion exclusion : exclusionList1) {
                RealmExclusion realmExclusion = new RealmExclusion();
                realmExclusion.setFacilityId(exclusion.getFacilityId());
                realmExclusion.setOptionsId(exclusion.getOptionsId());
                exclusionRealmList1.add(realmExclusion);
            }
            exclusionRealmList.setRealmExclusions(exclusionRealmList1);
            realmExclusions.add(exclusionRealmList);
        }
        return realmExclusions;
    }


    public static FacilityApiResponse getFacilityApiResponse(RealmFacilityApiResponse realmFacilityApiResponse) {
        FacilityApiResponse facilityApiResponse = new FacilityApiResponse();

        facilityApiResponse.setExclusions(getExclusionList(realmFacilityApiResponse.getExclusions()));
        facilityApiResponse.setFacilities(getFacilityList(realmFacilityApiResponse.getFacilities()));

        return facilityApiResponse;
    }

    public static List<List<Exclusion>> getExclusionList(RealmList<RealmExclusionList> realmExclusionLists) {
        List<List<Exclusion>> exclusionList = new ArrayList<>();

        for(RealmExclusionList exclusionRealmList : realmExclusionLists) {
            List<Exclusion> exclusionList1 = new ArrayList<>();
            for (RealmExclusion realmExclusion : exclusionRealmList.getRealmExclusions()) {
                Exclusion exclusion = new Exclusion();
                exclusion.setFacilityId(realmExclusion.getFacilityId());
                exclusion.setOptionsId(realmExclusion.getOptionsId());
                exclusionList1.add(exclusion);
            }
            exclusionList.add(exclusionList1);
        }
        return exclusionList;
    }

    public static List<Facility> getFacilityList(RealmList<RealmFacility> facilities){
        List<Facility> realmFacilities = new ArrayList<>();

        for(RealmFacility realmFacility: facilities) {
            Facility facility = new Facility();
            facility.setFacilityId(realmFacility.getFacilityId());
            facility.setName(realmFacility.getName());

            List<Option> optionList = new ArrayList<>();
            for(RealmOption optionRealm: realmFacility.getOptions()) {
                Option option = new Option();
                option.setIcon(optionRealm.getIcon());
                option.setId(optionRealm.getId());
                option.setName(optionRealm.getName());
                optionList.add(option);
            }
            facility.setOptions(optionList);
            realmFacilities.add(facility);
        }
        return realmFacilities;
    }


}
