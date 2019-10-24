package localbrowsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Test(groups = {"mac", "windows"})
public class Chrome {
    private WebDriver driver;

    public String gTitleLocator=".g-title";
    public String lButton = "//a[@href='https://seleniumaboveandbeyond.com' and contains(@class,'button')]";
    public String inputUserName = "login_field";
    public String sUserName = "redridehell@gmail.com";
    public String inputPassword = "password";
    public String sPassword = "noVoyAEscribirMiPasswd";
    public String buttonCommit = "//input[@name='commit']";
    public String ghUserName="(//span[contains(text(),'miguelcoca')])[8]";

    @BeforeTest
    public void chromeSetup(){
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }

    @Test
    public void gitLogin() {
        driver.get("https://github.com");
        WebElement singIn = driver.findElement(By.cssSelector("a[href='/login']"));
        singIn.click();
        WebElement username = driver.findElement(By.xpath("//*[contains(@id, 'login_field')]"));
        WebElement password = driver.findElement(By.xpath("//*[contains(@id, 'password')]"));
        WebElement login = driver.findElement(By.xpath("//input[@value = 'Sign in']"));
        username.sendKeys("jhudy.delgadillo@gmail.com");
        password.sendKeys("Passs");
        login.click();
        WebElement userGit = driver.findElement(By.xpath("(//*[contains(text(),'jhudy')])[1]"));
        Assert.assertNotNull(userGit);
    }

    @Test
    public void taskModule1() {
        driver.manage().window().maximize();
        gitLogin();
        WebElement project = driver.findElement(By.xpath("(//span[contains(text(),'SelSimpleDemo')])[2]")); //my project
        project.click();
        WebElement localBrowser = driver.findElement(By.xpath("//span[@class='simplified-path']"));
        localBrowser.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement fileJava = driver.findElement(By.xpath("(//a[contains(text(),'java')])[1]"));
        fileJava.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String infoFile = driver.findElement(By.xpath("//span[@class='file-mode']/parent::div")).getText();
        WebElement editOption = driver.findElement(By.xpath("//button[@class='btn-octicon tooltipped tooltipped-nw']"));
        editOption.click();
        System.out.println(infoFile);

    }

    @Test
    public void setProfile() {
        gitLogin();
        WebElement optionsUser = driver.findElement(By.xpath("(//span[@class='dropdown-caret'])[2]"));
        optionsUser.click();
        WebElement userProfile = driver.findElement(By.xpath("//a[text()='Your profile']"));
        userProfile.click();
        WebElement setProfile = driver.findElement(By.xpath("(//button[ text()='Edit profile'])[1]"));
        setProfile.click();
        WebElement bioUser = driver.findElement(By.xpath("//textarea[@name='user[profile_bio]']"));
        Assert.assertNotNull(bioUser);
    }

    @Test
    public void setStatus(){
        gitLogin();
        WebElement optionsUser = driver.findElement(By.xpath("(//span[@class='dropdown-caret'])[2]"));
        optionsUser.click();
        WebElement userProfile = driver.findElement(By.xpath("//a[text()='Your profile']"));
        userProfile.click();
        WebElement setStatus = driver.findElement(By.xpath("(//span[text()='Set status'])[2]"));
        setStatus.click();
        WebElement formEditStatus = driver.findElement(By.xpath("(//form[@class='position-relative flex-auto js-user-status-form'])[2]"));
        Assert.assertNotNull(formEditStatus);
        System.out.println("llego"+ formEditStatus);
    }

    @Test
    public void pullRequestsList() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://github.com/miguelcoca/SelSimpleDemo/commit/4a8f0ff5e879d687b24c9b39f17ffdf6358c6952");
        WebElement pullRequestsTab = driver.findElement(By.xpath("//span[text()='Pull requests']"));
        pullRequestsTab.click();
        Thread.sleep (1000); // to be able to see the driver procedure calmly and find items that are not immediately available
        WebElement requestsList = driver.findElement(By.xpath("//*[@id='js-issues-toolbar']"));
        Assert.assertNotNull(requestsList);
    }

    @Test
    public void browseFiles() throws InterruptedException {
        driver.manage().window().maximize();
        gitLogin();
        WebElement project = driver.findElement(By.xpath("(//a[@href='/miguelcoca/SelSimpleDemo'])[1]"));
        project.click();
        WebElement commitsTab = driver.findElement(By.xpath("//a[@href='/miguelcoca/SelSimpleDemo/commits/master']"));
        commitsTab.click();
        Thread.sleep (1000);   // it is necessary to find the next element
        WebElement selectCommit = driver.findElement(By.xpath("//*[contains(text(),'couple sample tests added.')]"));
        selectCommit.click();
        Thread.sleep (1000);  // it is necessary to find the next element
        WebElement buttonBrowseFiles = driver.findElement(By.xpath("//a[text()='Browse files']"));
        buttonBrowseFiles.click();
        WebElement tableFiles = driver.findElement(By.xpath("//table[@class='files js-navigation-container js-active-navigation-container']"));
        Assert.assertNotNull(tableFiles);
    }

    @Test
    public void login(){
        loginAction();
        Assert.assertNotNull(driver.findElement(By.xpath(ghUserName)));
    }

    private void loginAction() {
        driver.navigate().to("https://github.com/login");

        WebElement InputUserName = driver.findElement(By.id(inputUserName));
        InputUserName.sendKeys(sUserName);

        WebElement InputPassword = driver.findElement(By.id(inputPassword));
        InputPassword.sendKeys(sPassword);

        driver.findElement(By.xpath(buttonCommit)).click();
    }

    @Test
    public void test(){
        driver.get("http://lazycoder.io/feedback");
        Assert.assertEquals(driver.getTitle(), "Feedback | Lazy Coder IO");
    }

    @Test
    public void testGTitle(){
        driver.get("http://lazycoder.io");
        WebElement gTitle = driver.findElement(By.cssSelector(gTitleLocator));
        Assert.assertTrue(gTitle.getText().contains("FROM THE BLOG"));
    }

    @Test
    public void clickLearnButotn(){
        driver.get("http://lazycoder.io");

        List<WebElement> buttons = driver.findElements(By.xpath(lButton));

        for(WebElement button:buttons){
            if(button.getText().contains("LEARN")){
                //we should interact with the element that matches our criteria.
                //button.click();
            }

            button.click();
            break;
        }
    }

    @AfterTest
    public void testTeardown(){
        driver.quit();
    }
}
