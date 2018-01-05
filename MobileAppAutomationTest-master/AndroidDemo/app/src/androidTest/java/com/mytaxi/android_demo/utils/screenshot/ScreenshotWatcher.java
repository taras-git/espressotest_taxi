package com.mytaxi.android_demo.utils.screenshot;

import android.graphics.Bitmap;
import android.support.test.runner.screenshot.ScreenCapture;
import android.support.test.runner.screenshot.Screenshot;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;

/**
 * Created by tymchysh on 1/5/2018.
 */

public class ScreenshotWatcher extends TestWatcher {

    private void captureScreenshot(String name) {
        ScreenCapture capture = Screenshot.capture();
        capture.setFormat(Bitmap.CompressFormat.PNG);
        capture.setName(name);
        try {
            capture.process();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    protected void succeeded(Description description) {
        captureScreenshot(description.getMethodName() + "_success");
    }


    @Override
    protected void failed(Throwable e, Description description) {
        captureScreenshot(description.getMethodName() + "_fail");
    }

}