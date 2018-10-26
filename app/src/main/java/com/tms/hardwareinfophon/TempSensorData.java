package com.tms.hardwareinfophon;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

//docs
//https://developer.android.com/reference/android/hardware/Sensor#TYPE_AMBIENT_TEMPERATURE

public class TempSensorData implements SensorEventListener {

    private final SensorManager sensorManager;
    private final Sensor tempSensor;
    private final Sensor accSensor;
    private final Sensor gyroSensor;

    public TempSensorData() {
        sensorManager = (SensorManager)MainActivity._Instance.getSystemService(MainActivity.SENSOR_SERVICE);
        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {

            case Sensor.TYPE_ACCELEROMETER:
                //data
                break;

            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                //data
                break;

            case Sensor.TYPE_GYROSCOPE:
                //data
                break;

            default:
                //data
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void ResumeSensor() {
        sensorManager.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void PauseSensor() {
        sensorManager.unregisterListener(this);
    }
}
