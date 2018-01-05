package com.mytaxi.android_demo.utils.myutils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tymchysh on 1/2/2018.
 */

public class MyUtils {

    public static void clearUserData(){
        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();

        for (String fileName : sharedPreferencesFileNames) {
            InstrumentationRegistry
                    .getTargetContext()
                    .getSharedPreferences(fileName.replace(".xml", ""),
                            Context.MODE_PRIVATE).edit().clear().commit();
        }
    }

    public static String getUsername(){

        return AssetsPropertyReader.getValue("Username");
    }

    public static String getPassword(){

        return AssetsPropertyReader.getValue("Password");
    }

    public static String getSearchString(){

        return AssetsPropertyReader.getValue("SearchString");
    }

    public static String getFullName(){

        return AssetsPropertyReader.getValue("FullName");
    }

    public static String getPackageName(){

        return AssetsPropertyReader.getValue("PackageName");
    }

    public static  void runShellScript() throws InterruptedException, IOException {
        String cmd = "adb shell ls";
        String cmdreturn = "";
        Runtime run = Runtime.getRuntime();
        Process pr = null;
        try {
            pr = run.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        while ((buf.readLine())!=null) {
            System.out.println(cmdreturn);
        }
    }

}
