package com.example.asg02;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.asg02.view.ManagerAddCinemaActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ManagerAddCinemaActivityUITest {

    @Rule
    public ActivityScenarioRule<ManagerAddCinemaActivity> activityScenarioRule
            = new ActivityScenarioRule<>(ManagerAddCinemaActivity.class);

    @Test
    public void testBackCinemaButton() {
        onView(withId(R.id.backCinema)).perform(ViewActions.click());
        onView(withId(R.id.managerActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testAddCinemaTitleDisplayed() {
        Espresso.onView(ViewMatchers.withText("Thêm rạp mới"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testEnterCinemaName() {
        Espresso.onView(ViewMatchers.withId(R.id.enterCinemaName))
                .perform(ViewActions.replaceText("Rạp ABC"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.enterCinemaName))
                .check(ViewAssertions.matches(ViewMatchers.withText("Rạp ABC")));
    }

    @Test
    public void testEnterAddressDetails() {
        Espresso.onView(ViewMatchers.withId(R.id.enterProvince))
                .perform(ViewActions.replaceText("TP. Hồ Chí Minh"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.enterDistrict))
                .perform(ViewActions.replaceText("Quận 1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.enterCommune))
                .perform(ViewActions.replaceText("Phường Bến Nghé"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.enterDetailAddress))
                .perform(ViewActions.replaceText("123 Đường Nguyễn Huệ"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.enterProvince))
                .check(ViewAssertions.matches(ViewMatchers.withText("TP. Hồ Chí Minh")));
        Espresso.onView(ViewMatchers.withId(R.id.enterDistrict))
                .check(ViewAssertions.matches(ViewMatchers.withText("Quận 1")));
        Espresso.onView(ViewMatchers.withId(R.id.enterCommune))
                .check(ViewAssertions.matches(ViewMatchers.withText("Phường Bến Nghé")));
        Espresso.onView(ViewMatchers.withId(R.id.enterDetailAddress))
                .check(ViewAssertions.matches(ViewMatchers.withText("123 Đường Nguyễn Huệ")));
    }

    @Test
    public void testAddCinemaButton() {
        Espresso.onView(ViewMatchers.withId(R.id.finishAddCinema))
                .perform(ViewActions.click());

        // Kiểm tra hành vi sau khi nhấn nút "Thêm"
    }
}
