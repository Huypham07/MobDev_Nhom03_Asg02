package com.example.asg02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {CinemaSystemActivityUITest.class,
        InfoActivityUITest.class,
        LoginActivityUITest.class,
        ManagerActivityUITest.class,
        ManagerAddShowActivityUITest.class,
        ManagerAddHallActivityUITest.class,
        ManagerAddCinemaActivityUITest.class,
        ManagerChangePasswordUITest.class,
        ManagerEditAndDeleteActivityUITest.class,
        MoviesInCinemaActivityUITest.class}
)
public class ActivityTestSuite {
}
