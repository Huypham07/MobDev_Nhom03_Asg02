package com.example.admin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {CreateEventLogicTest.class,
        CreateMovieLogicTest.class}
)
public class UnitTestSuite {
}
