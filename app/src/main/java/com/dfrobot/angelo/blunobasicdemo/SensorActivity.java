package com.dfrobot.angelo.blunobasicdemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SensorActivity extends BlunoLibrary {

    private TextView textViewDataMoisture;
    private TextView textViewDataTemperature;
    private TextView textViewDataIllumination;
    private Button buttonSaveData;
    private Button buttonHistory;
    private ImageButton buttonSensorSync;

    private SensorDataLine SDL;
    private SensorDb SD;
    BlunoLibrary BLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        textViewDataMoisture = (TextView) findViewById(R.id.data_moisture);
        textViewDataTemperature = (TextView) findViewById(R.id.data_temperature);
        textViewDataIllumination = (TextView) findViewById(R.id.data_illumination);

        buttonSaveData = (Button) findViewById(R.id.buttonSaveData);
        buttonSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save SensorDataLine into the SensorDb

                // Get the time(maybe UTC).
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String timeStr = formatter.format(curDate);

                // Set a new Database Line.
                SDL = new SensorDataLine();
                SDL.setTIME(timeStr);
                SDL.setMOISTURE(textViewDataMoisture.getText().toString());
                SDL.setTEMPERATURE(textViewDataTemperature.getText().toString());
                SDL.setILLUMINATION(textViewDataIllumination.getText().toString());

                // Add to Database.
                SD = new SensorDb(getApplicationContext());
                SD.addSensorData(SDL);
            }
        });

        buttonHistory = (Button) findViewById(R.id.buttonHistory);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHistoryV = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intentHistoryV);

                onStop();
            }
        });

        buttonSensorSync = (ImageButton) findViewById(R.id.sensor_sync);
        buttonSensorSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send String and get the data and save that into the database.
                serialSend("2");
            }
        });
    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called

    }

    @Override
    public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
        // Get the STRING in theString.
        // Cut theString into 3 numbers.
        String num1 = "";
        String num2 = "";
        String num3 = "";
        int whichNum;
        int flag1, flag2;       // The flag1 signs the start Index, and the flag2 signs end Index.
        for(flag1 = 0, flag2 = 0, whichNum = 1; whichNum < 3; flag2++)
        {
            if(theString.charAt(flag2) == ' ')
            {
                switch (whichNum)
                {
                    case 1: num1 = theString.substring(flag1, flag2); break;
                    case 2: num2 = theString.substring(flag1 + 1, flag2);
                            num3 = theString.substring(flag2);
                            break;
                }
                flag1 = flag2;
                whichNum++;
            }
        }
        // Set the numbers to textViews.
        textViewDataMoisture.setText(num1);
        textViewDataTemperature.setText(num2);
        textViewDataIllumination.setText(num3);
    }
}
