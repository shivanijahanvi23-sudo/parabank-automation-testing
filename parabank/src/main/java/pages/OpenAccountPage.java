package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage extends BasePage {

    private By accountTypeSelect = By.id("type");
    private By fromAccountSelect = By.id("fromAccountId");
    private By openAccountBtn = By.cssSelector("input[value='Open New Account']");
    private By accountOpenedHeader = By.xpath("//h1[text()='Account Opened!']");
    private By newAccountIdLink = By.id("newAccountId");

    public OpenAccountPage(org.openqa.selenium.WebDriver driver) {
        super(driver);
    }

    public String openSavingsAccountAndGetNumber() {
        wait.until(ExpectedConditions.elementToBeClickable(accountTypeSelect));
        Select type = new Select(driver.findElement(accountTypeSelect));
        try {
            type.selectByVisibleText("SAVINGS");
        } catch (Exception ignored) {
            type.selectByIndex(0);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccountSelect));
        Select from = new Select(driver.findElement(fromAccountSelect));
        if (from.getOptions().size() > 0) {
            from.selectByIndex(0);
        }

        driver.findElement(openAccountBtn).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(accountOpenedHeader));

        try {
            WebElement acctLink = wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountIdLink));
            return acctLink.getText().trim();
        } catch (Exception e) {
            try {
                WebElement any = driver.findElement(By.xpath("//a[contains(@href,'activity.htm')]"));
                return any.getText().trim();
            } catch (Exception ex) {
                return "";
            }
        }
    }

    public boolean isAccountOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(accountOpenedHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
