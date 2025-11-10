package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private By accountsOverviewHeader = By.xpath("//h1[text()='Accounts Overview']");
    private By openNewAccountLink = By.linkText("Open New Account");
    private By transferFundsLink = By.linkText("Transfer Funds");
    private By billPayLink = By.linkText("Bill Pay");
    private By logoutLink = By.linkText("Log Out");
    private By welcomeHeader = By.xpath("//h1[text()='Welcome']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountsOverviewDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(accountsOverviewHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isWelcomeDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void goToOpenNewAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(openNewAccountLink)).click();
    }

    public void goToTransferFunds() {
        wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink)).click();
    }

    public void goToBillPay() {
        wait.until(ExpectedConditions.elementToBeClickable(billPayLink)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
