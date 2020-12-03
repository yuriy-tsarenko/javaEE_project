package com.javaee.example.datasource;

import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;

public class Session {
    private OrientDB orient;
    private ODatabaseSession db;

    public ODatabaseSession openSession() {
        orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
        db = orient.open("orient_db", "admin", "admin");
        return db;
    }

    public void closeSession() {
        db.close();
        orient.close();
    }
}
