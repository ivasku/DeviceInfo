package com.tms.hardwareinfophon;


import android.app.ActivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.ACTIVITY_SERVICE;

public class Util {
    private static final Util ourInstance = new Util();

    public static Util getInstance() {
        return ourInstance;
    }

    private Util() {
    }

    int getNumberOfCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    String getTotalRam() {
        RandomAccessFile reader = null;
        String load = null;
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double totRam = 0;
        String lastValue = "";
        try {
            reader = new RandomAccessFile("/proc/meminfo", "r");
            load = reader.readLine();

            // Get the Number value from the string
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
            }
            reader.close();

            totRam = Double.parseDouble(value);
            // totRam = totRam / 1024;

            double mb = totRam / 1024.0;
            double gb = totRam / 1048576.0;
            double tb = totRam / 1073741824.0;

            if (tb > 1) {
                lastValue = twoDecimalForm.format(tb).concat(" TB");
            } else if (gb > 1) {
                lastValue = twoDecimalForm.format(gb).concat(" GB");
            } else if (mb > 1) {
                lastValue = twoDecimalForm.format(mb).concat(" MB");
            } else {
                lastValue = twoDecimalForm.format(totRam).concat(" KB");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lastValue;
    }


    List<String> getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        MainActivity._Instance.getWindowManager().getDefaultDisplay().getMetrics(dm);

        float mXDpi;
        float mYDpi;

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;

        mXDpi = dm.xdpi; // The exact physical pixels per inch of the screen in the X dimension.
        mYDpi = dm.ydpi;
        double x = mXDpi / 0.0254f; // 1 inch == 0.0254 metre
        double y = mYDpi / 0.0254f;

        double screenInches = x + y;
        List<String> displayInfo = new ArrayList<>();
        displayInfo.add(String.valueOf(width));
        displayInfo.add(String.valueOf(height));
        displayInfo.add(String.format("%.2f", screenInches) + " inches");
        displayInfo.add(String.valueOf(dens));

        return displayInfo;
    }

    double getAvailableRam() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) MainActivity._Instance.getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        return (double) (mi.availMem / 0x100000L);
    }

    String getAvailableRamPercentage() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) MainActivity._Instance.getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        return String.format("%.2f", mi.availMem / (double) mi.totalMem * 100.0);
    }

    String getInternalStorage() {

        File path = Environment.getDataDirectory();
        StatFs stat2 = new StatFs(path.getPath());
        long blockSize = stat2.getBlockSize();
        long availableBlocks = stat2.getAvailableBlocks();
        String format = Formatter.formatFileSize(MainActivity._Instance, availableBlocks * blockSize);
        //Log.e("", "Format : " + format);
        return format;
    }

    String getFreeInternalStorage() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdAvailSize = (double) stat.getAvailableBlocks()
                * (double) stat.getBlockSize();
        //One binary gigabyte equals 1,073,741,824 bytes.
        double gigaAvailable = sdAvailSize / 1073741824;
        String percentAvail = String.format("%.2f", gigaAvailable);
        return percentAvail;
    }

    String getScreenSizeInches() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        MainActivity._Instance.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        double d1 = displayMetrics.widthPixels / displayMetrics.xdpi;
        double d2 = displayMetrics.heightPixels / displayMetrics.ydpi;
        double deviceInches = Math.sqrt(d1 * d1 + d2 * d2);
        String percentAvail = String.format("%.2f", deviceInches);
        return percentAvail;
    }

     String getCPUDetails(){

         return "TODO TODO";
    }




}
