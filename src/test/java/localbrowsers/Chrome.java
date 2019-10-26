package localbrowsers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;
@Test(groups = {"mac", "windows"})
public class Chrome {
    private WebDriver driver;
    public String gTitleLocator=".g-title";
    public String lButton = "//a[@href='https://seleniumaboveandbeyond.com' and contains(@class,'button')]";
    public String inputUserName = "login_field";
    public String sUserName = "daniel-sj77@hotmail.com";
    public String inputPassword = "password";
    public String sPassword = "miPasswordSecreto";
    public String buttonCommit = "//input[@name='commit']";
    public String ghUserName="(//span[contains(text(),'danieldas')])[8]";
    //edit profile
    public String buttonEdit = "(//button[(text()='Edit profile') and (@name='button')])[1]";
    public String buttonGuardarProfile = "//button[@class='btn btn-sm btn-primary']";
    //set status
    public String aSetStatus = "//div[@class='d-inline-block text-gray-dark']/child:: span";
    public String inputEstado = "(//input[@name='message'])[2]";
    public String buttonGuardarEstado = "(//button[@class='width-full btn btn-primary mr-2 js-user-status-submit'])[2]";
    public String sEstado = "hola";
    //drop repositories
    public String inputBuscarRep="//input[@id='your-repos-filter']";
    public String imgAvatar="(//img[@class='avatar'])[2]";
    public String dropRep="//a[(contains(@class,'drop')) and (@href='/danieldas?tab=repositories')]";
    //browse files
    public String aBrowse="//a[(text()='Browse files')]";
    public String summaryClone="//summary[@class='btn btn-sm ml-2 btn-primary']";

/*
-Tab Pull requests
   Xpath:    //a[(contains(@class,'js')) and (@href='/miguelcoca/SelSimpleDemo/pulls')]
   Css:      a[href='/miguelcoca/SelSimpleDemo/pulls']
-Boton Browse Files
   Xpath:   //a[(text()='Browse files')]
   Css:     a[title='Browse the repository at this point in the history']
-1 parent ....
   Xpath:   //div[(@class='flex-auto no-wrap text-right')]
   Css:     div[class='flex-auto no-wrap text-right']
-Boton Split
   Xpath:   //a[contains(text(),'Split')]
   Css:     a[href='https://github.com/miguelcoca/SelSimpleDemo/commit/4a8f0ff5e879d687b24c9b39f17ffdf6358c6952?diff=split']
-Expand all
   Xpath:   //a[contains(@title,'Expand All')]
   Css:     a[title='Expand All']
-Boton Edit profile
   Xpath:   (//button[(text()='Edit profile') and (@name='button')])[1]
   Css:     div.d-none div.js-profile-editable-area div.hide-sm button[name='button']
-Set status
   Xpath:   (//span[(text()='Set status')])[2]
   Css:     div.pt-1.ws-normal div.d-inline-block.text-gray-dark span.text-gray.ml-2
-Tab repositories
   Xpath:   //a[(contains(@class,'UnderlineNav')) and (@href='/danieldas?tab=repositories')]
   Css:     a.UnderlineNav-item[href='/danieldas?tab=repositories']
-Menu Your repositories
   Xpath:   //a[(contains(@class,'drop')) and (@href='/danieldas?tab=repositories')]
   Css:     a.dropdown-item[href='/danieldas?tab=repositories']
*/


    public String aSignIn="//a[(@href='/login')]";
    public String aSimpleDemo="(//a[(@href='/danieldas/SelSimpleDemo')])[1]";
    public String aLocalBrowsers="//a[(@href='/danieldas/SelSimpleDemo/tree/master/src/test/java/localbrowsers')]";
    public String aChrome="//a[(@href='/danieldas/SelSimpleDemo/blob/master/src/test/java/localbrowsers/Chrome.java')]";
    public String divLines="//div[contains(@class,'text-mono')]";
    public String cantidadLineas, cantidadLineas2;
    public String btnEdit="//button[(@class='btn-octicon tooltipped tooltipped-nw')]";
    public String divPadre="//div[@class = 'CodeMirror-sizer']";

    @BeforeTest
    public void chromeSetup(){
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }
    @Test
    public void examen(){
        driver.navigate().to("https://github.com");
        driver.findElement(By.xpath(aSignIn)).click();
        loginAction();
//ingreso al proyecto
        driver.findElement(By.xpath(aSimpleDemo)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//local browsers
        driver.findElement(By.xpath(aLocalBrowsers)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//archivo chrome con contador de lineas
        driver.findElement(By.xpath(aChrome)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        cantidadLineas=driver.findElement(By.xpath(divLines)).getText();
        cantidadLineas=cantidadLineas.substring(16);
        cantidadLineas=cantidadLineas.substring(0, 3);
//Click en edit
        driver.findElement(By.xpath(btnEdit)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//Cantidad lineas edit
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement webElement = driver.findElement(By.xpath(divPadre));
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({block: 'end'});", webElement);
        cantidadLineas2 = driver.findElement(By.cssSelector(".CodeMirror-code>div:last-child>div>div")).getText();
//Verificacion final
        Assert.assertEquals(cantidadLineas, cantidadLineas2);
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
    public void editProfile(){
        loginAction();
        driver.navigate().to("https://github.com/danieldas");
        driver.findElement(By.xpath(buttonEdit)).click();
        driver.findElement(By.xpath(buttonGuardarProfile)).click();
    }
    @Test
    public void setStatus(){
        loginAction();
        driver.navigate().to("https://github.com/danieldas");
        driver.findElement(By.xpath(aSetStatus)).click();

        WebElement InputEstado = driver.findElement(By.xpath(inputEstado));
        InputEstado.sendKeys(sEstado);
        driver.findElement(By.xpath(buttonGuardarEstado)).click();
    }
    @Test
    public void dropRepositories(){
        loginAction();
        driver.navigate().to("https://github.com/danieldas");
        driver.findElement(By.xpath(imgAvatar)).click();
        driver.findElement(By.xpath(dropRep)).click();
        Assert.assertNotNull(driver.findElement(By.xpath(inputBuscarRep)));
    }
    @Test
    public void browseFiles(){
        loginAction();
        driver.navigate().to("https://github.com/miguelcoca/SelSimpleDemo/commit/4a8f0ff5e879d687b24c9b39f17ffdf6358c6952?diff=unified");
        driver.findElement(By.xpath(aBrowse)).click();
        Assert.assertNotNull(driver.findElement(By.xpath(summaryClone)));
    }
    @Test
    public void test(){
        driver.get("https://www.youtube.com/");
        Assert.assertEquals(driver.getTitle(), "YouTube");
    }
    @Test
    public void test2(){
        driver.get("https://www.youtube.com/?hl=es-419");
        Assert.assertEquals(driver.getTitle(), "YouTube");
    }
    @AfterTest
    public void testTeardown(){
        driver.quit();
    }
}