package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsAnything.anything;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AdminMessageActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminMessageActivity> activityRule = new ActivityScenarioRule<>(AdminMessageActivity.class);

    @Test
    public void testBackButton() {
        Espresso.onView(withId(R.id.backBtn7)).perform(ViewActions.click());
    }

    @Test
    public void testListViewItemClick() {
        onData(anything())
                .inAdapterView(withId(R.id.userListView))
                .atPosition(0)
                .perform(ViewActions.click());
    }
}
