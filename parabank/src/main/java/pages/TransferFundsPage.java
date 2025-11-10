package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage extends BasePage {

    private By amountField = By.id("amount");
    private By fromAccountSelect = By.id("fromAccountId");
    private By toAccountSelect = By.id("toAccountId");
    private By transferBtn = By.cssSelector("input[value='Transfer']");
    private By transferCompleteHeader = By.xpath("//h1[text()='Transfer Complete!']");

    public TransferFundsPage(org.openqa.selenium.WebDriver driver) {
        super(driver);
    }

    public void transfer(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField)).clear();
        driver.findElement(amountField).sendKeys(amount);

        try {
            Select from = new Select(driver.findElement(fromAccountSelect));
            Select to = new Select(driver.findElement(toAccountSelect));
            if (from.getOptions().size() > 0) from.selectByIndex(0);
            if (to.getOptions().size() > 0) to.selectByIndex(0);
        } catch (Exception ignored) {}

        driver.findElement(transferBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(transferCompleteHeader));
    }

    public boolean isTransferComplete() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(transferCompleteHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
