package com.example.summercodingcalendar.util;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if(oldVersion == 0){
            RealmObjectSchema objectSchema = schema.get("Schedule");
            objectSchema.addField("isDeleted", boolean.class)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("isDeleted", false);
                        }
                    });
            oldVersion++;
        }
    }
}
