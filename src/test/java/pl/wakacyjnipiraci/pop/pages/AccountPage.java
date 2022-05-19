package pl.wakacyjnipiraci.pop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private WebDriver driver;
    @FindBy (xpath = "//div[contains(text(),'Zaloguj się')]")
    private WebElement loginTab;
    @FindBy (xpath = "//div[@class='hp__sc-dx1qy-5 dGCRpF']")
    private WebElement signupTab;


    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Metoda odpowiadajaca za przejscie do widoku formularza logowania
     * @return
     */
    public LoginPage clickLogin(){
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.elementToBeClickable(loginTab));
        loginTab.click();
        return new LoginPage(driver);
    }
    /**
     * Metoda odpowiadajaca za przejscie do widoku formularza rejestracji
     * @return
     */
    public SignUpPage clickSignUp(){
        new WebDriverWait(driver, Duration.ofSeconds(7)).until(ExpectedConditions.elementToBeClickable(signupTab));
        signupTab.click();
        return new SignUpPage(driver);
    }

}
