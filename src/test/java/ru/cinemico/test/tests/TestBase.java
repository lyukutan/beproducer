package ru.cinemico.test.tests;

import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.cinemico.test.appmanager.ApplicationManager;

public class TestBase {

    protected static final String EPIC_BASIC_SCENARIO = "Smoke проверки";
    static final String FEATURE_AUTHORIZATION = "Авторизация в системе";
    public static final String URL = "https://frontend.beproducer.pro/";

    protected final ApplicationManager applicationManager = new ApplicationManager();

    @BeforeMethod
    @Step("Инициализация драйвера, переход на web-ресурс")
    public void setUp(){
        applicationManager.init();
    }

    @AfterMethod
    @Step("Закрытие драйвера (скриншот, если ошибка)")
    public void tearDown(ITestResult result){
        applicationManager.stop(result);
    }


}
