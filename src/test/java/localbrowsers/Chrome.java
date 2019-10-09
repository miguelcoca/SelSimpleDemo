package localbrowsers;
/* expresiones Css y XPath
1: boton Edit Profile
(//button[contains(@class,'js-profile-editable-edit-button')])[1]
.js-profile-editable-edit-button
2.Set Status
(//span[contains(text(),'Set status') ])[2]
.pt-1.ws-normal span
3. Tab repositories
//a[contains(text(),'Repositories' )]
.UnderlineNav-item[href*='tab=repositories' ]
4. your repositories
//a[contains(text(),'Your repositories')]
.dropdown-item[href*='tab=repositories']
5.Browse
//a[contains(text(),'Browse files')]
.btn-outline[title*=Browse]

6. pull request
 //span[contains(text(),'Pull request')]
.reponav-item[href*='pulls']
7. split boton
//a[contains(text(),'Split')]
.BtnGroup-item[href*=split]
8.icono lateral isquierdo
(//a[contains(@href,'diff')])[6]
.single-expander
9. parent y comit de la parte superior
//div[contains(@class,'flex-auto no-wrap')]
.flex-auto.no-wrap.text-right

10. signo + de lateriañ isquierdo
(//span[contains(@data-code-marker,'+')])[1]
span.blob-code-inner.blob-code-marker[data-code-marker='+']
*
* */
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
    public String gTitleLocator = ".g-title";
    public String lButton = "//a[@href='https://seleniumaboveandbeyond.com' and contains(@class,'button')]";
    public String repositorio= "//a[@href='/miguelcoca?tab=repositories']";

    public String inputUserName = "login_field";
    public String sUserName= "williamh.gutierrezb@gmail.com";
    public String inputPassword= "password";
    public String sPassword = "Admin_1234*";
    public String buttonCommit="//input[@name='commit']";
    public String ghUserName ="(//span[contains(text(),'william')])[2]";


    public String URLsesion = "https://github.com/login";
    public String URLgit= "https://github.com/williamgutierrez";
    public String URLdemo="https://github.com/williamgutierrez/SelSimpleDemo";
    public String expSetStatus ="(//span[contains(text(),'Set status') ])[2]";
    public String expEditProfile ="(//button[contains(@class,'js-profile-editable-edit-button')])[1]";
    public String expRepositories = "//a[contains(text(),'Repositories' )]";
    public String expPullRequeest = "//span[contains(text(),'Pull request')]";



    @BeforeTest
    public void chromeSetup() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }



    @Test
    public void met_setStatus(){
        loginAction();
        driver.navigate().to(URLgit);
        WebElement titulo = driver.findElement(By.xpath(expSetStatus));
        boolean paso= titulo.getText().contains("Set status");
        Assert.assertTrue(paso);
    }

    @Test
    public void met_setEditProfile(){
        loginAction();
        driver.navigate().to(URLgit);
        driver.findElement(By.xpath(expEditProfile)).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Test
    public void test() {
        driver.get("http://lazycoder.io/feedback");
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Feedback | Lazy Coder IO");

    }
    @Test
    public void testGTitle() {
        driver.get("http://lazycoder.io");
        WebElement gTitle = driver.findElement(By.cssSelector(gTitleLocator));
        System.out.println(gTitle.getText());
        Assert.assertTrue(gTitle.getText().contains("FROM THE BLOG"));
    }



    @Test
    private void loginAction(){
        driver.navigate().to(URLsesion);
        WebElement InputUserName = driver.findElement(By.id(inputUserName));
        InputUserName.sendKeys(sUserName);

        WebElement InputPassword=  driver.findElement(By.id(inputPassword));
        InputPassword.sendKeys(sPassword);

        driver.findElement(By.xpath(buttonCommit)).click();
        Assert.assertNotNull(driver.findElement(By.xpath(ghUserName)));

    }


    @Test
    public void clickLearnButotn(){
        driver.get("https://lazycoder.io/");

        List<WebElement> buttons = driver.findElements(By.xpath(lButton));
        System.out.println(buttons);
        for(WebElement button:buttons){
            if(button.getText().contains("LEARN")){
                //we should interact with the element that matches our criteria.
                button.click();
            }

//            button.click();
            break;
        }
    }



    @AfterTest
    public void testTeardown() {
        driver.quit();
    }
	


}
