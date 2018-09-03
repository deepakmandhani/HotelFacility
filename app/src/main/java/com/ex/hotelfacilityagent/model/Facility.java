package com.ex.hotelfacilityagent.model;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(Parcel.Serialization.BEAN)
public class Facility {

    @PrimaryKey
    private String facility_id;
    private String name;
    private List<Option> options = null;

    public String getFacilityId() {
        return facility_id;
    }

    public void setFacilityId(String facilityId) {
        this.facility_id = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
