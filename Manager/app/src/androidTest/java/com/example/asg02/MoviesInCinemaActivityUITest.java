package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.asg02.view.MoviesInCinemaActivity;

@RunWith(AndroidJUnit4.class)
public class MoviesInCinemaActivityUITest {

    @Rule
    public ActivityScenarioRule<MoviesInCinemaActivity> activityScenarioRule = new ActivityScenarioRule<>(MoviesInCinemaActivity.class);

    @Test
    public void testTextViewDisplay() {
        Espresso.onView(withId(R.id.textView4)).check(ViewAssertions.matches(withText("Đang chiếu")));
        Espresso.onView(withId(R.id.textView5)).check(ViewAssertions.matches(withText("Sắp chiếu")));
        Espresso.onView(withId(R.id.textView7)).check(ViewAssertions.matches(withText("Godzilla X Kong: Đế chế mới")));
        Espresso.onView(withId(R.id.textView8)).check(ViewAssertions.matches(withText("1 Giờ 55 phút")));
    }

    @Test
    public void testBackInButton() {
        onView(withId(R.id.backIn)).perform(ViewActions.click());
        onView(withId(R.id.managerActivity)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testImageViewDisplay() {
        onView(withId(R.id.imageView)).check(ViewAssertions.matches(isDisplayed()));
    }



//    @Test
//    public void testHorizontalScrollViewScroll() {
//        onView(withId(R.id.horizontalScrollView)).check(ViewAssertions.matches(isDisplayed()));
//    }
}
