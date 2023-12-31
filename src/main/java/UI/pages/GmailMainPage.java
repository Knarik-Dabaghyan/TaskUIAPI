package UI.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import UI.utils.Waits;

public class GmailMainPage extends BasePage{
    Logger logger = LogManager.getRootLogger();
    private final String gmailPageIdentifierLocator = "//a[@title = 'Gmail' and @class = 'gb_ke gb_pc gb_ie']";
    @FindBy(xpath = gmailPageIdentifierLocator)
    private WebElement gmailPageIdentifier;

    @FindBy(xpath = "//div[text() = 'Compose']")
    private WebElement composeButton;

    private final String searchButtonLocator = "//input[@class='gb_ef']";
    @FindBy(xpath = "//input[@class='gb_ef']")
    private WebElement searchButton;

    private final String starredPageTextLocator = "//td[@class='TC']";
    @FindBy(xpath = "//td[@class='TC']")
    private WebElement starredPageText;

    private final String searchedMailLocator = "//div[@class='BltHke nH oy8Mbf']";
    @FindBy(xpath = "//div[@class='BltHke nH oy8Mbf']")
    private WebElement searchedMail;

    @FindBy(xpath = "//div[@data-tooltip = 'Sent']")
    private WebElement sentButton;

    private final String inboxButtonLocator = "//a[@href='https://mail.google.com/mail/u/0/#inbox']";
    @FindBy(xpath = "//a[@href='https://mail.google.com/mail/u/0/#inbox']")
    private WebElement inboxButton;

    private final String draftsButtonLocator = "//div[@data-tooltip = 'Drafts']";
    @FindBy(xpath = draftsButtonLocator)
    private WebElement draftsButton;

    private final String hoverTextLocator = "//div[@class='TO aBP nZ aiq NQ']";
    @FindBy(xpath = hoverTextLocator)
    private WebElement hoverText;

    private final String draftsQuantityLocator = "//a[contains(text(),'Drafts')]/parent::span/following-sibling::div";
    @FindBy(xpath = draftsQuantityLocator)
    private WebElement draftsQuantity;

    @FindBy(xpath = "//a[@class = 'gb_C gb_Ma gb_h']")
    private WebElement userButton;

    private final String signOutButtonLocator = "//a[text() = 'Sign out']";
    @FindBy(xpath = signOutButtonLocator)
    private WebElement signOutButton;
    public GmailMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public int getDraftsQuantity() {
        waits.waitElementToBeClickableByLocator(By.xpath(draftsQuantityLocator));
        logger.info("draft quantity is "+draftsQuantity.getText());
        return Integer.parseInt(draftsQuantity.getText());
    }

    public void clickOnComposeButton() {
        logger.info("click on Compose Button");
        composeButton.click();
    }

    public void openDraftsPage() {
        waits.waitElementToBeClickableByLocator(By.xpath(draftsButtonLocator));
        draftsButton.click();
        logger.info("open draft page");
    }

    public boolean isInGmailPage() {
        waits.waitElementVisibility(By.xpath(gmailPageIdentifierLocator));
        return gmailPageIdentifier.isDisplayed();
    }

    public void openSentMails() {
        waits.waitElementToBeClickableByLocator(By.xpath(draftsQuantityLocator));
        sentButton.click();
        logger.info("open Sent mail page");
    }

    public void searchName(String name) {
        waits.waitElementToBeClickableByLocator(By.xpath(searchButtonLocator));
        Actions action = new Actions(driver);
        action.sendKeys(searchButton, name + Keys.ENTER).build().perform();
        logger.info("search-"+name+"name");
    }

    public boolean isContainSearchedName(String name) {
        waits.waitElementToBeClickableByLocator(By.xpath(searchedMailLocator));
        String searchMailText = searchedMail.getText();
        return searchMailText.contains(name);
    }
    public boolean isContainStarredText(String name) {
        waits.waitElementToBeClickableByLocator(By.xpath(starredPageTextLocator));
        String starredText = starredPageText.getText();
        return starredText.contains(name);
    }

    public void hoverInboxButton() {
        waits.waitElementToBeClickableByLocator(By.xpath(inboxButtonLocator));
        Actions action = new Actions(driver);
        action.moveToElement(inboxButton).perform();
        logger.info("hover inbox button");
    }

    public String getHoverText() {
        waits.waitElementToBeClickableByLocator(By.xpath(hoverTextLocator));
        logger.info("hover text is"+hoverText.getText());
        return hoverText.getText();
    }

    public void openAlertWindow() {
        waits.waitElementVisibility(By.xpath(searchButtonLocator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('Welcome to Starred page');");
        logger.info("open alert window");
    }

    public void navigateToStarredMailPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.location = 'https://mail.google.com/mail/u/0/#starred'");
        logger.info("navigate to starred mail page");
    }

    public void signOut() {
        userButton.click();
        waits.waitElementToBeClickableByLocator(By.xpath(signOutButtonLocator));
        signOutButton.click();
        logger.info("sign out");
    }
}
