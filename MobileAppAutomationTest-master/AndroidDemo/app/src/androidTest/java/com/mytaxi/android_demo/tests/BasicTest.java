package com.mytaxi.android_demo.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.robots.CallDriverRobot;
import com.mytaxi.android_demo.robots.LoginRobot;
import com.mytaxi.android_demo.utils.MyUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BasicTest {

    LoginRobot loginRobot;
    CallDriverRobot callDriverRobot;

    String mUsernameToBeTyped;
    String mPasswordToBeTyped;
    String mWrongPasswordToBeTyped;
    String mSearchStringToBeTyped;
    String mDriverFullName;
    String mPackageName;

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
        callDriverRobot = new CallDriverRobot();
    }

    @Before
    @After
    public void clearUserData(){
        MyUtils.clearUserData();
    }

}
