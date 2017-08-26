package com.dfrobot.angelo.blunobasicdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SettingActivity extends BlunoLibrary {

    private EditText editTextPlantName;
    private EditText editTextSetMoisture1;
    private EditText editTextSetMoisture2;
    private EditText editTextSetIllumination1;
    private EditText editTextSetIllumination2;
    private ImageButton buttonDefaultPlant1;
    private ImageButton buttonDefaultPlant2;
    private ImageButton buttonDefaultPlant3;
    private ImageButton buttonDefaultPlant4;
    private Button buttonSaveSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        editTextPlantName = (EditText) findViewById(R.id.editTextPlantName);
        editTextSetMoisture1 = (EditText) findViewById(R.id.editTextSetMoisture1);
        editTextSetMoisture2 = (EditText) findViewById(R.id.editTextSetMoisture2);
        editTextSetIllumination1 = (EditText) findViewById(R.id.editTextSetIllumination1);
        editTextSetIllumination2 = (EditText) findViewById(R.id.editTextSetIllumination2);

        buttonDefaultPlant1 = (ImageButton) findViewById(R.id.buttonDefaultPlant1);
        buttonDefaultPlant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the EditText.
                /*
                * Shinohara's suggestion(2017.08.27):
                * Just xxxx.setText("...").
                * I don't know the data for default, so this part need further filling.
                */
            }
        });

        buttonDefaultPlant2 = (ImageButton) findViewById(R.id.buttonDefaultPlant2);
        buttonDefaultPlant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the EditText.
            }
        });

        buttonDefaultPlant3 = (ImageButton) findViewById(R.id.buttonDefaultPlant3);
        buttonDefaultPlant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the EditText.
            }
        });

        buttonDefaultPlant4 = (ImageButton) findViewById(R.id.buttonDefaultPlant4);
        buttonDefaultPlant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the EditText.
            }
        });

        buttonSaveSetting = (Button) findViewById(R.id.buttonSaveSetting);
        buttonSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send Setting to the pot.

                /*
                * Shinohara's suggestion(2017.08.27):
                * In the meeting about this project, in this part, we want to send a string with 3 numbers.
                * 1_xxxx_xxxx.
                * But... there are 4 numbers should be set.
                * That is, 1_xxxx_xxxx_xxxx_xxxx.
                * I don't know how the Bluno works, so I will just write the codes, but it will probably not work.
                */
                String theWholeStr;
                theWholeStr = "1 " +
                                editTextSetMoisture1.getText().toString() + " " +
                                editTextSetMoisture2.getText().toString() + " " +
                                editTextSetIllumination1.getText().toString() + " " +
                                editTextSetIllumination2.getText().toString();

                serialSend(theWholeStr);
            }
        });
    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called

    }

    @Override
    public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
        // This Activity will not receive data.
    }
}
