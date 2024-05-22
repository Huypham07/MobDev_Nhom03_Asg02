package com.example.admin;

import static org.hamcrest.core.IsAnything.anything;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
public class AdminManageEventActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminManageEventActivity> activityRule =
            new ActivityScenarioRule<>(AdminManageEventActivity.class);

    @Test
    public void testButtonClick() {
        Espresso.onView(withId(R.id.addNewEventBtn)).perform(ViewActions.click());
    }

//    @Test
//    public void testItemClick() {
//        onData(anything())
//                .inAdapterView(withId(R.id.eventListView))
//                .atPosition(0)
//                .perform(ViewActions.click());
//    }
}
