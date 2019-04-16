package ru.cinemico.test.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.cinemico.HamcrestHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public final static int IMPLICITY_WAIT = 10;

    public AuthorizationPage authorizationPage;
    public MainPage mainPage;

    protected WebDriver driver;

    public void linkExists(String URLName) throws IOException {
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
        con.setRequestMethod("HEAD");
        HamcrestHelper.assertThat("Проверка, что HTTP Status-Code 200: OK", con.getResponseCode() == HttpURLConnection.HTTP_OK);
    }

    public void init() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);

        authorizationPage = new AuthorizationPage(driver);
        mainPage = new MainPage(driver);

        driver.get("https://frontend.beproducer.pro/main");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        driver.close();
    }

}
