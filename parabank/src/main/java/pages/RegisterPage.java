package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    private By linkRegister = By.linkText("Register");
    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zip = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirmPassword = By.id("repeatedPassword");
    private By registerBtn = By.xpath("//input[@value='Register']");

    private By successMessage = By.xpath("//p[contains(text(),'Your account was created successfully')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void openRegisterPage() {
        wait.until(ExpectedConditions.elementToBeClickable(linkRegister)).click();
    }

    public void registerNewUser(String fName, String lName, String addr, String cityText, String stateText,
                                String zipText, String phoneText, String ssnText, String user, String pass) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).clear();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(address).clear();
        driver.findElement(address).sendKeys(addr);
        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(cityText);
        driver.findElement(state).clear();
        driver.findElement(state).sendKeys(stateText);
        driver.findElement(zip).clear();
        driver.findElement(zip).sendKeys(zipText);
        driver.findElement(phone).clear();
        driver.findElement(phone).sendKeys(phoneText);
        driver.findElement(ssn).clear();
        driver.findElement(ssn).sendKeys(ssnText);

        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmPassword).clear();
        driver.findElement(confirmPassword).sendKeys(pass);

        driver.findElement(registerBtn).click();

        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert a = driver.switchTo().alert();
            a.accept();
        } catch (Exception ignored) {}

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }
}
