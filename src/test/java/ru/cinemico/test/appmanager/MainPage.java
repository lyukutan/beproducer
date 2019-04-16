package ru.cinemico.test.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.cinemico.HamcrestHelper;

import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class MainPage {
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

    @Step("Проверка видимости полей")
    public MainPage checkEnabledFields(){
        HamcrestHelper.assertThat("Проверка видимости логотипа", headerLogo.isEnabled());
        HamcrestHelper.assertThat("Проверка видимости кнопки \"Вход\"",  entrance.isEnabled());
        HamcrestHelper.assertThat("Проверка видимости кнопки \"Язык\"",  headerLanguages.isEnabled());
        HamcrestHelper.assertThat("Проверка наличия кнопок \"swiper\"",  !swiperBullet.isEmpty());
        HamcrestHelper.assertThat("Проверка наличия кнопок \"Кнопок на баннерах\"",  !buttonBanners.isEmpty());
        HamcrestHelper.assertThat("Проверка наличия кнопок \"Footer Menu \"",  toLowerCase(footerMenuLink.get(0).getText()).equals("faq")
                && toLowerCase(footerMenuLink.get(1).getText()).equals("новости и аналитика")
                && toLowerCase(footerMenuLink.get(2).getText()).equals("связаться с нами")
                && toLowerCase(footerMenuLink.get(3).getText()).equals("контакты"));
        ScreenshotHelper.makeScreenshot(driver);
        return new MainPage(driver);
    }

}
