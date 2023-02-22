package com.se.se300_bed2bed.data;

import java.util.HashMap;

public abstract class Database {

    /*
    GET() will be used to access the database. When defined a HashMap should be returned
    containing the appropriate information, such as flight information or uber cost
    */
    protected abstract HashMap<String, String> GET();

}
