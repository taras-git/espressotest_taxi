package com.mytaxi.android_demo.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import java.io.File;

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

}
