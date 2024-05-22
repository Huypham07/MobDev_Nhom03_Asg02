package com.example.asg02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {AddCinemaLogicTest.class,
        AddHallLogicTest.class,
        AddShowLogicTest.class,
        LoginControllerTest.class}
)
public class UnitTestSuite {
}
