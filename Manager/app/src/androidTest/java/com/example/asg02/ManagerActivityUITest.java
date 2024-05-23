package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.asg02.view.ManagerActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ManagerActivityUITest {

    @Rule
    public ActivityScenarioRule<ManagerActivity> activityScenarioRule
            = new ActivityScenarioRule<>(ManagerActivity.class);

    @Test
    public void testExpandableListViewVisibility() {
        Espresso.onView(ViewMatchers.withId(R.id.expandableListView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testTextViewContent() {
        Espresso.onView(ViewMatchers.withId(R.id.textView9))
                .check(ViewAssertions.matches(ViewMatchers.withText("Tên cụm rạp")));
    }

    @Test
    public void testImageViewVisibility() {
        Espresso.onView(ViewMatchers.withId(R.id.imageView14))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testClickOnChildItem() {
        Espresso.onData(Matchers.anything())
                .inAdapterView(ViewMatchers.withId(R.id.expandableListView))
                .atPosition(0)
                .perform(ViewActions.click());
    }
}
