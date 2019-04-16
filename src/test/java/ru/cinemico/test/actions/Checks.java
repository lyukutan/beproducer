package ru.cinemico.test.actions;

import org.openqa.selenium.WebElement;
import ru.cinemico.HamcrestHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static ru.cinemico.test.appmanager.ApplicationManager.WAIT_SLEEP;

public class Checks {

    public static void linkExists(String URLName) throws IOException {
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
        con.setRequestMethod("HEAD");
        HamcrestHelper.assertThat("Проверка, что HTTP Status-Code 200: OK", con.getResponseCode() == HttpURLConnection.HTTP_OK);
    }

    protected Boolean isDisplayed(WebElement element){
        try {
            element.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    protected void click(WebElement element) throws InterruptedException {
        element.click();
        Thread.sleep(WAIT_SLEEP);
    }

    protected void click(WebElement element, int coefficient) throws InterruptedException {
        element.click();
        Thread.sleep(WAIT_SLEEP * coefficient);
    }

}
