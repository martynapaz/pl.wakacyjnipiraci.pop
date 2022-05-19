package pl.wakacyjnipiraci.pop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    private WebDriver driver;
    @FindBy (xpath = "//div[contains(text(),'Ilość wyników: ')]")
    private WebElement resultCounterText;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Metoda zwraca text wyniku zadanego w wyszukiwarce
     * @return
     */

    public String getResultCounterText(){
        return resultCounterText.getText();
    }
}