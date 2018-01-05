package com.mytaxi.android_demo.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.robots.CallDriverRobot;
import com.mytaxi.android_demo.robots.LoginRobot;
import com.mytaxi.android_demo.utils.myutils.MyUtils;
import com.mytaxi.android_demo.utils.myrules.LocaleRule;
import com.mytaxi.android_demo.utils.screenshot.ScreenshotWatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.mytaxi.android_demo.utils.locales.Locales.english;
import static com.mytaxi.android_demo.utils.locales.Locales.ukrainian;

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


    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public final ScreenshotWatcher mScreenshotWatcher = new ScreenshotWatcher();

    public final GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE);

    public final LocaleRule mLocaleRule = new LocaleRule(english(), ukrainian());


    @Rule
    public final RuleChain mRuleChain = RuleChain
            .outerRule(mLocaleRule)
            .outerRule(mScreenshotWatcher)
            .around(mGrantPermissionRule)
            .around(mActivityRule);


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
