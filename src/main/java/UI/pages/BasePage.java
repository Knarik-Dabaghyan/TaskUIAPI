package UI.pages;
import UI.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;

import UI.utils.Waits;

public class BasePage {
    protected WebDriver driver;
    protected Waits waits;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
    }
}