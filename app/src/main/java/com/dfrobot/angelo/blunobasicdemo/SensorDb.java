package com.dfrobot.angelo.blunobasicdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;

import com.dfrobot.angelo.blunobasicdemo.SensorDbSchema.SensorTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShinoharaYuyoru on 2017/08/26.
 */

/*
* This class is used for setting Db.
*/

public class SensorDb {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public SensorDb(Context context)
    {
        mContext = context.getApplicationContext();
        mDatabase = new SensorBaseHelper(mContext).getWritableDatabase();
    }

    public void addSensorData(SensorDataLine s)
    {
        ContentValues values = getContentValues(s);

        mDatabase.insert(SensorTable.NAME, null, values);
    }

    public List<SensorDataLine> getSensorData()
    {
        return new ArrayList<>();
    }

    private static ContentValues getContentValues(SensorDataLine s)
    {
        ContentValues values = new ContentValues();
        values.put(SensorTable.Cols.TIME, s.getTIME());
        values.put(SensorTable.Cols.MOISTURE, s.getMOISTURE());
        values.put(SensorTable.Cols.TEMPERATURE, s.getTEMPERATURE());
        values.put(SensorTable.Cols.ILLUMINATION, s.getILLUMINATION());

        return values;
    }
}
