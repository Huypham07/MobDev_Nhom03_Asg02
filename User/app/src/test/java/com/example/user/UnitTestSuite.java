package com.example.user;

import com.example.user.controller.account.LoginLogicTest;
import com.example.user.controller.account.RegisterLogicTest;
import com.example.user.controller.account.UpdateAccountLogicTest;
import com.example.user.controller.booking.GetBookingLogicTest;
import com.example.user.controller.cinema.GetCinemaLogicTest;
import com.example.user.controller.event.GetAllEventLogicTest;
import com.example.user.controller.movie.GetMovieLogicTest;
import com.example.user.controller.province.GetProvinceLogicTest;
import com.example.user.controller.review.GetReviewLogicTest;
import com.example.user.controller.show.GetShowLogicTest;
import com.example.user.view.chat.ChatLogicTest;

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
        GetShowLogicTest.class,
        ChatLogicTest.class,
        GetProvinceLogicTest.class}
)
public class UnitTestSuite {
}
