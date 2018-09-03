package com.ex.hotelfacilityagent.db.model;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmFacility extends RealmObject{

    @PrimaryKey
    private String facility_id;
    private String name;
    private RealmList<RealmOption> options = null;

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

    public RealmList<RealmOption> getOptions() {
        return options;
    }

    public void setOptions(RealmList<RealmOption> options) {
        this.options = options;
    }

}
