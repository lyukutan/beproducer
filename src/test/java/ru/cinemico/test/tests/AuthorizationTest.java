package ru.cinemico.test.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.io.IOException;

public class AuthorizationTest extends TestBase  {

    @Epic("Базовые проверки")
    @Feature("Авторизация в системе")
    @Test(description = "Проверка доступности веб-интерфейса")
    @Description(
            "•Открыть браузер  и развернуть на весь экран.\n" +
            "•Перейти в Веб-приложение\n" +
            "•Проверить статус код полученный при переходе по URL\n" +
            "•Проверить доступность элементов на главной странице\n")
    @Issue("-")
    public void AvailabilityWebInterface() throws IOException {
        applicationManager.linkExists("https://frontend.beproducer.pro/main");
        applicationManager.mainPage.checkEnabledFields();

    }

}
