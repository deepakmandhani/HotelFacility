
package com.ex.hotelfacilityagent.db.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmFacilityApiResponse extends RealmObject {

    @PrimaryKey
    int id;
    private RealmList<RealmFacility> facilities = new RealmList<>();
    private RealmList<RealmExclusionList> exclusions = new RealmList<>();

    public RealmList<RealmFacility> getFacilities() {
        return facilities;
    }

    public void setFacilities(RealmList<RealmFacility> facilities) {
        this.facilities.addAll(facilities);
        //this.facilities = facilities;
    }

    public RealmList<RealmExclusionList> getExclusions() {
        return exclusions;
    }

    public void setExclusions(RealmList<RealmExclusionList> exclusions) {
        this.exclusions.addAll(exclusions);
        //this.exclusions = exclusions;
    }

}
