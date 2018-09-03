package com.ex.hotelfacilityagent.db;

import com.ex.hotelfacilityagent.db.model.RealmFacilityApiResponse;
import com.ex.hotelfacilityagent.model.FacilityApiResponse;

import io.realm.Realm;
import io.realm.RealmResults;

public class DatabaseService {

    private static DatabaseService databaseService;
    private final Realm realm;

    private DatabaseService() {
        realm = Realm.getDefaultInstance();
    }

    public static DatabaseService getInstance() {
        if (databaseService == null) {
            databaseService = new DatabaseService();
        }
        return databaseService;
    }

    public void writeSoundData(FacilityApiResponse facilityApiResponse) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(ModelFactory.getRealmFacilityApiResponse(facilityApiResponse));
        realm.commitTransaction();
    }

    public FacilityApiResponse readSoundData() {
/*        {
            FacilityApiResponse facilityApiResponse = null;
            realm.beginTransaction();
            RealmResults<ReaFacilityApiResponse> realmResults = realm.where(FacilityApiResponse.class).findAll();
            facilityApiResponse = realm.copyFromRealm(realmResults);
            realm.commitTransaction();
            return facilityApiResponse;
        }*/

        FacilityApiResponse facilityApiResponse = null;
        realm.beginTransaction();
        RealmFacilityApiResponse realmResults = realm.where(RealmFacilityApiResponse.class).findFirst();
        facilityApiResponse  = ModelFactory.getFacilityApiResponse(realmResults);
        realm.commitTransaction();
        return facilityApiResponse;
    }

    public void closeRealmDB() {
        realm.close();
    }
}
