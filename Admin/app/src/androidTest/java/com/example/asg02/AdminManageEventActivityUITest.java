package com.example.asg02;

import static org.hamcrest.core.IsAnything.anything;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
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
