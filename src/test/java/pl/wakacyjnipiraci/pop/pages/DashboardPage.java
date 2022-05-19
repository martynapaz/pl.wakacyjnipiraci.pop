package pl.wakacyjnipiraci.pop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    @FindBy(xpath = "//body/div[@id='hp-app']/div[2]/div[1]/div[3]/div[1]/div[1]/img[1]")
    private WebElement userAccountImg;
    @FindBy (xpath = "//div[contains(text(),'Witaj!,')]")
    private WebElement userNameLogged;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public String getUserNameText(){
        userAccountImg.click();
        return userNameLogged.getText();
    }
}
