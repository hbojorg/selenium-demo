package automation;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        //System.setProperty("webdriver.gecko.driver", "drivers/fx/mac/geckodriver");
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/mac/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginLive() {
        driver.navigate().to("https://login.live.com");
        driver.findElement(By.name("loginfmt")).sendKeys("some email hotmail");
        driver.findElement(By.id("idSIButton9")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("passwd")).sendKeys("some pass");
        driver.findElement(By.id("idSIButton9")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String identity = driver.findElement(By.xpath("/html/body/div[1]/header/div[2]/section/div/div/header/div/div/div[4]/div[2]/div/div[2]/div/div[1]")).getText();
        Assert.assertTrue(StringUtils.isNotBlank(identity));
    }

    /*@Test
    public void loginAC() {
        driver.navigate().to("");
        driver.findElement(By.name("j_username")).sendKeys("test");
        driver.findElement(By.name("j_password")).sendKeys("test");
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //String identity = driver.findElement(By.xpath("/html/body/div/div[5]/div[2]/ul/li[1]/span")).getText();
        //Assert.assertTrue(identity.toLowerCase().contains(""));
    }*/

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
