package pl.wakacyjnipiraci.pop.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.wakacyjnipiraci.pop.pages.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MainTest {
    public WebDriver driver;
    public StartPage startPage;
    public AccountPage accountPage;
    public LoginPage loginPage;
    public SignUpPage signUpPage;
    public DashboardPage dashboardPage;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    /**
     * TC1: Poprawne logowanie użytkownika dla podanych danych
     */
    @Test(description = "Poprawne logowanie użytkownika")
        public void loginValidTest(){
        String expectedTextLoggedUser = "Witaj!, martyna.jankowicz";

        startPage = new StartPage(driver).openPage();
        startPage.checkAndCloseCookieConsent();
        startPage.clickAccount();
        dashboardPage = new LoginPage(driver).fillValidLoginForm("martyna.jankowicz@gmail.com","Tester2022!");
        Assert.assertEquals(dashboardPage.getUserNameText(),expectedTextLoggedUser);
        new LoginPage(driver).logOutUser();
    }

    /**
     * TC2: Niepoprawna logowanie użytkownika w celu sprawdzenia
     * poprawności walidacji formularza do logowania
     */
    @Test(description = "Niepoprawne logowanie użytkownika w celu sprawdzenia \n" +
            "poprawności walidacji formularza do logowania")
    public void loginInvalidEmailTest(){
        String alertExpectedText = "E-mail ma niepoprawny format";

        startPage = new StartPage(driver).openPage();
        startPage.checkAndCloseCookieConsent();
        startPage.clickAccount();
        loginPage = new AccountPage(driver).clickLogin();
        dashboardPage = loginPage.fillValidLoginForm("martyna.jankowiczgmail.com","Tester2022!");
        Assert.assertEquals(loginPage.getAlertInvalidLoginEmail(),alertExpectedText);
    }

    /**
     * TC3: Próba rejestracji już istniejącego użytkownika
     */
    @Test(description = "Próba rejestracji już istniejącego użytkownika")
    public void signupExistingUserTest(){
        String alertExpectedText = "E-mail jest już w użyciu";

        startPage = new StartPage(driver).openPage();
        startPage.checkAndCloseCookieConsent();
        startPage.clickAccount();
        signUpPage = new AccountPage(driver).clickSignUp();
        dashboardPage = signUpPage.fillValidSignUpForm("martyna.jankowicz@gmail.com","Tester2022!");
        Assert.assertEquals(signUpPage.getAlertExistingEmail(),alertExpectedText);
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
