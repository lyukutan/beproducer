package ru.yandex.test.appmanager;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.test.model.Monitor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.util.ArrayList;

public class FilterHelper {
    private int screenshotCount = 0;
    private WebDriver driver;

    public FilterHelper(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Дождаться загрузки")
    public void waitForLoad() {
        WebDriverWait waiter = new WebDriverWait(driver, 30, 100);
        waiter.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='preloadable__preloader preloadable__preloader_visibility_visible preloadable__paranja']"), 0));
        waiter.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='preloadable__preloader preloadable__preloader_visibility_visible preloadable__paranja']"), 0));
    }



    /*
    @Step("Сделать скриншот")
    // @Attachment(value = "Вложение", type = "image/png", fileExtension = ".png")
    public void makeScreenshot() throws Exception {
        //Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS, 500, true).withName("Result" + ++screenshotCount).save();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage image = Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).getImage();
        ImageIO.write(image, "png", baos);
        baos.flush();
       // scenario.embed(baos.toByteArray(), "image/png");
        Allure.addAttachment("Вложение",
                "image/png",
               // new ByteArrayInputStream(FileUtils.readFileToByteArray(new File("screenshots/Result" + screenshotCount + ".png"))),
                new ByteArrayInputStream(baos.toByteArray()),
                ".png");
    }
*/
    @Step("Отсортировать по цене")
    public void rateByCoast() throws InterruptedException {
        driver.findElement(By.xpath("//a[.='по цене']")).click();
        this.waitForLoad();
    }

    @Step("Устанивить производителя {manufacturer}")
    public void setManufacturer(String manufacturer) {
        driver.findElement(By.xpath("//fieldset[child::legend[.='Производитель']]//span[.='" + manufacturer + "']")).click();
    }

    @Step("Установить цвет {color}")
    public void setColor(String color) {
        driver.findElement(By.xpath("//fieldset[child::legend[.='Цвет']]//div[child::span[.='Цвет " + color.toLowerCase() + "']]")).click();
    }

    @Step("Установить цену от {price}")
    public void setMinPrice(String price) {
        driver.findElement(By.xpath("//*[@id='glpricefrom']")).clear();
        driver.findElement(By.xpath("//*[@id='glpricefrom']")).sendKeys(price);
    }

    @Step("Установить цену до {price}")
    public void setMaxPrice(String price) {
        driver.findElement(By.xpath("//*[@id='glpriceto']")).clear();
        driver.findElement(By.xpath("//*[@id='glpriceto']")).sendKeys(price);
    }

    @Step("Установить размер экрана {size}")
    public void setScreenSize(String size) {
        driver.findElement(By.xpath("//fieldset[child::legend[.='Размер экрана']]//span[.='" + size + "']")).click();
    }

    @Step("Установить разрешение {resolution}")
    public void setResolution(String resolution) {

        driver.findElement(By.xpath("//fieldset[./legend[text()='Макс. разрешение']]//span[text()='" + resolution + "']")).click();
    }

    @Step("Установить параметры из списка модели данных {monitors}")
    public void setMonitorParameters(ArrayList<Monitor> monitors) throws Exception {

        for (Monitor monitor : monitors) {
            this.resetParameters();
            this.setParametersFromClass(monitor);
            this.waitForLoad();
            ScreenshotHelper.makeScreenshot(driver);
        }
    }

    @Step("Сбросить фильтры")
    public void resetParameters() {
        driver.findElement(By.xpath("//span[text()='Все фильтры']")).click();
        driver.findElement(By.xpath("//button[./span[contains(text(),'Сбросить фильтр')]]")).click();
        driver.findElement(By.xpath("//a[./span[text()='Показать подходящие']]")).click();
        ScreenshotHelper.makeScreenshot(driver);


    }

    @Step("Установить параметры из модели данных {object}")
    public void setParametersFromClass(Object object) {
        if (object instanceof Monitor) {
            if (((Monitor) object).getPriceFrom() != null) {
                this.setMinPrice(((Monitor) object).getPriceFrom().toString());
            }

            if (((Monitor) object).getPriceTo() != null) {
                this.setMaxPrice(((Monitor) object).getPriceTo().toString());
            }

            if (((Monitor) object).getManufactorer() != null) {
                this.setManufacturer(((Monitor) object).getManufactorer());
            }

            if (((Monitor) object).getResolution() != null) {
                this.setResolution(((Monitor) object).getResolution());
            }

            if (((Monitor) object).getScreenSize() != null) {
                this.setScreenSize(((Monitor) object).getScreenSize());
            }

            if (((Monitor) object).getHdmi() != null) {
                this.setHdmi();
            }

            if (((Monitor) object).getAspectRatio() != null) {
                this.setAspectRatio(((Monitor) object).getAspectRatio());
            }
/*
           if ((Monitor) object).getMatrixType() != null) {
    this.setMatrixType(((Monitor) object).getMatrixType());
            }*/

            //ScreenshotHelper.makeScreenshot(driver);

        }
    }

    private void setMatrixType(String matrixType) {

    }

    @Step("Установить соотношение сторон {aspectRatio}")
    private void setAspectRatio(String aspectRatio) {
        driver.findElement(By.xpath("//fieldset[child::legend[.='Соотношение сторон']]//span[.='" + aspectRatio + "']")).click();
    }

    @Step("Установить HDMI")
    private void setHdmi() {
        driver.findElement(By.xpath("//fieldset[child::legend[.='Вход HDMI']]//span[.='Вход HDMI']")).click();
    }

    @Step("Удалить старые скриншоты")
    public void clearScreenshots() throws Exception {
        FileUtils.deleteDirectory(new File("screenshots"));
    }
}
