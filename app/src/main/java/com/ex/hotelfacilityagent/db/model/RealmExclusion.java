
package com.ex.hotelfacilityagent.db.model;

import io.realm.RealmModel;
import io.realm.RealmObject;

public class RealmExclusion extends RealmObject implements RealmModel{

    private String facility_id;
    private String options_id;

    public String getFacilityId() {
        return facility_id;
    }

    public RealmExclusion() {
    }

    public RealmExclusion(String facility_id, String options_id) {
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
