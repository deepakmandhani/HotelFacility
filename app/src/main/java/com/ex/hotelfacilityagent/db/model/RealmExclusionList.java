package com.ex.hotelfacilityagent.db.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class RealmExclusionList extends RealmObject {

    RealmList<RealmExclusion> realmExclusions = null;

    public RealmList<RealmExclusion> getRealmExclusions() {
        return realmExclusions;
    }

    public void setRealmExclusions(RealmList<RealmExclusion> realmExclusions) {
        this.realmExclusions = realmExclusions;
    }
}
