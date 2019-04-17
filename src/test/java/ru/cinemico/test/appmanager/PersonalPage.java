package ru.cinemico.test.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.cinemico.test.actions.Checks;

import static ru.cinemico.HamcrestHelper.assertThat;
import static ru.cinemico.test.tests.TestBase.URL;

public class PersonalPage extends Checks {
    private WebDriver driver;

    public PersonalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy(xpath = "//a[contains(@class, 'logout')]")
    private WebElement logout;

    @Step("Выход из Личного Кабинета")
    public MainPage clickLogout() throws InterruptedException {
        click(logout);
        assertThat("Перешли на главную страницу (проверка url)", driver.getCurrentUrl().equals(URL + "main"));
        return new MainPage(driver);
    }

}
