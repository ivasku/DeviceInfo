package com.tms.hardwareinfophon;

import android.text.Html;

import com.jaredrummler.android.device.DeviceName;

public class UtilInfo {

    private String deviceManugacturer, deviceMarketName, deviceModel, deviceCodename, deviceName;
    private String deviceInfo;

    public UtilInfo() {

    }

    public String getDeviceManufacturer () {
        return deviceManugacturer;
    }

    public String getDeviceMarketName() {
        getDeviceInfoLib();
        return deviceMarketName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceCodename() {
        return deviceCodename;
    }

    public String getDeviceName() {
        return deviceName;
    }


    public void getDeviceInfoLib() {
        DeviceName.getDeviceInfo(MainActivity._Instance);


        DeviceName.with(MainActivity._Instance).request(new DeviceName.Callback() {

            @Override public void onFinished(DeviceName.DeviceInfo info, Exception error) {
                deviceManugacturer = info.manufacturer;  // "Samsung"
                deviceMarketName = info.marketName;            // "Galaxy S8+"
                deviceModel = info.model;                // "SM-G955W"
                deviceCodename = info.codename;          // "dream2qltecan"
                deviceName = info.getName();       // "Galaxy S8+"
                //info.
            }
        });
    }


}
