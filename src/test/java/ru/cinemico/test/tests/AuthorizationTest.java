package ru.cinemico.test.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static ru.cinemico.test.actions.Checks.linkExists;

public class AuthorizationTest extends TestBase  {

    @DataProvider
    public Object[][] otherUsersDP() {
        return new Object[][]{
                {"test@test", "123", "investor", true},
                {"test@test1", "123", "producer", false},
                {"qwqw@mail.ru", "228", "investor", false}
        };
    }

    @DataProvider
    public Object[][] niceUserDP() {
        return new Object[][]{
                {"test@test", "123", "investor", true}
        };
    }

    @Epic(EPIC_BASIC_SCENARIO)
    @Feature(FEATURE_AUTHORIZATION)
    @Test(description = "Проверка доступности веб-интерфейса")
    @Description("Открыть браузер  и развернуть на весь экран\n"           +
                 "Перейти в Веб-приложение\n"                              +
                 "Проверить статус код полученный при переходе по URL\n"   +
                 "Проверить доступность элементов на главной странице\n")
    @Issue("-")
    public void availabilityWebInterface() throws IOException {
        linkExists(URL);
        applicationManager.mainPage.checkEnabledFields();
    }


    @Epic(EPIC_BASIC_SCENARIO)
    @Feature(FEATURE_AUTHORIZATION)
    @Test(dataProvider = "otherUsersDP", description = "Авторизация под различными ролями")
    @Description("Открыть браузер  и развернуть на весь экран\n"           +
                 "Перейти в Веб-приложение\n"                              +
                 "Переход на форму авторизации\n"                          +
                 "Заполнить поле E-mail\n"                                 +
                 "Заполнить поле пароль\n"                                 +
                 "Нажать кнопку Войти\n"                                   +
                 "Отображенные вкладки должны соответствовать роли" )
    @Issue("-")
    public void authorizationOtherUsers(String login, String pass, String role, Boolean niceOrNegativeUser) throws IOException, InterruptedException {
        applicationManager
                .mainPage.clickEntrance()
                .fillFields(login, pass)
                .clickSubmitAuthorization(role, niceOrNegativeUser);
    }

    @Epic(EPIC_BASIC_SCENARIO)
    @Feature(FEATURE_AUTHORIZATION)
    @Test(dataProvider = "niceUserDP", description = "Отмена авторизации")
    @Description("Открыть браузер  и развернуть на весь экран\n"           +
            "Перейти в Веб-приложение\n"                                   +
            "Переход на форму авторизации\n"                               +
            "Заполнить поле E-mail\n"                                      +
            "Заполнить поле пароль\n"                                      +
            "Закрыть форму авторизации"  )
    @Issue("-")
    public void cancellationAuthorization(String login, String pass, String role, Boolean niceOrNegativeUser) throws InterruptedException {
        applicationManager
                .mainPage.clickEntrance()
                .fillFields(login, pass)
                .clickCloseAuthorization();
    }

    @Epic(EPIC_BASIC_SCENARIO)
    @Feature(FEATURE_AUTHORIZATION)
    @Test(dataProvider = "niceUserDP", description = "Выход из ЛК")
    @Description("Открыть браузер  и развернуть на весь экран\n"           +
            "Перейти в Веб-приложение\n"                                   +
            "Переход на форму авторизации\n"                               +
            "Заполнить поле E-mail\n"                                      +
            "Заполнить поле пароль\n"                                      +
            "Выход из ЛК"  )
    @Issue("-")
    public void checkLogoutLK(String login, String pass, String role, Boolean niceOrNegativeUser) throws IOException, InterruptedException {
        applicationManager
                .mainPage.clickEntrance()
                .fillFields(login, pass)
                .clickSubmitAuthorization(role, niceOrNegativeUser)
                .clickLogout()
                .checkEnabledFields();
    }

    @Epic(EPIC_BASIC_SCENARIO)
    @Feature(FEATURE_AUTHORIZATION)
    @Test(dataProvider = "otherUsersDP", description = "Проверка восстановления пароля")
    @Description("Открыть браузер  и развернуть на весь экран\n"           +
            "Перейти в Веб-приложение\n"                                   +
            "Переход на форму авторизации\n" +
            "Нажатие на ссылку 'Напомнить?'\n" +
            "Заполнить обьязательные поля и нажать Восстановить\n" +
            "Проверка появившегося окна/сообщение об ошибке\n" +
            "Закрыть форму - перейти в главное меню\n" +
            "Повторно зайти на панель напоминания пароля\n" +
            "Проверка \"чистоты\" формы"                               )
    @Issue("-")
    public void restorePasswordEmail(String login, String pass, String role, Boolean niceOrNegativeUser) throws InterruptedException {
        applicationManager
                .mainPage.clickEntrance()
                .clickRestoreEmail()
                .fillFieldsForRestoreEmail(login, niceOrNegativeUser)
                .clickCloseAuthorization()
                .clickEntrance()
                .checkModalAuthIsDisplayes()
                .clickRestoreEmail();
    }
}
