package com.example.admin;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@LargeTest
public class AdminCreateMovieActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminCreateMovieActivity> activityRule =
            new ActivityScenarioRule<>(AdminCreateMovieActivity.class);

    @Test
    public void testUIComponentsDisplayed() {
        Espresso.onView(withId(R.id.enterMovieName)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.selectPoster)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterReleasedDate)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterDurationMins)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterDescription)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterCensor)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterGenre)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterDirector)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterActors)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterLanguage)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.enterTrailerLink)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.addCinemaBtn)).perform(ViewActions.scrollTo()).check(matches(isDisplayed()));
    }

    @Test
    public void testEditTextHint() {
        Espresso.onView(withId(R.id.enterMovieName)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Nhập tên phim")));
        Espresso.onView(withId(R.id.enterReleasedDate)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Ngày công chiếu")));
        Espresso.onView(withId(R.id.enterDurationMins)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Thời lượng phim")));
        Espresso.onView(withId(R.id.enterDescription)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Mô tả")));
        Espresso.onView(withId(R.id.enterCensor)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Kiểm duyệt")));
        Espresso.onView(withId(R.id.enterGenre)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Thể loại")));
        Espresso.onView(withId(R.id.enterDirector)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Đạo diễn")));
        Espresso.onView(withId(R.id.enterActors)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Diễn viên")));
        Espresso.onView(withId(R.id.enterLanguage)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Ngôn ngữ")));
        Espresso.onView(withId(R.id.enterTrailerLink)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.withHint("Link trailer")));
    }

    @Test
    public void testButtonEnabled() {
        // Kiểm tra xem nút thêm có bị vô hiệu hóa ban đầu không
        Espresso.onView(withId(R.id.addCinemaBtn)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.isNotEnabled()));

        Espresso.onView(withId(R.id.enterMovieName)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("Tên phim"));
        Espresso.onView(withId(R.id.enterReleasedDate)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("01/01/2024"));
        Espresso.onView(withId(R.id.enterDurationMins)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("120"));
        Espresso.onView(withId(R.id.enterDescription)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("Mô tả phim"));
        Espresso.onView(withId(R.id.enterCensor)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("T16"));
        Espresso.onView(withId(R.id.enterGenre)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("Hành động"));
        Espresso.onView(withId(R.id.enterDirector)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("Đạo diễn"));
        Espresso.onView(withId(R.id.enterActors)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("Diễn viên"));
        Espresso.onView(withId(R.id.enterLanguage)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("Tiếng Anh"));
        Espresso.onView(withId(R.id.enterTrailerLink)).perform(ViewActions.scrollTo()).perform(ViewActions.replaceText("http://example.com"));
    }
}
