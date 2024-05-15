package com.example.asg02;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AdminMessagingActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminMessagingActivity> activityRule =
            new ActivityScenarioRule<>(AdminMessagingActivity.class);

}
