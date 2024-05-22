package com.example.admin;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AdminMessagingActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminMessagingActivity> activityRule =
            new ActivityScenarioRule<>(AdminMessagingActivity.class);

}
