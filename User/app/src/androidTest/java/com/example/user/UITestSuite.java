package com.example.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {ChatActivityUITest.class,
        LoginActivityUITest.class,
        MainActivityUITest.class,
        RegisterActivityUITest.class}
)
public class UITestSuite {
}
