package com.mytaxi.android_demo.robots;

import com.mytaxi.android_demo.R;

/**
 * Created by tymchysh on 1/2/2018.
 */

public class LoginRobot extends Robot{

    public LoginRobot login(String username, String password) {
        typeTextOnViewById(R.id.edt_username, username, true);
        typeTextOnViewById(R.id.edt_password, password, true);
        clickOnViewById(R.id.btn_login);

        return this;
    }

    public LoginRobot snackbarLoginFailedDisplayed() {
        int id = android.support.design.R.id.snackbar_text;

        waitId(id, 2000);
        isSnackbarWithTextDisplayed(id, "Login failed");

        return this;
    }

    public CallDriverRobot loginSuccess() {
        int id = R.id.textSearch;

        waitId(id, 2000);
        isViewDisplayed(id);

        return new CallDriverRobot();
    }
}
