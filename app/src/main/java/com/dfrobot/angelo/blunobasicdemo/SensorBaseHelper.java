package com.dfrobot.angelo.blunobasicdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.Sensor;

import com.dfrobot.angelo.blunobasicdemo.SensorDbSchema.SensorTable;

/**
 * Created by ShinoharaYuyoru on 2017/08/26.
 */

/*
* This class is used for SQLite Db's creating and upgrading.
*/

public class SensorBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "sensorBase.db";

    public SensorBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table" + SensorTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    SensorTable.Cols.TIME + ", " +
                    SensorTable.Cols.MOISTURE + ", " +
                    SensorTable.Cols.TEMPERATURE + ", " +
                    SensorTable.Cols.ILLUMINATION + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
