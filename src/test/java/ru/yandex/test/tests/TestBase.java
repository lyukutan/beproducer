package ru.yandex.test.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import ru.yandex.test.appmanager.ApplicationManager;

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
