package UI.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import UI.utils.Waits;

import java.util.List;

public class DraftPage extends BasePage{
    Logger logger = LogManager.getRootLogger();

    private final String allMailsDraftsLocator = "//span[text() = 'Draft']//ancestor::tr[@role = 'row']";
    @FindBy(xpath = allMailsDraftsLocator)
    private List<WebElement> allMailsInDrafts;

    private final String allMailsSubjectLocator = "//span[@class = 'bog']/span";
    public DraftPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void openLastMailFromDrafts() {
        waits.waitElementToBeClickableByLocator(By.xpath(allMailsDraftsLocator));
        allMailsInDrafts.get(0).click();
        logger.info("Open last mail from draft");

    }

}
