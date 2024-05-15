package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.*;

public class AdminAddCinemaActivity2UITest {

    @Rule
    public ActivityScenarioRule<AdminAddCinemaActivity2> activityScenarioRule =
            new ActivityScenarioRule<>(AdminAddCinemaActivity2.class);

    @Test
    public void testEmailEditText() {
        // Kiểm tra EditText có tồn tại không
        Espresso.onView(ViewMatchers.withId(R.id.emailName))
                .check(ViewAssertions.matches(isDisplayed()));

        // Nhập dữ liệu vào EditText và kiểm tra lại
        Espresso.onView(ViewMatchers.withId(R.id.emailName))
                .check(ViewAssertions.matches(withText("cinemaname@star3.cineplex.com")));
    }

    @Test
    public void testPasswordEditText() {
        // Kiểm tra EditText mật khẩu có tồn tại không
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .check(ViewAssertions.matches(isDisplayed()));

        // Nhập dữ liệu vào EditText mật khẩu và kiểm tra lại

        Espresso.onView(ViewMatchers.withId(R.id.password))
                .check(ViewAssertions.matches(withText("#star3cineplex#cinemaname")));
    }

    @Test
    public void testFinishButton() {
        // Kiểm tra Button "Hoàn tất" có tồn tại không
        Espresso.onView(ViewMatchers.withId(R.id.finishBtn))
                .check(ViewAssertions.matches(isDisplayed()));

        // Kiểm tra hành vi khi nhấp vào Button "Hoàn tất"
        Espresso.onView(ViewMatchers.withId(R.id.finishBtn))
                .perform(ViewActions.click());

        // Thực hiện kiểm tra các hành vi tiếp theo sau khi nhấp vào Button "Hoàn tất"
    }
}
