package pl.wakacyjnipiraci.pop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FavoritePage {
    private WebDriver driver;
    @FindBy (xpath = "//h3[contains(text(),'Nie masz jeszcze ulubionych')]")
    private WebElement emptyLoggedLikesText;
    @FindBy (xpath = "//p[contains(text(),'Zachowaj to, co cię inspiruje! W trakcie przeglądania kliknij w serduszko, aby zapisać w Ulubionych.')]")
    private WebElement emptyLoggedLikesTextAddition;
    @FindBy (xpath = "//h3[contains(text(),'Zaloguj się, by zachować to, co cię inspiruje')]")
    private WebElement emptyUnloggedLikesText;
    @FindBy (xpath = "//p[contains(text(),'Moźesz dodać do 99 pozycji')]")
    private WebElement emptyUnloggedLikesTextAddition;


    public FavoritePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    /**
     * Metoda zwraca text zaslepki dla niezalogowanego uzytkownika
     * @return
     */
    public String getEmptyUnloggedLikesText(){
        return emptyUnloggedLikesText.getText();
    }

    /**
     * Metoda zwraca text dodatkowy zaslepki dla niezalogowanego uzytkownika
     * @return
     */
    public String getEmptyUnloggedLikesTextAddition(){
        return emptyUnloggedLikesTextAddition.getText();
    }

    /**
     * Metoda zwraca text zaslepki dla zalogowanego uzytkownika
     * @return
     */
    public String getEmptyLoggedLikesText(){
        return emptyLoggedLikesText.getText();
    }

    /**
     * Metoda zwraca text dodatkowy zaslepki dla zalogowanego uzytkownika
     * @return
     */
    public String getEmptyLoggedLikesTextAddition(){
        return emptyLoggedLikesTextAddition.getText();
    }
}
