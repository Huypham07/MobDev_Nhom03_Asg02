package com.example.admin;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
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
