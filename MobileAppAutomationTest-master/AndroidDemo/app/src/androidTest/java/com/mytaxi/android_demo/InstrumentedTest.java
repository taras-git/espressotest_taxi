package com.mytaxi.android_demo;

import android.content.Context;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.robots.CallDriverRobot;
import com.mytaxi.android_demo.robots.LoginRobot;
import com.mytaxi.android_demo.utils.MyUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
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
public class InstrumentedTest {

    LoginRobot loginRobot;
    CallDriverRobot callDriverRobot;

    private String mUsernameToBeTyped;
    private String mPasswordToBeTyped;
    private String mWrongPasswordToBeTyped;
    private String mSearchStringToBeTyped;
    private String mDriverFullName;
    private String mPackageName;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        mUsernameToBeTyped = MyUtils.getUsername();
        mPasswordToBeTyped = MyUtils.getPassword();
        mWrongPasswordToBeTyped = mPasswordToBeTyped + "wrong";
        mSearchStringToBeTyped = MyUtils.getSearchString();
        mDriverFullName = MyUtils.getFullName();
        mPackageName = MyUtils.getPackageName();
    }

    @Before
    public void initRobots(){
        loginRobot = new LoginRobot();
    }

    @Before
    @After
    public void clearUserData(){
        MyUtils.clearUserData();
    }


    @Test
    public void useAppContext() throws Exception {
        Context appContext = getTargetContext();

        assertEquals(mPackageName, appContext.getPackageName());
    }

    @Test
    @LargeTest
    public void successfulLoginTest() {
        loginRobot
                .login(mUsernameToBeTyped, mPasswordToBeTyped);

        callDriverRobot = loginRobot
                .loginSuccess()
                .searchByName(mSearchStringToBeTyped)
                .callDriver(mDriverFullName);
    }

    @Test
    @LargeTest
    public void unsuccessfulLoginTest() {
        loginRobot
                .login(mUsernameToBeTyped, mWrongPasswordToBeTyped)
                .snackbarLoginFailedDisplayed();
    }
}
