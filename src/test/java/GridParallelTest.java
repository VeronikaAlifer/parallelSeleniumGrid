import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GridParallelTest {

    RemoteWebDriver driver;
    WebElement element;

    @BeforeTest
    @Parameters({"platform", "browserName", "remoteUrl"})
    public void set(String platform, String browserName, String remoteUrl) throws MalformedURLException {

        DesiredCapabilities cap = null;

        if (browserName.equals("firefox")) {
            cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");
        } else if (browserName.equals("internet explorer")) {
            cap = DesiredCapabilities.internetExplorer();
            cap.setBrowserName("internet explorer");
        }
        else if (browserName.equals("chrome")) {
            cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
        }

        cap.setPlatform(Platform.fromString(platform));
        driver = new RemoteWebDriver(new URL(remoteUrl), cap);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        //   driver.navigate().to("https://www.google.com");
        driver.get("https://www.google.com");
    }

    @Test
    public void googleSearch() throws InterruptedException {
        driver.findElement(By.name("q")).sendKeys("tut.by");
        driver.findElementByName("btnK").click();

    }

    @Test
    public void clickExecuteAutomation() {
        driver.findElementByPartialLinkText("Белорусский портал TUT.BY. Новости Беларуси и мира").click();
        Assert.assertTrue(true);
    }

}
