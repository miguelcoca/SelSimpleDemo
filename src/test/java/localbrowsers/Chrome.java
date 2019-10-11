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

@Test(groups = {"mac", "windows"})
public class Chrome {
    private WebDriver driver;

    public String gTitleLocator = ".g-title";
    public String lButton = "//a[@href='https://seleniumaboveandbeyond.com' and contains(@class,'button')]";

    //Login page
    public String loginURL = "https://github.com/login";
    public String inputUserName = "login_field";
    public String sUserName = "marcelo_0614@hotmail.com";
    public String inputPassword = "password";
    public String sPassword = "xxxxxxxxx";
    public String buttonCommit = "//input[@name='commit']";
    public String UserInSession = "marcelo0614";
    public String ghUserName = "(//span[contains(text(),'" + UserInSession + "')])[8]";

    //My Repositories Page  // FIRST SCREENSHOOT
    public String myRepositoriesURL = "https://github.com/" + UserInSession + ";";
    public String setStatus = "//div[@class='d-inline-block text-gray-dark']";
    //public String setStatuscss = "div[class='d-inline-block text-gray-dark']>span[class='text-gray ml-2']";
    public String editProfile = "//div[@class='hide-sm hide-md']";
    //public String editProfilecss = "div[class='d-none d-md-block']>div[class='js-profile-editable-area']>div[class='hide-sm hide-md']>button[class='btn btn-block mt-2 mb-3 js-profile-editable-edit-button']";
    public String repositories = "//nav[@class='UnderlineNav-body']/child::a[2]";
    //public String repositoriesss = "a[class='UnderlineNav-item mr-0 mr-md-1 mr-lg-3 selected ']";
    public String user = "//summary[@aria-label='View profile and more']";
    //public String usercss = "div[class='Header-item position-relative mr-0 d-none d-lg-flex']>details[class='details-overlay details-reset']>summary[class='Header-link']>span[class='dropdown-caret']";
    public String yourRepositories = "//a[@data-ga-click='Header, go to repositories, text:your repositories']";
    //public String yourRepositoriesURLcss = "details-menu> a:nth-child(6)";
    public String arrayElements[] = {setStatus, editProfile, repositories, user, yourRepositories};

    //Project // SECOND SCREENSHOOT
    public String projectURL="https://github.com/miguelcoca/SelSimpleDemo/commit/e121f02527d3012cff94f5fd3d889adf5a310903";
    public String pullRequest="//span[contains(text(),'Pull request')]";
    //public String pullRequestcss= "svg[class='octicon octicon-git-pull-request']";
    public String symbol="(//td[@class='blob-num blob-num-expandable'])[1]";
    //public String symbolcss= "path[d='M10 6L7 3 4 6h2v6h2V6h2zm4 0c0-.55-.45-1-1-1h-2.5l1 1h1l-2 2H9v1h1.5l2 2H9v1h4c.55 0 1-.45 1-1l-2.5-2.5L14 6zM3.5 8H5v1H3.5l-2 2H5v1H1c-.55 0-1-.45-1-1l2.5-2.5L0 6c0-.55.45-1 1-1h2.5l-1 1h-1l2 2z']" ;
    public String plusSymbol="(//span[@data-code-marker='+'])[1]";
    //public String plusSymbolcss= "table[class='diff-table js-diff-table tab-size  '] > tbody>tr:nth-child(5)>td:nth-child(3)";
    public String browseFiles= "//a[@title='Browse the repository at this point in the history']";
    //public String browseFilescss= "a[title='Browse the repository at this point in the history']";
    public String labelCommit= "//div[@class='flex-auto no-wrap text-right']";
    //public String labelCommitcss= "div[class='flex-auto no-wrap text-right']";
    public String splitButton= "//a[@class='btn btn-sm BtnGroup-item']";
    //public String splitButtoncss= "a[class='btn btn-sm BtnGroup-item']";
    public String arrayElements2[] = {pullRequest, symbol, plusSymbol, browseFiles,labelCommit,splitButton};



    @BeforeTest
    public void chromeSetup() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
    }

    @Test
    public void login() {
        loginAction();
        Assert.assertNotNull(driver.findElement(By.xpath(ghUserName)));
    }

    @test //Verify if the elements are displayed in the First Screenshot, displaying Found/Not found in console
    public void identifyElementsMyRepos() {
        result = false
        int size=arrayElements.length;
        driver.navigate().to(myRepositoriesURL);
        for (int i; i <= size; i++) {
            result = thereIs(arrayElements[i])
            result = false;
        }
    }
    @test //Verify if the elements are displayed in the Second Screenshot, displaying Found/Not found in console
    public void identifyElementsHisRepo() {
        result = false;
        int size=arrayElements2.length;
        driver.navigate().to(myRepositoriesURL);
        for (int i; i <= size; i++) {
            result = thereIs(arrayElements2[i])
            System.out.println(result);
            result = false;
        }
    }
    public boolean thereIs(String a) {
        boolean isPresent = false;
        isPresent = driver.findElements(By.xpath(a)).size() > 0;
        if (isPresent) {
            System.out.println("Found");
        } else {
            System.out.println("Not found");
        }
        return isPresent;
    }

    private void loginAction() {
        driver.navigate().to(loginURL);
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
