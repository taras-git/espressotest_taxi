package com.mytaxi.android_demo.utils.myrules;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.util.DisplayMetrics;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Locale;

/**
 * Created by tymchysh on 1/5/2018.
 */

public class LocaleRule implements TestRule {

    private final Locale[] mLocales;
    private Locale mDeviceLocale;

    public LocaleRule(Locale... locales){
        mLocales = locales;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try{
                    if (mLocales != null) {
                        mDeviceLocale = Locale.getDefault();
                        for(Locale locale : mLocales) {
                            setLocale(locale);
                            base.evaluate();
                        }
                    }
                } finally {
                    if(mDeviceLocale != null){
                        setLocale(mDeviceLocale);
                    }
                }
            }
        };
    }

    private void setLocale(Locale locale) {
        Resources resources = InstrumentationRegistry.getContext().getResources();
        Locale.setDefault(locale);
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        DisplayMetrics displayMetrics  =resources.getDisplayMetrics();
        resources.updateConfiguration(config, displayMetrics);
    }
}
