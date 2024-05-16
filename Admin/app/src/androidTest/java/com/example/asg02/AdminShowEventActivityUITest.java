package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AdminShowEventActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminShowEventActivity> activityRule =
            new ActivityScenarioRule<>(AdminShowEventActivity.class);

    @Test
    public void testEditButton_Click() {
        onView(withId(R.id.editBtn)).perform(ViewActions.click());
        onView(withId(R.id.addEventActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testBackButton_Click() {
        onView(withId(R.id.backBtn6)).perform(ViewActions.click());
        onView(withId(R.id.managerEventActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
