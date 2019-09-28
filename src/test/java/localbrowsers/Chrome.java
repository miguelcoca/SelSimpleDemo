package localbrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test(groups = {"mac", "windows"})
public class Chrome {
    private WebDriver driver;

    @BeforeTest
    public void chromeSetup(){
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }
    @Test
    public void test(){
        driver.get("http://lazycoder.io/feedback    ");
        Assert.assertEquals(driver.getTitle(), "Feedback | Lazy Coder IO");
    }

    @AfterTest
    public void testTeardown(){
        driver.quit();
    }
	
	@Test
    public void testing2(){
        driver.get("http://lazycoder.io/feedback    ");
        Assert.assertEquals(driver.getTitle(), "Feedback test2 ww | Lazy Coder IO");
    }
	
	
}
