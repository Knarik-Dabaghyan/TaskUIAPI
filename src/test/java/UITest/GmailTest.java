package UITest;

import UI.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import UI.driver.DriverSingleton;
import UI.pages.*;
import UI.service.UserCreator;
import UI.utils.Waits;
import UI.model.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class GmailTest extends BaseTest {
    SoftAssert softAssert;
    private final String mailSubjectText = "hello";
    private final String mailBodyText = "test message";
    private final String otherUserMail = "knarikdabaghyan@gmail.com";
    private String name = "Knarik";
    int sentMailsBeforeSendinNewMail;
    int draftsQuantityAfterCreatingNewMail;
    int draftsQuantityBeforeCreatingNewMail;
    int draftsQuantityAfterSendingMail;
    int sentMailsAfterSendingNewMail;
    @Test()
    public void gmailTest() {
        assertTrue(gmailMainPage.isInGmailPage(), "It's not Gmail main page");
        gmailMainPage.openSentMails();
        sentMailsBeforeSendinNewMail = sentPage.getSentMailsCount();
        draftsQuantityBeforeCreatingNewMail = gmailMainPage.getDraftsQuantity();
        gmailMainPage.clickOnComposeButton();
        mailCreatingPage.enterOtherUserEmail(otherUserMail);
        mailCreatingPage.enterSubjectText(mailSubjectText);
        mailCreatingPage.enterBodyText(mailBodyText);
        mailCreatingPage.clickOnSetSaveAndCloseButton();
        draftsQuantityAfterCreatingNewMail = gmailMainPage.getDraftsQuantity();
        assertEquals(draftsQuantityAfterCreatingNewMail - 1, draftsQuantityBeforeCreatingNewMail, "The message isn't saved in drafts");

        gmailMainPage.openDraftsPage();
        draftPage.openLastMailFromDrafts();
        softAssert = new SoftAssert();
        softAssert.assertEquals(mailBodyText, mailCreatingPage.getTextFromMailBody(), "Actual body are different from Expected");
        softAssert.assertEquals(otherUserMail, mailCreatingPage.getTextFromSendToFiled(), "Users whom mails was sent are different");
        softAssert.assertAll();

        mailCreatingPage.clickSendButton();
        draftsQuantityAfterSendingMail = gmailMainPage.getDraftsQuantity();
        assertEquals(draftsQuantityAfterSendingMail, draftsQuantityAfterCreatingNewMail - 1, "After sending mail, mail isn't disappeared from drafts");
        gmailMainPage.openSentMails();
        sentMailsAfterSendingNewMail = sentPage.getSentMailsCount();
        assertEquals(sentMailsBeforeSendinNewMail + 1, sentMailsAfterSendingNewMail, "Sent mail isn't in Sent folder");

        gmailMainPage.signOut();
    }

    @Test
    public void searchByNameTest() {
        gmailMainPage.searchName(name);
        assertTrue(gmailMainPage.isContainSearchedName(name), "there is no mail that contain searched name");
    }

    @Test
    public void checkHoverText() {
        gmailMainPage.hoverInboxButton();
        assertEquals(gmailMainPage.getHoverText(), "Inbox", "Hover text does not match");
    }

    @Test
    public void checkStarredPage() throws InterruptedException {
        assertTrue(gmailMainPage.isInGmailPage(), "It's not Gmail main page");
        gmailMainPage.navigateToStarredMailPage();
        assertTrue(gmailMainPage.isContainStarredText("No starred messages"), "Text does not match");
        gmailMainPage.openAlertWindow();
    }

}