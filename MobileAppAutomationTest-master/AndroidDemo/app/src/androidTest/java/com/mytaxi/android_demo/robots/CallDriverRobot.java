package com.mytaxi.android_demo.robots;

import com.mytaxi.android_demo.R;

/**
 * Created by tymchysh on 1/2/2018.
 */

public class CallDriverRobot extends Robot{

    public CallDriverRobot searchByName(String searchString) {
        typeTextOnViewById(R.id.textSearch, searchString, true);

        return this;
    }

    public CallDriverRobot callDriver(String driverName) {
        clickOnViewInPopupByText(driverName);
        clickOnViewById(R.id.fab);

        return this;
    }

}
