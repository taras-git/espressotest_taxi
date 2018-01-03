package com.mytaxi.android_demo.robots;

import android.support.annotation.NonNull;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.Root;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by tymchysh on 1/2/2018.
 */

public class Robot {
    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    protected  void clickOnViewById(int viewId){
        onView(withId(viewId))
                .perform(click());
    }

    protected void clickOnViewInPopupByText(String text){
        onView(withText(text))
                .inRoot(isPopupWindow())
                .perform(click());
    }

    protected void typeTextOnViewById(int viewId, String textToType, boolean closeSoftKeyboard){
        if (closeSoftKeyboard) {
            onView(withId(viewId))
                    .perform(typeText(textToType),
                            closeSoftKeyboard());
        } else {
            onView(withId(viewId))
                    .perform(typeText(textToType));
        }
    }

    protected void isSnackbarWithTextDisplayed(int viewId, String text){
        onView(allOf(withId(viewId),
                withText(text)))
                .check(matches(isDisplayed()));

    }

    protected void isViewDisplayed(int viewId){
        onView(withId(viewId))
                .check(matches(isDisplayed()));
    }

    @NonNull
    protected static Matcher<Root> isPopupWindow() {

        return isPlatformPopup();
    }

}
