
package com.ex.hotelfacilityagent.model;

import com.ex.hotelfacilityagent.model.Exclusion;
import com.ex.hotelfacilityagent.model.Facility;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(Parcel.Serialization.BEAN)
public class FacilityApiResponse {

    @PrimaryKey
    int id;
    private List<Facility> facilities = new ArrayList<>();
    private List<List<Exclusion>> exclusions = new ArrayList<>();

    public List<com.ex.hotelfacilityagent.model.Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities.addAll(facilities);
        //this.facilities = facilities;
    }

    public List<List<Exclusion>> getExclusions() {
        return exclusions;
    }

    public void setExclusions(List<List<Exclusion>> exclusions) {
        this.exclusions.addAll(exclusions);
        //this.exclusions = exclusions;
    }

}
