package ru.cinemico.test.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.cinemico.test.actions.Checks;

import java.io.IOException;

import static ru.cinemico.HamcrestHelper.assertThat;
import static ru.cinemico.test.appmanager.ApplicationManager.WAIT_SLEEP;
import static ru.cinemico.test.tests.TestBase.URL;

public class AuthorizationPage extends Checks {
    private WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='form-authorize__title']")
    private WebElement titleAuthorize;

    @FindBy(xpath = "//input[@id='form-authorize-email']")
    private WebElement authorizeEmail;

    @FindBy(xpath = "//input[@id='form-authorize-password']")
    private WebElement authorizePassword;

    @FindBy(xpath = "//a[@class='form-authorize__forget-link']")
    private WebElement forgetLink;

    @FindBy(xpath = "//a[.='Зарегистрироваться']")
    private WebElement registration;

    @FindBy(xpath = "//div[@class='modal__close']")
    private WebElement close;

    @FindBy(xpath = "//div[@class='form-authorize__fail ng-star-inserted']")
    private WebElement authorizeFail;

    @FindBy(xpath = "//div[@class='form-authorize__submit']")
    private WebElement authorizeSubmit;


    @FindBy(xpath = "//div[contains(@class, 'modal--auth')]")
    private WebElement modalAuth;


    @Step("Заполнение полей авторизации")
    public AuthorizationPage fillFields(String email, String pass){
        authorizeEmail.sendKeys(email);
        authorizePassword.sendKeys(pass);
        return new AuthorizationPage(driver);
    }

    @Step("Закрытие окна авторизации")
    public MainPage clickCloseAuthorization() throws InterruptedException {
        click(close, 2); /** Страница не успевает прогружаться*/
        assertThat("Окно авторизации закрыто и не отображается", !isDisplayed(modalAuth));
        return new MainPage(driver);
    }

    @Step("Нажатие кнопки Войти в окне авторизации")
    public PersonalPage clickSubmitAuthorization(String role, Boolean flag) throws IOException, InterruptedException {
        click(authorizeSubmit);
        if (flag){
            Thread.sleep(WAIT_SLEEP);
            assertThat("Перешли на страницу Персонала (проверка url)", driver.getCurrentUrl().equals(URL + "personal/info"));
            linkExists(URL);
            //todo проверка появления страницы взависимости от роли
            return new PersonalPage(driver);
        } else {
            assertThat("Проверить, что система не приняла некорректные данные", authorizeFail.isDisplayed());
            //driver.close();
            return new PersonalPage(null);
        }
    }
}
