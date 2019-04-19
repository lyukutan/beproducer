package ru.cinemico.test.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ru.cinemico.test.actions.Checks.linkExists;
import static ru.cinemico.test.tests.TestBase.URL;

public class ApplicationManager {

    public final static int IMPLICITY_WAIT = 5;
    public final static int WAIT_SLEEP = 2000; // milisec


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

        driver.get(URL);
        try {
            Thread.sleep(WAIT_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ScreenshotHelper.makeScreenshot(driver);
    }

    public void getUrl(String dopUrl){
        String newUrl = URL + dopUrl;
        driver.get(newUrl);
        try {
            linkExists(newUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void stop(ITestResult result) {
        if(result.FAILURE == result.getStatus()){
            ScreenshotHelper.makeScreenshot(driver);
        }
        driver.close();
    }

}
