package com.dfrobot.angelo.blunobasicdemo;

/**
 * Created by ShinoharaYuyoru on 2017/08/26.
 */

/*
* This class is used for setting Db.
*/


public class SensorDbSchema {
    public static final class SensorTable
    {
        public static final String NAME = "SENSOR_DATA";

        public static final class Cols
        {
            public static final String TIME = "TIME";
            public static final String MOISTURE = "MOISTURE";
            public static final String TEMPERATURE = "TEMPERATURE";
            public static final String ILLUMINATION = "ILLUMINATION";
        }
    }
}
