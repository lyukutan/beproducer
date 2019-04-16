package ru.cinemico.test.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.cinemico.test.tests.TestBase;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public final static int IMPLICITY_WAIT = 5;
    public final static int WAIT_SLEEP = 1500; // milisec


    public AuthorizationPage authorizationPage;
    public MainPage mainPage;

    protected WebDriver driver;


    public void init() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);

        authorizationPage = new AuthorizationPage(driver);
        mainPage = new MainPage(driver);

        driver.get(TestBase.URL);
        try {
            Thread.sleep(WAIT_SLEEP*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        driver.close();
    }

}
