package com.mytaxi.android_demo.tests;

import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.robots.CallDriverRobot;
import com.mytaxi.android_demo.robots.LoginRobot;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest extends BasicTest{

    @Test
    public void useAppContext() throws Exception {
        Context appContext = getTargetContext();

        assertEquals(mPackageName, appContext.getPackageName());
    }

    @Test
    @LargeTest
    public void successfulLoginTest() {
        loginRobot = new LoginRobot();
        loginRobot
                .login(mUsernameToBeTyped, mPasswordToBeTyped);

        callDriverRobot = new CallDriverRobot();
        callDriverRobot = loginRobot
                .loginSuccess()
                .searchByName(mSearchStringToBeTyped)
                .callDriver(mDriverFullName);
    }

    @Test
    @LargeTest
    public void unsuccessfulLoginTest() {
        loginRobot = new LoginRobot();

        loginRobot
                .login(mUsernameToBeTyped, mWrongPasswordToBeTyped)
                .snackbarLoginFailedDisplayed();
    }
}