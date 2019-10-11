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

    public String sXpEdit = "(//div[@class='d-none d-md-block']/div[@class='js-profile-editable-area']/div[@class='hide-sm hide-md']/button[@name='button'])";
    public String sXpSetStatus = "(//div[@class='d-inline-block text-gray-dark']/span[contains(.,'Set status')])";
    public String sXpRepositories = "(//a[contains(.,'Repositories')])";
    public String sXpRepository = "(//a[contains(.,'Your repositories')])";
    public String sXpEditProfile = "(//button[contains(.,'Edit profile')]))";

    @BeforeTest
    public void chromeSetup() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }

    @Test
    public void test() {
        driver.get("http://lazycoder.io/feedback    ");
        Assert.assertEquals(driver.getTitle(), "Feedback | Lazy Coder IO");
    }

    @AfterTest
    public void testTeardown() {
        driver.quit();
    }

    @AfterTest
    public void test1() {
        driver.quit();
    }
}
