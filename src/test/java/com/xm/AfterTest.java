package com.xm;

import com.xm.lib.Init;
import io.cucumber.java.AfterAll;

public class AfterTest {

    @AfterAll
    public static void afterAll() {
        Init.dispose();
    }

}
