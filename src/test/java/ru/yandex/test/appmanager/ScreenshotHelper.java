package ru.yandex.test.appmanager;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ScreenshotHelper {

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] makeScreenshot(WebDriver driver){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage image = Shutterbug.shootPage(driver).getImage();
            ImageIO.write(image, "png", baos);
            baos.flush();
        }
        catch (IOException e ){}
        return baos.toByteArray();
    }
}
