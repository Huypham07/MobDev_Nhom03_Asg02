package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.asg02.view.InfoActivity;

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
    }

    @Test
    public void testChangeNameButton() {
        Espresso.onView(ViewMatchers.withId(R.id.changeName)).perform(ViewActions.click());

    }

    @Test
    public void testImageViewVisibility() {
        Espresso.onView(ViewMatchers.withId(R.id.imageView3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
