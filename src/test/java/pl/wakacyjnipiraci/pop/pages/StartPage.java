package pl.wakacyjnipiraci.pop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage {
    private WebDriver driver;
    @FindBy (id = "tarteaucitronPersonalize")
    private WebElement cookieButton;
    @FindBy (xpath = "//body/div[@id='hp-app']/div[2]/div[1]/div[3]/div[1]/div[1]/img[1]")
    private WebElement accountImg;
    @FindBy (xpath = "//body/div[@id='hp-app']/div[2]/div[1]/div[3]/a[1]/span[1]")
    private WebElement favoriteHeartBar;
    @FindBy (id = "searchInput")
    private WebElement searchInput;
    @FindBy (xpath = "//body/div[@id='hp-app']/div[3]/div[1]/button[1]")
    private WebElement inputButtonSubmit;


    public StartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public StartPage openPage(){
        driver.get("https://www.wakacyjnipiraci.pl/");
        return new StartPage(driver);
    }

    /**
     * metoda sprawdza czy jest belka ze zgodą cookie i ja wyłącza
     */
    public void checkAndCloseCookieConsent() {
        if (cookieButton.isDisplayed()) {
            System.out.println("weszlo");
            cookieButton.click();
        }
    }
    /**
     * przejscie do widoku okna gdzie mozna się zalogować lub zarejestrować
     */
    public AccountPage clickAccount(){
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.elementToBeClickable(accountImg));
        accountImg.click();
        return new AccountPage(driver);
    }

    /**
     * Kliknięcie i przejście do strony z ulubionymi ofertami, artykulami
     */
    public FavoritePage clickGoToLikePage(){
        favoriteHeartBar.click();
        return new FavoritePage(driver);
    }

    /**
     * wpisywanie zadanej frazy w wyszukiwarce
     */
    public ResultPage inputSearch(String textPhrase){
            searchInput.sendKeys(textPhrase);
            inputButtonSubmit.click();
        return new ResultPage(driver);
    }
}
