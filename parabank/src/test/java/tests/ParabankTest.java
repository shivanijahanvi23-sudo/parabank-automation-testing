package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ParabankTest extends BaseClass {

    private static String username = "shivani" + System.currentTimeMillis();
    private static final String password = "Test@123";
    private static final String ssnValue = "123456789";

    @Test(priority = 1)
    public void registerUser() {
        RegisterPage register = new RegisterPage(driver);
        register.openRegisterPage();
        register.registerNewUser(
                "Shivani", "K", "Hyderabad Road", "Hyderabad", "TS",
                "500072", "9876543210", ssnValue, username, password
        );

        HomePage home = new HomePage(driver);
        boolean ok = home.isWelcomeDisplayed() || home.isAccountsOverviewDisplayed();
        Assert.assertTrue(ok, "Registration failed or user not logged in after registration.");
        System.out.println("Registered and logged in with username: " + username);
    }

    @Test(priority = 2)
    public void loginUser() {
        LoginPage login = new LoginPage(driver);
        login.login(username, password);

        HomePage home = new HomePage(driver);
        boolean loggedIn = home.isAccountsOverviewDisplayed() || home.isWelcomeDisplayed();
        Assert.assertTrue(loggedIn, "Login failed or accounts overview not visible.");
    }

    @Test(priority = 3)
    public void openAccount() {
        HomePage home = new HomePage(driver);
        home.goToOpenNewAccount();

        OpenAccountPage open = new OpenAccountPage(driver);
        String acct = open.openSavingsAccountAndGetNumber();
        Assert.assertTrue(!acct.isEmpty(), "Account opening failed or account number not retrieved");
        System.out.println("New account created: " + acct);
    }

    @Test(priority = 4)
    public void transferFunds() {
        HomePage home = new HomePage(driver);
        home.goToTransferFunds();

        TransferFundsPage tf = new TransferFundsPage(driver);
        tf.transfer("100");
        Assert.assertTrue(tf.isTransferComplete(), "Transfer did not complete");
    }

    @Test(priority = 5)
    public void billPayment() {
        HomePage home = new HomePage(driver);
        home.goToBillPay();

        BillPaymentPage bp = new BillPaymentPage(driver);
        bp.payBill("Electricity Board", "Main Street", "Hyderabad", "TS", "500072",
                "9999999999", "123456", "50");
        Assert.assertTrue(bp.isBillPaymentComplete(), "Bill payment did not complete");
    }

    @Test(priority = 6)
    public void logoutUser() {
        LogoutPage logout = new LogoutPage(driver);
        logout.clickLogout();
        Assert.assertTrue(logout.isLoggedOut(), "Logout not successful");
    }
}
