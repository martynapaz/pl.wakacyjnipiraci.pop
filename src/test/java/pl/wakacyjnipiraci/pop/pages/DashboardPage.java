package pl.wakacyjnipiraci.pop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    /**
     * Strona po zalogowaniu użytkownika umożliwiająca dodawać ulubione oferty, ustawienia profilu
     */
    private WebDriver driver;
    @FindBy(xpath = "//body/div[@id='hp-app']/div[2]/div[1]/div[3]/div[1]/div[1]/img[1]")
    private WebElement userAccountImg;
    @FindBy (xpath = "//div[contains(text(),'Witaj!,')]")
    private WebElement userNameLogged;
    @FindBy (xpath = "//body/div[@id='hp-app']/div[2]/div[1]/div[3]/a[1]/span[1]")
    private WebElement favoriteHeartBar;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }


    /**
     * Zwraca nazwe zalogowanego użytkownika
     * @return
     */
    public String getUserNameText(){
        userAccountImg.click();
        return userNameLogged.getText();
    }

    /**
     * Kliknięcie i przejście do strony z ulubionymi ofertami, artykulami
     */
    public FavoritePage clickGoToLikePage(){
        favoriteHeartBar.click();
        return new FavoritePage(driver);
    }
}