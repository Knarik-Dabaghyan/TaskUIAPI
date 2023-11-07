package UITest;

import UI.driver.DriverSingleton;
import UI.model.User;
import UI.pages.*;
import UI.service.UserCreator;
import UI.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected Waits waits;
    protected LoginPage loginPage;
    protected GmailMainPage gmailMainPage;
    protected SentPage sentPage;
    protected MailCreatingPage mailCreatingPage;
    protected DraftPage draftPage;

    @BeforeMethod
    public void setup() {
        driver = DriverSingleton.getDriver();
        driver.get("https://mail.google.com/");
        waits = new Waits(driver);
        loginPage = new LoginPage(driver);
        gmailMainPage = new GmailMainPage(driver);
        sentPage = new SentPage(driver);
        mailCreatingPage = new MailCreatingPage(driver);
        draftPage = new DraftPage(driver);

        User testUser = UserCreator.getCredentialsFromProperty();
        loginPage.login(testUser);
    }

    @AfterMethod
    public void tearDown() {
        DriverSingleton.closeDriver();

    }
    }