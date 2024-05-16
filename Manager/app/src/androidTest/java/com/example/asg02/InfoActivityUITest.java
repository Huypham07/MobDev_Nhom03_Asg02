package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InfoActivityUITest {

    @Rule
    public ActivityScenarioRule<InfoActivity> activityScenarioRule
            = new ActivityScenarioRule<>(InfoActivity.class);

    @Test
    public void testBackButton() {
        Espresso.onView(ViewMatchers.withId(R.id.backInfo)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.managerActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testChangeNameButton() {
        Espresso.onView(ViewMatchers.withId(R.id.changeName)).perform(ViewActions.click());

        // Kiểm tra xem có đúng là đã chuyển sang màn hình đổi tên không (dùng id của view trên màn hình mới để kiểm tra)
//        Espresso.onView(ViewMatchers.withId(R.id.newNameEditText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testChangePasswordButton() {
        Espresso.onView(ViewMatchers.withId(R.id.changePassword)).perform(ViewActions.click());

        // Kiểm tra xem có đúng là đã chuyển sang màn hình đổi mật khẩu không (dùng id của view trên màn hình mới để kiểm tra)
//        Espresso.onView(ViewMatchers.withId(R.id.newPasswordEditText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testImageViewVisibility() {
        Espresso.onView(ViewMatchers.withId(R.id.imageView2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.imageView3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.imageView4)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
