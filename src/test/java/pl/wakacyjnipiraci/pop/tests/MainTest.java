package pl.wakacyjnipiraci.pop.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.wakacyjnipiraci.pop.ddt.ReadExcel;
import pl.wakacyjnipiraci.pop.pages.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MainTest {
    public WebDriver driver;
    public StartPage startPage;
    public LoginPage loginPage;
    public SignUpPage signUpPage;
    public DashboardPage dashboardPage;
    public FavoritePage favoritePage;
    public ResultPage resultPage;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }



    /**
     * TC1: Niepoprawna logowanie użytkownika w celu sprawdzenia
     * poprawności walidacji formularza do logowania
     */
    @Test(description = "Niepoprawne logowanie użytkownika w celu sprawdzenia \n" +
            "poprawności walidacji formularza do logowania", priority = 0)
    public void loginInvalidEmailTest(){
        String alertExpectedText = "E-mail ma niepoprawny format";
        ReadExcel data = new ReadExcel();
        String emailInvalidUser = data.getData("InvalidData",1,0);
        String passInvalidUser = data.getData("InvalidData",1,1);

        startPage = new StartPage(driver).openPage();
        startPage.checkAndCloseCookieConsent();
        startPage.clickAccount();
        loginPage = new AccountPage(driver).clickLogin();

        dashboardPage = loginPage.fillValidLoginForm(emailInvalidUser,passInvalidUser);

        Assert.assertEquals(loginPage.getAlertInvalidLoginEmail(),alertExpectedText);
    }

    /**
     * TC2: Próba rejestracji już istniejącego użytkownika
     */
    @Test(description = "Próba rejestracji już istniejącego użytkownika", priority = 1)
    public void signupExistingUserTest(){
        String alertExpectedText = "E-mail jest już w użyciu";
        ReadExcel data = new ReadExcel();
        String emailValidUser = data.getData("ValidData",1,0);
        String passValidUser = data.getData("ValidData",1,1);

        startPage = new StartPage(driver).openPage();
        startPage.checkAndCloseCookieConsent();
        startPage.clickAccount();
        signUpPage = new AccountPage(driver).clickSignUp();
        dashboardPage = signUpPage.fillValidSignUpForm(emailValidUser,passValidUser);

        Assert.assertEquals(signUpPage.getAlertExistingEmail(),alertExpectedText);
    }

    /**
     * TC3: Przejście do zakladki "Ulubione" na niezalogowanym użytkowniku
     */
    @Test(description = "Przejście do zakladki \"Ulubione\" na niezalogowanym użytkowniku", priority = 2)
    public void goToUnloggedUserFavorite(){
        String expectedText = "Zaloguj się, by zachować to, co cię inspiruje";
        String expectedTextAddition = "Moźesz dodać do 99 pozycji";

        startPage = new StartPage(driver).openPage();
        startPage.checkAndCloseCookieConsent();
        startPage.clickGoToLikePage();
        favoritePage = new FavoritePage(driver);

        Assert.assertEquals(favoritePage.getEmptyUnloggedLikesText(),expectedText);
        Assert.assertEquals(favoritePage.getEmptyUnloggedLikesTextAddition(),expectedTextAddition);
    }

    /**
     * TC4: Wpisanie zadanej frazy "Wyspy Kanaryjskie" w wyszukiwarkę
     */
    @Test(description = "Wpisanie zadanej frazy \"Wyspy Kanaryjskie\" w wyszukiwarkę", priority = 3)
    public void searchPhraseUsingInputSearchBar(){
        String phraseToSearch = "Wyspy Kanaryjskie";
        String expectedCounterResultText = "Ilość wyników: 8";

        startPage = new StartPage(driver).openPage();
        startPage.checkAndCloseCookieConsent();
        startPage.inputSearch(phraseToSearch);
        resultPage = new ResultPage(driver);

        Assert.assertEquals(resultPage.getResultCounterText(),expectedCounterResultText);
    }

    /**
     * TC5: Poprawne logowanie użytkownika dla podanych danych
     */
    @Test(description = "Poprawne logowanie użytkownika", priority = 4)
    public void loginValidTest(){
        String expectedTextLoggedUser = "Witaj!, martyna.jankowicz";
        ReadExcel data = new ReadExcel();
        String emailValidUser = data.getData("ValidData",1,0);
        String passValidUser = data.getData("ValidData",1,1);

        startPage = new StartPage(driver).openPage();
        startPage.checkAndCloseCookieConsent();
        startPage.clickAccount();
        dashboardPage = new LoginPage(driver).fillValidLoginForm(emailValidUser,passValidUser);

        Assert.assertEquals(dashboardPage.getUserNameText(),expectedTextLoggedUser);
        new LoginPage(driver).logOutUser();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}