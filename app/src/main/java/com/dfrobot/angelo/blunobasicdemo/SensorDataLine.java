package com.dfrobot.angelo.blunobasicdemo;

/**
 * Created by ShinoharaYuyoru on 2017/08/26.
 */

/*
* This class is used for setting or getting a line with 4 data which will be inserted or read.
*/

public class SensorDataLine {
    private String mTIME;
    private String mMOISTURE;
    private String mTEMPERATURE;
    private String mILLUMINATION;

    public void setTIME(String t)
    {
        mTIME = t;
    }

    public void setMOISTURE(String t)
    {
        mMOISTURE = t;
    }

    public void setTEMPERATURE(String t)
    {
        mTEMPERATURE = t;
    }

    public void setILLUMINATION(String t)
    {
        mILLUMINATION = t;
    }

    public String getTIME()
    {
        return mTIME;
    }

    public String getMOISTURE()
    {
        return mMOISTURE;
    }

    public String getTEMPERATURE()
    {
        return mTEMPERATURE;
    }

    public String getILLUMINATION()
    {
        return mILLUMINATION;
    }
}
