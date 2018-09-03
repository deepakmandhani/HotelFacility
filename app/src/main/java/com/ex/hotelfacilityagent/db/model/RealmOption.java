
package com.ex.hotelfacilityagent.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmOption extends RealmObject{

    private String name;
    private String icon;
    @PrimaryKey
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
