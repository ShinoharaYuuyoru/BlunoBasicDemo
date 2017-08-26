package com.dfrobot.angelo.blunobasicdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by ShinoharaYuyoru on 2017/08/26.
 */

public class AutoActivity extends AppCompatActivity {
    private ImageButton buttonAutoSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        buttonAutoSync = (ImageButton) findViewById(R.id.buttonAutoSync);
        buttonAutoSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send the message and get the data, and insert them into the database.

                /*
                * Shinohara's suggestion(2017.08.27):
                * At the start, we think this Activity sends the string "3" to let the Bluno save 30 groups of data automatically, and sends data to Application,
                * but there is a problem is, this Application, or this Activity may not wait for the Bluno until the groups of data send.
                * So I think this Activity is nearly ineffective.
                * That is, this Activity should delete.
                *
                * So I will not write this part.
                *
                * If you want to send or receive data in this Activity,
                * you can read the codes about Bluetooth's sending and receiving data in SensorActivity.java and SettingActivity.java for reference.
                */
            }
        });
    }
}
