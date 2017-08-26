package com.dfrobot.angelo.blunobasicdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ShinoharaYuyoru on 2017/08/26.
 */

public class HistoryActivity extends ActionBarActivity {
    private Button buttonClearHistory;
    private SensorBaseHelper sbh;
    private SQLiteDatabase sd;
    private ArrayList<SensorDataLine> SDLList;
    private ListView lv;
    private SensorDataLine SDL;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Show the Database in ListView.
        sbh = new SensorBaseHelper(this);
        sd = sbh.getReadableDatabase();
        SDLList = new ArrayList<>();
        Cursor cursor = sd.rawQuery("select * from SENSOR_DATA", null);
        cursor.moveToFirst();
        while(cursor.moveToNext())
        {
            String time = cursor.getString(cursor.getColumnIndex("TIME"));
            String moisture = cursor.getString(cursor.getColumnIndex("MOISTURE"));
            String temperature = cursor.getString(cursor.getColumnIndex("TEMPERATURE"));
            String illumination = cursor.getString(cursor.getColumnIndex("ILLUMINATION"));
            SDL = new SensorDataLine();
            SDL.setTIME(time);
            SDL.setMOISTURE(moisture);
            SDL.setTEMPERATURE(temperature);
            SDL.setILLUMINATION(illumination);
            SDLList.add(SDL);
        }
        lv = (ListView) findViewById(R.id.listViewDb);
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return SDLList.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;

                if(convertView == null)
                {
                    view = View.inflate(getBaseContext(), R.layout.listdatalayout, null);
                }
                else
                {
                    view = convertView;
                }

                TextView time = (TextView) findViewById(R.id.DBLTime);
                TextView moisture = (TextView) findViewById(R.id.DBLMoisture);
                TextView temperature = (TextView) findViewById(R.id.DBLTemperature);
                TextView illumination = (TextView) findViewById(R.id.DBLIllumination);

                SensorDataLine sdl = SDLList.get(position);
                time.setText(sdl.getTIME());
                moisture.setText(sdl.getMOISTURE());
                temperature.setText(sdl.getTEMPERATURE());
                illumination.setText(sdl.getILLUMINATION());
                return view;
            }
        });

        buttonClearHistory = (Button) findViewById(R.id.buttonClearHistory);
        buttonClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the Database.
                sd.execSQL("DELETE FROM SENSOR_DATA");
            }
        });

        // Get the Database with Arraylist and show the data with ListView.
    }
}
