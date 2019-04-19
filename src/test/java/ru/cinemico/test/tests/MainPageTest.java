package ru.cinemico.test.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.io.IOException;

import static ru.cinemico.test.actions.Checks.linkExists;

public class MainPageTest extends TestBase {

    @Epic(EPIC_BASIC_SCENARIO)
    @Feature(FEATURE_AUTHORIZATION)
    @Test(description = "Проверка url сайта")
    @Description("Открыть браузер  и развернуть на весь экран\n"           +
            "Перейти в Веб-приложение\n"                              +
            "Проверить статус код полученный при переходе по URL\n"   +
            "Проверить доступность элементов на главной странице\n")
    @Issue("-")
    public void availabilityURLs() throws IOException, InterruptedException {
        applicationManager.mainPage.checkEnabledFieldsMainPage();
        linkExists(URL);

        //страница часто задаваемых вопросов
        applicationManager.getUrl("faq");
        applicationManager.mainPage.checkGetFAQ();

        //страница контактов организации
        applicationManager.getUrl("contacts");
        applicationManager.mainPage.checkGetContacts();

        //страница одного проекта
        applicationManager.getUrl("project/1");
        applicationManager.mainPage.checkGetProject();

        //страница личного кабинета
        applicationManager.getUrl("personal");
        //applicationManager.mainPage.checkGetPersonal();
    }

}
