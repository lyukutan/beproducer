package ru.cinemico.test.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.cinemico.test.appmanager.ApplicationManager;

public class TestBase {

    protected static final String EPIC_BASIC_SCENARIO = "Базовые проверки";
    static final String FEATURE_AUTHORIZATION = "Авторизация в системе";
    public static final String URL = "https://frontend.beproducer.pro/";

    protected final ApplicationManager applicationManager = new ApplicationManager();

    @BeforeMethod
    public void setUp(){
        applicationManager.init();
    }

    @AfterMethod
    public void tearDown(){
        applicationManager.stop();
    }


}
