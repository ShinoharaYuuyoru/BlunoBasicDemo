package com.dfrobot.angelo.blunobasicdemo;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends BlunoLibrary {
	private Button buttonScan;
	private Button buttonSerialSend;
	private ImageButton buttonSensor;
	private ImageButton buttonSetting;
	private ImageButton buttonAuto;
	private EditText serialSendText;
	private TextView serialReceivedText;
	private BluetoothAdapter blttA = BluetoothAdapter.getDefaultAdapter();		// Use to check Bluetooth's state.
																				// But this is just check the Bluetooth is open or not
																				// Doesn't check connecting or not.
																				// That is, we just think the Bluetooth ON == CONNECTING
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        onCreateProcess();														//onCreate Process by BlunoLibrary

        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200

        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data
        serialSendText=(EditText) findViewById(R.id.serialSendText);			//initial the EditText of the sending data

        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data
        buttonSerialSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				serialSend(serialSendText.getText().toString());				//send the data to the BLUNO
			}
		});

        buttonScan = (Button) findViewById(R.id.buttonScan);					//initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				buttonScanOnClickProcess();										//Alert Dialog for selecting the BLE device
			}
		});

		buttonSensor = (ImageButton) findViewById(R.id.buttonSensor);
		buttonSensor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(blttA.isEnabled())
				{
					Intent intentSensorV = new Intent(getApplicationContext(), SensorActivity.class);
					startActivity(intentSensorV);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "请确认蓝牙是否开启，且是否正确连接设备。", Toast.LENGTH_SHORT).show();
				}
			}
		});

		buttonSetting = (ImageButton) findViewById(R.id.buttonSetting);
		buttonSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(blttA.isEnabled())
				{
					Intent intentSettingV = new Intent(getApplicationContext(), SettingActivity.class);
					startActivity(intentSettingV);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "请确认蓝牙是否开启，且是否正确连接设备。", Toast.LENGTH_SHORT).show();
				}
			}
		});

		buttonAuto = (ImageButton) findViewById(R.id.buttonAuto);
		buttonAuto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(blttA.isEnabled())
				{
					Intent intentAutoV = new Intent(getApplicationContext(), AutoActivity.class);
					startActivity(intentAutoV);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "请确认蓝牙是否开启，且是否正确连接设备。", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	protected void onResume(){
		super.onResume();
		System.out.println("BlUNOActivity onResume");
		onResumeProcess();														//onResume Process by BlunoLibrary
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
		super.onActivityResult(requestCode, resultCode, data);
	}
	
    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();														//onPause Process by BlunoLibrary
    }
	
	protected void onStop() {
		super.onStop();
		onStopProcess();														//onStop Process by BlunoLibrary
	}
    
	@Override
    protected void onDestroy() {
        super.onDestroy();	
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

	@Override
	public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
		switch (theConnectionState) {											//Four connection state
		case isConnected:
			buttonScan.setText("Connected");
			break;
		case isConnecting:
			buttonScan.setText("Connecting");
			break;
		case isToScan:
			buttonScan.setText("Scan");
			break;
		case isScanning:
			buttonScan.setText("Scanning");
			break;
		case isDisconnecting:
			buttonScan.setText("isDisconnecting");
			break;
		default:
			break;
		}
	}

	@Override
	public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
		// TODO Auto-generated method stub
		serialReceivedText.append(theString);							//append the text into the EditText
		//The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.
		((ScrollView)serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);
	}
}