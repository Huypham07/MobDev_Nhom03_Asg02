package com.example.asg02;

import com.example.asg02.controller.account.LoginLogicTest;
import com.example.asg02.controller.account.RegisterLogicTest;
import com.example.asg02.controller.account.UpdateAccountLogicTest;
import com.example.asg02.controller.booking.GetBookingLogicTest;
import com.example.asg02.controller.cinema.GetCinemaLogicTest;
import com.example.asg02.controller.event.GetAllEventLogicTest;
import com.example.asg02.controller.movie.GetMovieLogicTest;
import com.example.asg02.controller.review.GetReviewLogicTest;
import com.example.asg02.controller.show.GetShowLogicTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {LoginLogicTest.class,
        RegisterLogicTest.class,
        UpdateAccountLogicTest.class,
        GetBookingLogicTest.class,
        GetCinemaLogicTest.class,
        GetAllEventLogicTest.class,
        GetMovieLogicTest.class,
        GetReviewLogicTest.class,
        GetShowLogicTest.class}
)
public class UnitTestSuite {
}
