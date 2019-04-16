package ru.cinemico.test.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ru.cinemico.test.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager applicationManager = new ApplicationManager();

    @BeforeTest
    public void setUp(){
        applicationManager.init();
    }

    @AfterTest
    public void tearDown(){
        applicationManager.stop();
    }


}
