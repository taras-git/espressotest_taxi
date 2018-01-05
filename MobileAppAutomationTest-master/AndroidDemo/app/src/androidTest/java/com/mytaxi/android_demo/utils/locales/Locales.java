package com.mytaxi.android_demo.utils.locales;

import java.util.Locale;

/**
 * Created by tymchysh on 1/5/2018.
 */

public final class Locales {

    private Locales() {
        throw new AssertionError();
    }


    public static Locale english() {
        return Locale.ENGLISH;
    }


    public static Locale russian() {

        return new Locale
                .Builder()
                .setLanguage("ru")
                .build();
    }


    public static Locale ukrainian() {
        return new Locale
                .Builder()
                .setLanguage("uk")
                .build();
    }
}
