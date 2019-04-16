package ru.cinemico.test.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

public class AuthorizationTest extends TestBase  {

    @Epic("базовые проверки")
    @Feature("Авторизация в системе")
    @Test(description = "Проверка доступности веб-интерфейса")
    @Description(
            "•\tОткрыть браузер  и развернуть на весь экран. \n" +
            "•\tПерейти в Веб-приложение\n" +
            "•\tПроверить доступность элементов на главной странице\t\t\n")
    @Issue("-")
    public void AvailabilityWebInterface(){
        applicationManager.mainPage.checkEnabledFields();
    }

}
