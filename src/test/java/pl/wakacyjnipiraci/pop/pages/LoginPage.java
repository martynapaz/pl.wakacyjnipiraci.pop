package pl.wakacyjnipiraci.pop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    @FindBy(id = "login-email")
    private WebElement loginEmail;
    @FindBy (id = "login-password")
    private WebElement loginPassword;
    @FindBy (xpath = "//button[contains(text(),'Zaloguj się')]")
    private WebElement loginButton;
    @FindBy (xpath = "//div[contains(text(),'E-mail ma niepoprawny format')]")
    private WebElement alertInvalidEmailFormat;
    @FindBy (xpath = "//body/div[@id='hp-app']/div[2]/div[1]/div[3]/div[1]/div[1]/img[1]")
    private WebElement userAccountImg;
    @FindBy (xpath = "//div[contains(text(),'Wyloguj')]")
    private WebElement logOutUserLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Metoda odpowiadajaca za wypelnienie formularza logowania
     * @param email
     * @param password
     * @return
     */
    public DashboardPage fillValidLoginForm(String email, String password){
        this.loginEmail.sendKeys(email);
        this.loginPassword.sendKeys(password);
        loginButton.click();
        return new DashboardPage(driver);
    }

    /**
     * Metoda zawraca text Alertu o niepoprawnym formacie email
     * @return
     */

    public String getAlertInvalidLoginEmail(){
        return alertInvalidEmailFormat.getText();
    }

    /**
     * Wylogowanie użytkownika
     * @return
     */
    public LoginPage logOutUser()
    {
        if(logOutUserLink.isDisplayed()){
            logOutUserLink.click();
        }else
        {
            userAccountImg.click();
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(logOutUserLink));
            logOutUserLink.click();
        }
        return new LoginPage(driver);
    }
}
