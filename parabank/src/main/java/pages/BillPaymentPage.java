package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BillPaymentPage extends BasePage {

    private By payeeName = By.name("payee.name");
    private By payeeAddress = By.name("payee.address.street");
    private By payeeCity = By.name("payee.address.city");
    private By payeeState = By.name("payee.address.state");
    private By payeeZip = By.name("payee.address.zipCode");
    private By payeePhone = By.name("payee.phoneNumber");
    private By payeeAccount = By.name("payee.accountNumber");
    private By verifyAccount = By.name("verifyAccount");
    private By amount = By.name("amount");
    private By sendPaymentBtn = By.cssSelector("input[value='Send Payment']");
    private By billCompleteHeader = By.xpath("//h1[text()='Bill Payment Complete']");

    public BillPaymentPage(org.openqa.selenium.WebDriver driver) {
        super(driver);
    }

    public void payBill(String payee, String street, String city, String stateText, String zipText,
                        String phoneText, String accountNum, String amt) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(payeeName)).clear();
        driver.findElement(payeeName).sendKeys(payee);
        driver.findElement(payeeAddress).sendKeys(street);
        driver.findElement(payeeCity).sendKeys(city);
        driver.findElement(payeeState).sendKeys(stateText);
        driver.findElement(payeeZip).sendKeys(zipText);
        driver.findElement(payeePhone).sendKeys(phoneText);
        driver.findElement(payeeAccount).sendKeys(accountNum);
        driver.findElement(verifyAccount).sendKeys(accountNum);
        driver.findElement(amount).sendKeys(amt);
        driver.findElement(sendPaymentBtn).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(billCompleteHeader));
    }

    public boolean isBillPaymentComplete() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(billCompleteHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
