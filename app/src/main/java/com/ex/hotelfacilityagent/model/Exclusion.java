
package com.ex.hotelfacilityagent.model;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(Parcel.Serialization.BEAN)
public class Exclusion {

    private String facility_id;
    private String options_id;

    public String getFacilityId() {
        return facility_id;
    }

    public Exclusion() {
    }

    public Exclusion(String facility_id, String options_id) {
        this.facility_id = facility_id;
        this.options_id = options_id;
    }

    public void setFacilityId(String facilityId) {
        this.facility_id = facilityId;
    }

    public String getOptionsId() {
        return options_id;
    }

    public void setOptionsId(String optionsId) {
        this.options_id = optionsId;
    }

}
