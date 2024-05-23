package com.example.asg02;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import com.example.asg02.view.ManagerAddCinemaActivity;

@RunWith(AndroidJUnit4.class)
public class ManagerAddCinemaActivityUITest {

    @Rule
    public ActivityScenarioRule<ManagerAddCinemaActivity> activityRule =
            new ActivityScenarioRule<>(ManagerAddCinemaActivity.class);

    @Test
    public void testUIElements() {
        // Check if the header text is displayed correctly
        onView(withText("Thêm rạp mới")).check(matches(isDisplayed()));

        // Enter text into the cinema name field and check if the text is displayed
        onView(withId(R.id.enterCinemaName)).perform(ViewActions.typeText("Cinema XYZ"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enterCinemaName)).check(matches(withText("Cinema XYZ")));

        // Enter text into the latitude field and check if the text is displayed
        onView(withId(R.id.enterLatitude)).perform(ViewActions.typeText("10.762622"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enterLatitude)).check(matches(withText("10.762622")));

        // Enter text into the longitude field and check if the text is displayed
        onView(withId(R.id.enterLongtitude)).perform(ViewActions.typeText("106.660172"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enterLongtitude)).check(matches(withText("106.660172")));

        // Enter text into the detail address field and check if the text is displayed
        onView(withId(R.id.enterDetailAddress)).perform(ViewActions.typeText("123 Main Street"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enterDetailAddress)).check(matches(withText("123 Main Street")));

        // Check if the Add button is displayed and clickable
        onView(withId(R.id.finishAddCinema)).check(matches(isDisplayed()));
        onView(withId(R.id.finishAddCinema)).perform(ViewActions.click());
    }

    @Test
    public void testBackButton() {
        // Check if the back button is displayed and clickable
        onView(withId(R.id.backCinema)).check(matches(isDisplayed()));
        onView(withId(R.id.backCinema)).perform(ViewActions.click());
    }
}
