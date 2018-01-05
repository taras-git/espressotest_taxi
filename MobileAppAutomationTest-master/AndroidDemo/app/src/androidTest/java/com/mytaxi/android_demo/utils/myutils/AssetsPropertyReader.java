package com.mytaxi.android_demo.utils.myutils;

/**
 * Created by tymchysh on 1/1/2018.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import static android.support.test.InstrumentationRegistry.getTargetContext;

public class AssetsPropertyReader {
    private Context context;
    private Properties properties;

    public AssetsPropertyReader(Context context) {
        this.context = context;
        /**
         * Constructs a new Properties object.
         */
        properties = new Properties();
    }

    private Properties getProperties(String FileName) {

        try {
            /**
             * getAssets() Return an AssetManager instance  application's package. AssetManager Provides access to an
             * application's raw asset files;
             */
            AssetManager assetManager = context.getAssets();
            /**
             * Open an asset using ACCESS_STREAMING mode.
             */
            InputStream inputStream = assetManager.open(FileName);
            /**
             * Loads properties from the specified InputStream
             */
            properties.load(inputStream);

        } catch (IOException e) {
            Log.e("AssetsPropertyReader", e.toString());
        }
        return properties;

    }

    public static String getValue(String key) {
        AssetsPropertyReader assetsPropertyReader = new AssetsPropertyReader(getTargetContext());
        return assetsPropertyReader.getProperties("user.data").getProperty(key);
    }

}
