package pl.wakacyjnipiraci.pop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    private WebDriver driver;
    @FindBy(id = "signup-email")
    private WebElement signupEmail;
    @FindBy (id = "signup-password")
    private WebElement signupPassword;
    @FindBy (xpath = "//button[@class='hp__sc-ds2rrp-10 erSGim'][contains(text(),'Zarejestruj się')]")
    private WebElement signupButton;
    @FindBy (xpath = "//div[contains(text(),'E-mail jest już w użyciu')]")
    private WebElement alertExistingEmail;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Metoda odpowiadajaca za wypelnienie formularza rejestracji
     * @param email
     * @param password
     * @return
     */
    public DashboardPage fillValidSignUpForm(String email, String password){
        this.signupEmail.sendKeys(email);
        this.signupPassword.sendKeys(password);
        signupButton.click();
        return new DashboardPage(driver);
    }

    /**
     * Metoda zawraca text Alertu o istniejacym juz mejlu
     * @return
     */
    public String getAlertExistingEmail(){
        return alertExistingEmail.getText();
    }
}
