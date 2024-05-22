package com.example.asg02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {AdminAddCinemaActivity2UITest.class,
        AdminAddCinemaActivityUITest.class,
        AdminAddEventActivityUITest.class,
        AdminCreateMovieActivityUITest.class,
        AdminMainActivityUITest.class,
        AdminManageEventActivityUITest.class,
        AdminMessageActivityUITest.class,
//        AdminMessagingActivityUITest.class,
        AdminShowEventActivityUITest.class,
        }
)
public class UITestSuite {
}
