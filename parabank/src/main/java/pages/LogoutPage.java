package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogoutPage extends BasePage {

    private By logoutLink = By.linkText("Log Out");
    private By loginPanel = By.id("loginPanel");

    public LogoutPage(org.openqa.selenium.WebDriver driver) {
        super(driver);
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public boolean isLoggedOut() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPanel)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
