package UI.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import UI.model.User;
import UI.utils.Waits;

public class LoginPage extends BasePage{
    Logger logger = LogManager.getRootLogger();
    private final String nextButtonLocator = "//div[@id='identifierNext']";
    private final String passwordLocator = "password";
    private final String nextButtonPasswordLocator = "passwordNext";

    @FindBy(id = nextButtonPasswordLocator)
    private WebElement nextButtonPassword;

    @FindBy(name = passwordLocator)
    private WebElement passwordFiled;

    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement emailFiled;

    @FindBy(xpath = nextButtonLocator)
    private WebElement nextButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void login(User user) {
        waits.waitElementToBeClickableByLocator(By.xpath(nextButtonLocator));
        emailFiled.sendKeys(user.getUserEmail());
        logger.info("type username"+user.getUserEmail()+" ");
        nextButton.click();
        waits.waitElementToBeClickableByLocator(By.id(nextButtonPasswordLocator));
        passwordFiled.sendKeys(user.getUserPassword());
        logger.info("type password"+user.getUserPassword());
        waits.waitElementToBeClickableByLocator(By.id(nextButtonPasswordLocator));
        nextButtonPassword.click();
    }
}
