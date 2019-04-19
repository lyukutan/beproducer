package ru.cinemico.test.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.cinemico.test.actions.Checks;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import static ru.cinemico.HamcrestHelper.assertThat;


public class MainPage extends Checks {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@class='header__logo']")
    private WebElement headerLogo;

    @FindBy(xpath = "//span[@class='header__auth--default-state']")
    private WebElement entrance;

    @FindBy(xpath = "//a[@class='header-languages__current']")
    private WebElement headerLanguages;

    @FindBy(xpath = "//a[@class='header-languages__list-link']")
    private List<WebElement> headerLanguagesList;

    @FindBy(xpath = "//span[contains(@class, 'swiper-pagination-bullet')]")
    private List<WebElement> swiperBullet;

    @FindBy(xpath = "//button[contains(@class, 'button__main-banners')]")
    private List<WebElement> buttonBanners;

    @FindBy(xpath = "//a[@class='about__full-link']")
    private WebElement aboutFullLink;

    @FindBy(xpath = "//a[@class='news__title']")
    private WebElement newsTitle;

    @FindBy(xpath = "//div[contains(@class, 'news-item news-item--size-sm ng-star-inserted')]")
    private List<WebElement> newsList;

    @FindBy(xpath = "//a[@class='footer__logo-link']")
    private WebElement footerLogo;

    @FindBy(xpath = "//a[@class='footer__menu-link']")
    private List<WebElement> footerMenuLink;

    @FindBy(xpath = "//div[@class='header-inner']")
    private WebElement headerNotMain;

    @FindBy(xpath = "//div[@class='page__content']")
    private WebElement contetnFAQ;

    @FindBy(xpath = "//div[@class='contacts__map']")
    private WebElement contactsMap;

    @FindBy(xpath = "//div[@class='contacts']")
    private WebElement contacts;

    @FindBy(xpath = "//div[contains(@class, 'project-banners')]")
    private List<WebElement> projectBanners;

    @FindBy(xpath = "//div[@class='page__content _with-sidebar']")
    private WebElement contentProject;

    @FindBy(xpath = "//div[@class='page__inner']")
    private WebElement errorPage;


    @Step("Проверка видимости полей на главной странице")
    public MainPage checkEnabledFieldsMainPage(){
        assertThat("Нет сообщения 404 NOT FOUND", !isDisplayed(errorPage) || (isDisplayed(errorPage) && !errorPage.getText().contains("404 NOT FOUND")));
        assertThat("Проверка видимости логотипа", headerLogo.isEnabled());
        assertThat("Проверка видимости кнопки \"Вход\"",  entrance.isEnabled());
        assertThat("Проверка видимости кнопки \"Язык\"",  headerLanguages.isEnabled());
        assertThat("Проверка наличия кнопок \"swiper\"",  !swiperBullet.isEmpty());
        assertThat("Проверка наличия кнопок \"Кнопок на баннерах\"",  !buttonBanners.isEmpty());
        assertThat("Проверка наличия кнопок \"Footer Menu \"",  toLowerCase(footerMenuLink.get(0).getText()).equals("faq")
                && toLowerCase(footerMenuLink.get(1).getText()).equals("новости и аналитика")
                && toLowerCase(footerMenuLink.get(2).getText()).equals("связаться с нами")
                && toLowerCase(footerMenuLink.get(3).getText()).equals("контакты"));
        assertThat("На главной странице нет header-inner",  !isDisplayed(headerNotMain));
        ScreenshotHelper.makeScreenshot(driver);
        return new MainPage(driver);
    }

    @Step("Проверка видимости полей на странице FAQ")
    public MainPage checkGetFAQ(){
        assertThat("Нет сообщения 404 NOT FOUND", !isDisplayed(errorPage) || (isDisplayed(errorPage) && !errorPage.getText().contains("404 NOT FOUND")));
        assertThat("На странице есть header-inner",  isDisplayed(headerNotMain));
        assertThat("На странице есть контент",  isDisplayed(contetnFAQ));
        assertThat("Проверка заголовка страницы", contetnFAQ.getText().contains("ЧАСТО ЗАДАВАЕМЫЕ ВОПРОСЫ"));
        ScreenshotHelper.makeScreenshot(driver);
        return new MainPage(driver);
    }

    @Step("Проверка видимости полей на странице контактов организации")
    public MainPage checkGetContacts(){
        assertThat("Нет сообщения 404 NOT FOUND", !isDisplayed(errorPage) || (isDisplayed(errorPage) && !errorPage.getText().contains("404 NOT FOUND")));
        assertThat("На странице есть header-inner",  isDisplayed(headerNotMain));
        assertThat("На странице загрузилась карта",  isDisplayed(contactsMap));
        assertThat("Проверка заголовка страницы", contacts.getText().contains("КОНТАКТЫ"));
        ScreenshotHelper.makeScreenshot(driver);
        return new MainPage(driver);
    }


    @Step("Проверка видимости полей на странице одного проекта")
    public MainPage checkGetProject() {
        assertThat("Нет сообщения 404 NOT FOUND", !isDisplayed(errorPage) || (isDisplayed(errorPage) && !errorPage.getText().contains("404 NOT FOUND")));
        assertThat("На странице нету header-inner",  !isDisplayed(headerNotMain));
        assertThat("На странице имеются баннеры",  projectBanners.size() > 0);
        assertThat("На странице есть контент",  isDisplayed(contentProject));
        assertThat("Проверка заголовка контента", contentProject.getText().contains("ОПИСАНИЕ ПРОЕКТА"));
        ScreenshotHelper.makeScreenshot(driver);
        return new MainPage(driver);
    }

    @Step("Переход на форму авторизации")
    public AuthorizationPage clickEntrance() throws InterruptedException {
        click(entrance);
        ScreenshotHelper.makeScreenshot(driver);
        return new AuthorizationPage(driver);
    }

}
